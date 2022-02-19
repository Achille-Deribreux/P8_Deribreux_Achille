package tourGuide.user.WebClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tourGuide.user.Entity.VisitedLocation;

import java.util.UUID;

@Service
public class GpsWebClient {

    @Autowired
    private RestTemplate restTemplate;

    private final Logger logger = LoggerFactory.getLogger(GpsWebClient.class);

    @Value("${tourguide.main.gpsurl}")
    private String BASE_URL_LOCALHOST_GPS;
    //Declare the path to userLocation
    private final String PATH_USER_LOCATION = "/getUserLocation";
    //Declare userId param
    private final String USER_ID = "?userId=";

    public void setBASE_URL_LOCALHOST_GPS(String BASE_URL_LOCALHOST_GPS) {
        this.BASE_URL_LOCALHOST_GPS = BASE_URL_LOCALHOST_GPS;
    }

    /**
     * Method which calls the gps app to track a user
     * @param userId id of the user from which we want the location
     * @return the location of the user
     */
    public VisitedLocation trackUserLocationFromGps(UUID userId){
        logger.info("send request to gps app to track location of user with id : "+userId);
        ResponseEntity<VisitedLocation> result  =
                restTemplate.getForEntity(BASE_URL_LOCALHOST_GPS+PATH_USER_LOCATION+USER_ID+userId, VisitedLocation.class);
        return result.getBody();
    }
}
