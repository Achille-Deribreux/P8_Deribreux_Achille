package tourGuide.user.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.con.Entity.*;
import com.con.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import tourGuide.user.Service.UserService;
import tourGuide.user.Utils.Mapper;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * This method answer to a get request at /getUserLocation and returns the location of the user
     * @param userName username of the user
     * @return the location of the user
     */
    @GetMapping("/getUserLocation")
    public VisitedLocation getUserLocation(@RequestParam(value="userName")String userName) throws URISyntaxException {
        logger.info("received request at /getUserLocation for username : "+userName);
        return userService.getUserLocation(userName);
    }

    /**
     * This method answer to a get request at /getUser and returns the wanted user
     * @param userName username of the user we want
     * @return the wanted user
     */
    @GetMapping("/getUser")
    public UserDTO getUser(@RequestParam(value="userName")String userName){
        logger.info("received request at /getUser for username : "+userName);
       User user = userService.getUser(userName);
       UserDTO userDTO = Mapper.userDTOMapper(user);
       return userDTO;
    }

    /**
     * This method answer to a get request at /getAllUsers and returns a list of all the users
     * @return list of all the users (dto list)
     */
    @GetMapping("/getAllUsers")
    public List<UserDTO> getAllUsers(){
        logger.info("received request at /getAllUsers");
        return Mapper.userListMapper(userService.getAllUsers());
    }

    /**
     * This method answer to a get request at /getAllUsersWithLocation and returns a map of all the users id and their current position
     * @return map of all the users with userId as key and position as value
     */
    @GetMapping("/getAllUsersWithLocation")
    public Map<UUID, Location> getAllUsersWithLocation() {
        logger.info("received request at /getAllUsers");
        return userService.getAllUsersWithLocation();
    }


    /**
     * This method answer to a post request at /addUserVisitedLocation and add a visited location to a user
     * @param userName username of the user for which you want to add a visited location
     * @param lastVisitedLocation visited location you want to add for the user
     */
    @PostMapping("/addUserVisitedLocation")
    public void addUserVisitedLocation(@RequestParam(value="userName")String userName, @RequestBody VisitedLocation lastVisitedLocation){
        logger.info("received request at /addUserVisitedLocation for username : "+userName);
        userService.addUserVisitedLocation(userName, lastVisitedLocation);
    }

    /**
     * This method answer to a post request at /addUserReward and add a reward to a user
     * @param userName username of the user for which you want to add a reward
     * @param userRewardToAdd reward you want to add for the user
     */
    @PostMapping("/addUserReward")
    public void addUserReward(@RequestParam(value="userName")String userName, @RequestBody UserReward userRewardToAdd){
        logger.info("received request at /addUserReward for username : "+userName);
        userService.addUserReward(userName, userRewardToAdd);
    }

    /**
     * This method answer to a post request at /addUserReward and add Preferences for a user
     * @param userName username of the user for which you want to add preferences
     * @param userPreferences preferences you want to add for the user
     */
    @PostMapping("/addUserPreferences")
    public void addUserPreferences(@RequestParam(value="userName")String userName, @RequestBody UserPreferencesDTO userPreferences){
        logger.info("received request at /addUserPreferences for username : "+userName);
        userService.addUserPreferences(userName, Mapper.userPreferencesDTOMapper(userPreferences));
    }

    /**
     * This method answer to a post request at /addUserReward and add a tripDeals to a user
     * @param userName username of the user for which you want to add trip deals
     * @param tripDeals trip deals you want to add for the user
     */
    @PostMapping("/addTripDeals")
    public void addUserTripDeals(@RequestParam(value="userName")String userName, @RequestBody List<Provider> tripDeals){
        logger.info("received request at /addTripDeals for username : "+userName);
        userService.addTripDeals(userName, tripDeals);
    }
}
