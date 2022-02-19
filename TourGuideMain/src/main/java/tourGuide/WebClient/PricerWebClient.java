package tourGuide.WebClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.con.DTO.*;
import com.con.Entity.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@Service
public class PricerWebClient {

    @Autowired
    private RestTemplate restTemplate;

    // Declare the base url (for localhost)
    @Value("${tourguide.main.pricerurl}")
    private String BASE_URL_LOCALHOST_PRICER;
    // Declare the path to calculateRewards
    private final String PATH_GET_TRIPDEALS = "/getTripDeals";

    private final String PARAM_ATTRACTIONID = "?attractionId=";

    private final String PARAM_ADULTS = "&adults=";

    private final String PARAM_USERNAME = "&userName=";

    private final String PARAM_CHILDREN = "&children=";

    private final String PARAM_NIGHTSSTAY = "&nightsStay=";

    private final String PARAM_REWARDS_POINTS = "&rewardsPoints=";

    private Logger logger = LoggerFactory.getLogger(PricerWebClient.class);

    public void setBASE_URL_LOCALHOST_PRICER(String BASE_URL_LOCALHOST_PRICER) {
        this.BASE_URL_LOCALHOST_PRICER = BASE_URL_LOCALHOST_PRICER;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * This method calls the pricer app to generate trip deals based on the user's preferences
     * @param attractionId id of the attraction
     * @param username name of the user
     * @param adults number of adults
     * @param children number of children
     * @param nightsStay nights that they like to stay
     * @param rewardsPoints sum of all the rewards points
     * @return a list of trip deals based on the user's preferences
     */
    public List<Provider> generateTripDeals(UUID attractionId,String username, int adults, int children, int nightsStay, int rewardsPoints) {
        logger.info("call the pricer app to generate tripdeals for user : "+username);
        ResponseEntity<List<Provider>> result = restTemplate.exchange(
                BASE_URL_LOCALHOST_PRICER+PATH_GET_TRIPDEALS+
                        PARAM_ATTRACTIONID+attractionId+
                        PARAM_USERNAME+username+
                        PARAM_ADULTS+adults+
                        PARAM_CHILDREN+children+
                        PARAM_NIGHTSSTAY+nightsStay+
                        PARAM_REWARDS_POINTS+rewardsPoints
                , HttpMethod.GET,null,new ParameterizedTypeReference<List<Provider>>() {});
        return result.getBody();
    }



}
