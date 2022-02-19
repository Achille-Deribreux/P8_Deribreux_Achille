package tourGuide.user.Repository;

import org.javamoney.moneta.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import tourGuide.user.Entity.*;
import tourGuide.user.Exceptions.UserNotFoundException;

import javax.annotation.PostConstruct;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Repository
public class UserRepository {

    public void setInternalUserMap(Map<String, User> internalUserMap) {
        this.internalUserMap = internalUserMap;
    }

    /**
     * Method who returns all the users from the usermap
     * @return List of all the users
     */
    public List<User> findAllUsers(){
        logger.info("find all users from map");
        return new ArrayList<>(internalUserMap.values());
    }



    /**
     * Method who search a user in the usermap based on his username
     * @param userName username of the wanted user
     * @return wanted user
     */
    public User getUser(String userName) {
        logger.info("search for user : "+userName+" in the map");
        if(internalUserMap.containsKey(userName)){
            return internalUserMap.get(userName);
        }
        else {
            throw new UserNotFoundException(userName);
        }

    }

    /**
     * Method who add a visited location to a user based on his userName
     * @param userName username of the user for which we want to add a visitedLocation
     * @param lastVisitedLocation visitedLocation we want to add
     */
    public void addUserVisitedLocation(String userName, VisitedLocation lastVisitedLocation) {
        logger.info("add visited Location for user : "+userName);
        User user = internalUserMap.get(userName);
        if(user != null){
            user.getVisitedLocations().add(lastVisitedLocation);
        }
    }

    /**
     * Method that add a reward for a user based on his userName
     * @param userName name of the user for which we want to add an userReward
     * @param userRewardToAdd userReward that we want to add
     */
    public synchronized void addUserReward(String userName, UserReward userRewardToAdd) {
        logger.info("add userReward for user : "+userName);
        User user = internalUserMap.get(userName);
        user.getUserRewards().add(userRewardToAdd);
        internalUserMap.put(userName, user);
    }

    /**
     * Method who add tripDeals for a user based on his username
     * @param userName name of the user for which you want to add tripDeals
     * @param tripdeals tripdeals to add
     */
    public void addTripDeals(String userName,List<Provider> tripdeals){
        logger.info("add tripDeals for user : "+userName);
        User user = internalUserMap.get(userName);
        user.setTripDeals(tripdeals);
        internalUserMap.put(userName, user);
    }


    /**
     * Method who add user preferences for a user based on his username
     * @param userName name of the user for which you want to add user preferences
     * @param userPreferences user preferences to add
     */
    public void addUserPreferences(String userName, UserPreferences userPreferences){
        logger.info("add user preferences for user : "+userName);
        User user = internalUserMap.get(userName);
        user.setUserPreferences(userPreferences);
        internalUserMap.put(userName, user);
    }


    /**********************************************************************************
     *
     * Methods Below: To Generate Users
     *
     **********************************************************************************/

    private boolean testMode = true;
    private Map<String, User> internalUserMap = new HashMap<>();
    private Logger logger = LoggerFactory.getLogger(UserRepository.class);
    private static int internalUserNumber = 100000;

    /**
     * Method who initialize the demo users when launching the app
     */
    private void initializeInternalUsers() {
        IntStream.range(0, internalUserNumber).forEach(i -> {
            String userName = "internalUser" + i;
            String phone = "000";
            String email = userName + "@tourGuide.com";
            User user = new User(UUID.randomUUID(), userName, phone, email);
            generateUserLocationHistory(user);
            user.setUserPreferences(generateUserPreferences());
            internalUserMap.put(userName, user);
        });
        logger.debug("Created " + internalUserNumber + " internal test users.");
    }

    /**
     * Method who generate a random userLocation history for demo
     * @param user user for which we want to generate this location history
     */
    private void generateUserLocationHistory(User user) {
        IntStream.range(0, 3).forEach(i-> {
            user.addToVisitedLocations(new VisitedLocation(user.getUserId(), new Location(generateRandomLatitude(), generateRandomLongitude()), getRandomTime()));
        });
    }

    /**
     * Method who generate a random Longitude
     * @return a random longitude
     */
    private double generateRandomLongitude() {
        double leftLimit = -180;
        double rightLimit = 180;
        return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
    }

    /**
     * Method who generates a random Latitude
     * @return a random latitude
     */
    private double generateRandomLatitude() {
        double leftLimit = -85.05112878;
        double rightLimit = 85.05112878;
        return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
    }

    /**
     * Method who generates a random time
     * @return a random time
     */
    private Date getRandomTime() {
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(new Random().nextInt(30));
        return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
    }

    /**
     * Method who generates random userPreferences
     * @return random UserPreferences
     */
    private UserPreferences generateUserPreferences(){
        CurrencyUnit currency = Monetary.getCurrency("USD");
        int randomSmall = ThreadLocalRandom.current().nextInt(100, 5000);
        int randomBig = ThreadLocalRandom.current().nextInt(5001, 10000);
        int randomDuration = ThreadLocalRandom.current().nextInt(1, 20);
        int randomTicketQuantity = ThreadLocalRandom.current().nextInt(1, 20);
        int randomAdults = ThreadLocalRandom.current().nextInt(1, 8);
        int randomChildrens = ThreadLocalRandom.current().nextInt(1, 4);

       return new UserPreferences(Integer.MAX_VALUE,currency, Money.of(randomSmall,currency), Money.of(randomBig,currency),randomDuration,randomTicketQuantity,randomAdults,randomChildrens);
    }

    @PostConstruct
    void postConstruct() {
        if(testMode) {
            logger.info("TestMode enabled");
            logger.debug("Initializing users");
            initializeInternalUsers();
            logger.debug("Finished initializing users");
        }
    }
}
