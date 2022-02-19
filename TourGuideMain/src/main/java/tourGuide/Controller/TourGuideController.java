package tourGuide.Controller;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.con.DTO.*;
import com.con.Entity.*;
import tourGuide.WebClient.UserWebClient;
import tourGuide.service.TourGuideService;

@RestController
public class TourGuideController {

    private Logger logger = LoggerFactory.getLogger(TourGuideService.class);

	@Autowired
    private TourGuideService tourGuideService = new TourGuideService();

    @Autowired
    private UserWebClient userWebClient = new UserWebClient();

    /**
     * Root request, returns "hello"
     * @return String who says hello
     */
    @GetMapping("/")
    public String index() {
        logger.info("request at root (/)");
       return "Greetings from TourGuide!";
    }

    /**
     * This method answer to a get request at /getLocation and returns the user's location
     * @param userName username of the user for which we want the location
     * @return visited location, the location of the user for which we want the location + timestamp
     */
    @GetMapping("/getLocation")
    public VisitedLocation getLocation(@RequestParam String userName) {
        logger.info("request at /getLocation with username :"+userName);
    	return tourGuideService.getLocation(userName);
    }

    /**
     * This method answer to a get request at /getNearbyAttractions and returns the closest attractions from a user
     * @param userName user from which you want the closest attractions
     * @return a list of the 5 closest attraction of the user's position
     */
    @GetMapping("/getNearbyAttractions")
    public List<NearbyAttractionsDTO> getNearbyAttractions(@RequestParam String userName) throws URISyntaxException {
        logger.info("request at /getNearbyAttractions with username :"+userName);
    	return tourGuideService.getNearByAttractions(userName);
    }

    /**
     * This method answer to a get request at /getRewards and returns all the rewards of a user
     * @param userName username of the user for which we want the rewards
     * @return a list of the rewards of the wanted user
     */
    @GetMapping("/getRewards")
    public List<UserReward> getRewards(@RequestParam String userName) {
        logger.info("request at /getRewards with username :"+userName);
    	return tourGuideService.getUserRewards(userName);
    }

    /**
     * This method answer to a get request at /getAllCurentLocations and returns a map with all the user's positions
     * @return a map with all the users position (userId as key and current location as value)
     */
    @GetMapping("/getAllCurrentLocations")
    public Map<UUID, Location> getAllCurrentLocations() {
        logger.info("request at /getAllCurrentLocation");
    	return tourGuideService.getAllUsersWithLocation();
    }


    /**
     * This method answer to a get request at /getTripDeals and returns a List of trip deals
     * @param userName userName for which you want to have trip deals
     * @return a list of trip deals for a wanted user
     */
    @GetMapping("/getTripDeals")
    public List<Provider> getTripDeals(@RequestParam String userName) throws URISyntaxException {
        logger.info("request at /getTripDeals with username :"+userName);
    	return tourGuideService.getTripDeals(userName);
    }

    /**
     * This method answer to a post request at /addUserPreferences and allows to set userPreferences for a user
     * @param userName username of the user for which you want to add user preferences
     * @param userPreferences userPreferencesDTO, the userPreferences that you want to set for the user
     */
    @PostMapping("/addUserPreferences")
    public void addUserPreferences(@RequestParam(value="userName")String userName, @RequestBody UserPreferencesDTO userPreferences) throws URISyntaxException {
        logger.info("request at /addUserPreferences with username :"+userName);
        tourGuideService.addUserPreferences(userName,userPreferences);
    }
}