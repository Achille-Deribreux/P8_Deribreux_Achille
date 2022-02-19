package tourGuide.WebClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.con.DTO.*;
import com.con.Entity.*;

import java.util.List;
import java.util.UUID;

@Service
public class GpsWebClient {

    @Autowired
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Declare the base url (for localhost)
    @Value("${tourguide.main.gpsurl}")
    private String BASE_URL_LOCALHOST_GPS;
    // Declare the path to attraction
    private final String PATH_NEARBY_ATTRACTIONS = "/getNearbyAttractions";
    //Declare the path to userLocation
    private final String PATH_USER_LOCATION = "/getUserLocation";
    //Declare userId param
    private final String USER_ID = "?userId=";
    //Declare latitute param
    private final String LATITUDE = "?latitude=";
    //Declare longitude param
    private final String LONGITUDE = "&longitude=";
    //Declare limit param
    private final String LIMIT = "&limit=";

    private Logger logger = LoggerFactory.getLogger(GpsWebClient.class);

    public void setBASE_URL_LOCALHOST_GPS(String BASE_URL_LOCALHOST_GPS) {
        this.BASE_URL_LOCALHOST_GPS = BASE_URL_LOCALHOST_GPS;
    }



    /**
     * Method who calls the gps app to find the nearby attractions from a user's position
     * @param latitude latitude of the position of the user
     * @param longitude longitude of the position of the user
     * @param limit the number of nearby attraction that we want
     * @return a list of NearbyAttractionsDTO, the size of the list is the limit param
     */
    public List<NearbyAttractionsDTO> getNearByAttractionsFromGps(double latitude, double longitude, Integer limit){
        logger.info("get nearby attractions from gps");
        ResponseEntity<List<NearbyAttractionsDTO>> result  =
                restTemplate.exchange(BASE_URL_LOCALHOST_GPS+PATH_NEARBY_ATTRACTIONS+LATITUDE+latitude+LONGITUDE+longitude+LIMIT+limit,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<NearbyAttractionsDTO>>() {});
        return result.getBody();
    }

    /**
     * Method who calls the gps app to track the user's location
     * @param userId id of the user from which we want the location
     * @return visited location, the current location of the user
     */
    public VisitedLocation trackUserLocationFromGps(UUID userId){
        ResponseEntity<VisitedLocation> result  =
                restTemplate.getForEntity(BASE_URL_LOCALHOST_GPS+PATH_USER_LOCATION+USER_ID+userId, VisitedLocation.class);
        return result.getBody();
    }
}
