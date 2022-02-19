package tourGuide.pricer.Service;

import com.con.Entity.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tourGuide.pricer.Utils.Mapper;
import tourGuide.pricer.WebClient.UserWebClient;
import tripPricer.TripPricer;

import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@Service
public class PricerService {

    private Logger logger = LoggerFactory.getLogger(PricerService.class);

    // Database connection will be used for external users, but for testing purposes internal users are provided and stored in memory
    private static final String tripPricerApiKey = "test-server-api-key";

    private TripPricer tripPricer = new TripPricer();

    @Autowired
    private UserWebClient userWebClient;

    /**
     * Setter for tripPricer
     * @param tripPricer tripPricer to set
     */
    public void setTripPricer(TripPricer tripPricer) {
        this.tripPricer = tripPricer;
    }

    /**
     * Setter for userWebClient
     * @param userWebClient userWebClient to set
     */
    public void setUserWebClient(UserWebClient userWebClient) {
        this.userWebClient = userWebClient;
    }

    /**
     * Method who get tripdeals for a user from the tripPricer app
     * @param username username of the user
     * @param attractionId id of the attraction
     * @param adults number of adults from the userPreferences
     * @param children number of children from the userPreferences
     * @param nightsStay number of nightsStay from the userPreferences
     * @param rewardsPoints number of  earned rewardsPoints
     * @return A list of TripDeals (providers) adapted to the user
     */
    public List<Provider> getTripDeals(String username, UUID attractionId, int adults, int children, int nightsStay, int rewardsPoints) throws URISyntaxException {
        logger.info("generate tripdeals for user : "+username);
        List<Provider> tripDeals =  Mapper.mapToEntityProvider(tripPricer.getPrice(tripPricerApiKey, attractionId, adults,children,nightsStay, rewardsPoints));
        userWebClient.addTripDeals(username, tripDeals);
        return tripDeals;
    }
}