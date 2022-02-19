package tourGuide.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tourGuide.DTO.NearbyAttractionsDTO;
import tourGuide.DTO.UserPreferencesDTO;
import tourGuide.Entity.*;
import tourGuide.WebClient.GpsWebClient;
import tourGuide.WebClient.PricerWebClient;
import tourGuide.WebClient.RewardsWebClient;
import tourGuide.WebClient.UserWebClient;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TourGuideService {
	private Logger logger = LoggerFactory.getLogger(TourGuideService.class);

	@Autowired
	private GpsWebClient gpsWebClient = new GpsWebClient();

	@Autowired
	private UserWebClient userWebClient = new UserWebClient();

	@Autowired
	private PricerWebClient pricerWebClient = new PricerWebClient();

	@Autowired
	private RewardsWebClient rewardsWebClient = new RewardsWebClient();

	public void setUserWebClient(UserWebClient userWebClient) {
		this.userWebClient = userWebClient;
	}

	public void setPricerWebClient(PricerWebClient pricerWebClient) {
		this.pricerWebClient = pricerWebClient;
	}

	public void setRewardsWebClient(RewardsWebClient rewardsWebClient) {
		this.rewardsWebClient = rewardsWebClient;
	}

	public void setGpsWebClient(GpsWebClient gpsWebClient) {
		this.gpsWebClient = gpsWebClient;
	}

	public TourGuideService() {
		//Empty constructor
	}

	/**
	 * Method which gets the rewards of a user
	 * @param userName username of the user from which we wants the rewards
	 * @return list of all the rewards of the user
	 */
	public List<UserReward> getUserRewards(String userName) {
		logger.info("get rewards for user : "+userName);
		User user = getUser(userName);
		return user.getUserRewards();
	}

	/**
	 * Method which calls the user app to get a map of all the users with their location
	 * @return map with all the userId and their last known location
	 */
	public Map<UUID, Location> getAllUsersWithLocation(){
		logger.info("get all the users with location");
		return userWebClient.getAllUsersWithLocation();
	}


	/**
	 * Method which calls the pricer app to get tripdeals for a user
	 * @param userName username of the user for which we want tripdeals
	 * @return list of tripdeals for the given user
	 */
	public List<Provider> getTripDeals(String userName) throws URISyntaxException {
		logger.info("get tripDeals for user : "+userName);
		User user = getUser(userName);
		int cumulatativeRewardPoints = user.getUserRewards().stream().mapToInt(UserReward::getRewardPoints).sum();
		return pricerWebClient.generateTripDeals(
				user.getUserId(),
				user.getUserName(),
				user.getUserPreferences().getNumberOfAdults(),
				user.getUserPreferences().getNumberOfChildren(),
				user.getUserPreferences().getTripDuration(),
				cumulatativeRewardPoints
		);
	}

	/**
	 * Method which calls the gps app to get the location of a user
	 * If the user have a location history, the app will return his last location, otherwise, it will get his current location
	 * @param userName username of the user for which we want the location
	 * @return the user location
	 */
	public VisitedLocation getLocation(String userName) {
		logger.info("get location for user : "+userName);
		return userWebClient.getUserLocationFromUser(userName);
	}

	/**
	 * Method which calls the gps app to track the location of a user and add it
	 * @param user user for which we want the location
	 * @return the current location of the user
	 */
	public VisitedLocation trackUserLocation(User user) throws URISyntaxException {
		logger.info("track user : "+user.getUserName());
		VisitedLocation visitedLocation = gpsWebClient.trackUserLocationFromGps(user.getUserId());
		userWebClient.addUserVisitedLocation(user.getUserName(), visitedLocation);
		return visitedLocation;
	}

	/**
	 * Method which calls the rewards app to calculate the rewards of a user
	 * @param user user for which we wants to calculate rewards
	 */
	public void calculateRewards(User user) throws URISyntaxException{
		logger.info("calculate rewards for user : "+user.getUserName());
		rewardsWebClient.calculateRewards(user);
	}

	/**
	 * Method which search the nearby attractions for a given username
	 * @param username username of the user for which we want the nearby attractions
	 * @return a list of the (5) closest attractions
	 */
	public List<NearbyAttractionsDTO> getNearByAttractions(String username) throws URISyntaxException {
		logger.info("get nearby attractions for user : "+username);
		User user = getUser(username);
		VisitedLocation visitedLocation = trackUserLocation(user);
		Location location = visitedLocation.getLocation();

		return gpsWebClient.getNearByAttractionsFromGps(location.getLatitude(), location.getLongitude(), 5);
	}

	/**
	 * Method which calls the user app to get a user based on his userName
	 * @param username username of the wanted user
	 * @return the user that we want
	 */
	public User getUser(String username){
		logger.info("get user : "+username);
		return userWebClient.getUser(username);
	}

	/**
	 * Method which call the pricer app to generate tripDeals
	 * @param user user for which we want to generate tripdeals
	 */
	public void generateTripDeals(User user) throws URISyntaxException {
		logger.info("generate tripDeals for : "+user.getUserName());
		int cumulatativeRewardPoints = user.getUserRewards().stream().mapToInt(UserReward::getRewardPoints).sum();
		pricerWebClient.generateTripDeals(
				user.getUserId(),
				user.getUserName(),
				user.getUserPreferences().getNumberOfAdults(),
				user.getUserPreferences().getNumberOfChildren(),
				user.getUserPreferences().getTripDuration(),
				cumulatativeRewardPoints
		);
	}

	/**
	 * Method who add userPreferences for a user via the user app
	 * @param username username of the user for which we want to add userPreferences
	 * @param userPreferencesDTO userPreferences that we want to add
	 */
	public void addUserPreferences(String username, UserPreferencesDTO userPreferencesDTO) throws URISyntaxException {
		logger.info("add user preferences for : "+username);
		userWebClient.addUserPreferences(username,userPreferencesDTO);
		User user = userWebClient.getUser(username);
		generateTripDeals(user);
	}


	
}
