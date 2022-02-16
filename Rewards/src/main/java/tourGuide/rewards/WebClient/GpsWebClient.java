package tourGuide.rewards.WebClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tourGuide.rewards.Controller.RewardsController;
import tourGuide.rewards.Entity.Attraction;
import tourGuide.rewards.Entity.User;

import java.util.List;

@Service
public class GpsWebClient {
    private Logger logger = LoggerFactory.getLogger(GpsWebClient.class);

    @Autowired
    private RestTemplate restTemplate;

    // Declare the base url (for localhost)
    //@Value("${tourguide.main.gpsurl}")
    private String BASE_URL_LOCALHOST_gps = "http://tourguide-gps:8081/gps";
    //Declare the path to getAllAttractions
    private final String PATH_GET_ALL_ATTRACTIONS = "/getAllAttractions";

    public void setBASE_URL_LOCALHOST_gps(String BASE_URL_LOCALHOST_gps) {
        this.BASE_URL_LOCALHOST_gps = BASE_URL_LOCALHOST_gps;
    }

    /**
     * This method get all the attractions from the gps app
     * @return List of all the attractions
     */
    public List<Attraction> getAllAttractions(){
        logger.info("get all the attractions from gps app");
        ResponseEntity<List<Attraction>> result  =
                restTemplate.exchange(BASE_URL_LOCALHOST_gps+PATH_GET_ALL_ATTRACTIONS,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Attraction>>() {
                        });
        return result.getBody();
    }
}
