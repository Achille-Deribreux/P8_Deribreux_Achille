package tourGuide.user.Service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tourGuide.user.Controller.UserController;
import tourGuide.user.DTO.UserDTO;
import tourGuide.user.Entity.*;
import tourGuide.user.Repository.UserRepository;
import tourGuide.user.Utils.Mapper;
import tourGuide.user.WebClient.GpsWebClient;
import tourGuide.user.WebClient.RewardsWebClient;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    GpsWebClient gpsWebClient;

    @Autowired
    RewardsWebClient rewardsWebClient;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setGpsWebClient(GpsWebClient gpsWebClient) {
        this.gpsWebClient = gpsWebClient;
    }

    public void setRewardsWebClient(RewardsWebClient rewardsWebClient) {
        this.rewardsWebClient = rewardsWebClient;
    }

    /**
     * Method who get all the users from the repository
     * @return list of all the users
     */
    public List<User> getAllUsers(){
        logger.info("call the repository to findAllUsers");
        return userRepository.findAllUsers();
    }

    /**
     * Method who add tripDeals for a user in the repository
     * @param userName name of the user for which you want to add tripDeals
     * @param tripdeals tripdeals to add
     */
    public void addTripDeals(String userName,List<Provider> tripdeals){
        logger.info("add TripDeals for user : "+userName);
        userRepository.addTripDeals(userName,tripdeals);
    }

    public void addUserPreferences(String userName, UserPreferences userPreferences){
        logger.info("add user preferences for user : "+userName);
        userRepository.addUserPreferences(userName,userPreferences);
    }

    /**
     * Method who get a user based on his username
     * @param username username of the wanted
     * @return the wanted user
     */
    public User getUser(String username){
        logger.info("get user for username : "+username);
        return userRepository.getUser(username);
    }

    /**
     * Method who get the userLocation based on the last known location or locates the user if none is known
     * @param userName name of the user for which we want to have location
     * @return VisitedLocation, the last known location or current
     */
    public VisitedLocation getUserLocation(String userName) throws URISyntaxException {
        logger.info("get location for user : "+userName);
        User user = getUser(userName);
        return (user.getVisitedLocations().size() > 0) ?
                user.getLastVisitedLocation() :
                trackUserLocation(user);
    }

    /**
     * Method who calls the gps to get the user's current location
     * @param user the user from which you want the location
     * @return the current user's location
     */
    public VisitedLocation trackUserLocation(User user) throws URISyntaxException {
        logger.info("track location for user : "+user.getUserName());
        VisitedLocation visitedLocation= gpsWebClient.trackUserLocationFromGps(user.getUserId());
        user.addToVisitedLocations(visitedLocation);
        logger.info("start calculating rewards for user : "+user.getUserName());
        for(UserReward reward : rewardsWebClient.calculateRewards(Mapper.userDTOMapper(user))) {
            user.addUserReward(reward);
        }
        return visitedLocation;
    }

    /**
     * Method who get all the usersId with their location (based on their last visited location)
     * @return Map with all the usersId and their location
     */
    public Map<UUID, Location> getAllUsersWithLocation() {
        logger.info("get all the users with location ");
        return getAllUsers().stream()
                .collect(Collectors.toMap(User::getUserId, user -> user.getLastVisitedLocation().getLocation()));
    }

    /**
     * Method that add a visitedLocation for a user in the repository
     * @param userName name of the user for which we want to add a visitedLocation
     * @param lastVisitedLocation visitedLocation that we want to add
     */
    public void addUserVisitedLocation(String userName, VisitedLocation lastVisitedLocation){
        logger.info("add visitedLocation for user : "+userName);
        userRepository.addUserVisitedLocation(userName, lastVisitedLocation);
    }

    /**
     * Method that add a reward for a user in the repository
     * @param userName name of the user for which we want to add an userReward
     * @param userRewardToAdd userReward that we want to add
     */
    public void addUserReward(String userName, UserReward userRewardToAdd) {
        logger.info("add userReward for user : "+userName);
        userRepository.addUserReward(userName, userRewardToAdd);
    }

}
