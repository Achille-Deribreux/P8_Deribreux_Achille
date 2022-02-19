package tourGuide.rewards.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rewardCentral.RewardCentral;
import tourGuide.rewards.Entity.*;
import tourGuide.rewards.Utils.DistanceCalculator;
import tourGuide.rewards.WebClient.GpsWebClient;
import tourGuide.rewards.WebClient.UserWebClient;

import java.net.URISyntaxException;
import java.util.List;

@Service
public class RewardsService {

    private Logger logger = LoggerFactory.getLogger(RewardsService.class);

    @Autowired
    private GpsWebClient gpsWebClient = new GpsWebClient();

    @Autowired
    private UserWebClient userWebClient = new UserWebClient();

    private RewardCentral rewardsCentral = new RewardCentral();

    private List<Attraction> attractions = null;

    private int proximityBuffer = 10;

    public void setGpsWebClient(GpsWebClient gpsWebClient) {
        this.gpsWebClient = gpsWebClient;
    }

    public void setUserWebClient(UserWebClient userWebClient) {
        this.userWebClient = userWebClient;
    }

    public void setRewardsCentral(RewardCentral rewardsCentral) {
        this.rewardsCentral = rewardsCentral;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }

    public void setProximityBuffer(int proximityBuffer) {
        this.proximityBuffer = proximityBuffer;
    }


    /**
     * This method calculates the rewards for the given User and add them by calling userWebclient.
     * A user receives a reward only if the distance between him and the attraction is less than the proximitybuffer value
     * A user can only have one rewards/attraction
     * @param user the user from which we want the rewards
     * @return List of the userRewards
     */
    public List<UserReward> calculateRewards(User user) {
        if(attractions == null){
            logger.info("load attractions list bc null");
            attractions = gpsWebClient.getAllAttractions();
        }
        logger.info("start calculating rewards for user + "+user.getUserName());
        user.getVisitedLocations().forEach(visitedLocation ->
                {
                    logger.info("start stream the visitedLocation list");
                    attractions.forEach(a -> {
                        if (user.getUserRewards().stream().noneMatch(r -> r.getAttraction().getAttractionName().equals(a.getAttractionName()))) {
                            if (nearAttraction(visitedLocation, a)) {
                                try {
                                    logger.info("User gets a reward !");
                                    user.getUserRewards().add(new UserReward(visitedLocation, a, getRewardPoints(a, user)));
                                    userWebClient.addUserReward(user.getUserName(), new UserReward(visitedLocation, a, getRewardPoints(a, user)));
                                } catch (URISyntaxException e) {
                                    e.printStackTrace();
                                }
                            }
                    }
                });});
        return user.getUserRewards();
    }

    /**
     * This Method check if the distance between the visitedLocation and the attraction is less than the proximity buffer value
     * @param visitedLocation a visited location
     * @param attraction the attraction
     * @return Boolean, if true then distance between the visitedLocation and the attraction is less than the proximity buffer value
     */
     boolean nearAttraction(VisitedLocation visitedLocation, Attraction attraction) {
         logger.info("Check distance between : "+ visitedLocation.getLocation() +" position and "+ attraction.getAttractionName()+" attraction");
        return DistanceCalculator.distance(attraction.getLatitude(), attraction.getLongitude(), visitedLocation.getLocation().getLatitude(), visitedLocation.getLocation().getLongitude()) < proximityBuffer;
    }

    /**
     * Method who calls the rewardsCentral app to calculate rewards
     * @param attraction the attraction for which we want to calculate a reward
     * @param user the user for which we want to calculate a reward
     * @return a number of rewardPoints
     */
     int getRewardPoints(Attraction attraction, User user) {
         logger.info("calculate rewards for user "+user.getUserName()+" and attraction "+ attraction.getAttractionName());
        return rewardsCentral.getAttractionRewardPoints(attraction.getAttractionId(), user.getUserId());
    }
}
