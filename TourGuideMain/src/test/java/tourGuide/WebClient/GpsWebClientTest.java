package tourGuide.WebClient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.con.DTO.*;
import com.con.Entity.*;
import tourGuide.Utils.Data;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class GpsWebClientTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    GpsWebClient gpsWebClient = new GpsWebClient();

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

    @BeforeAll
    static void setUpBeforeAll() {
        System.setProperty("GPS_URL","http://localhost:8081/gps");
        System.setProperty("PRICER_URL","http://localhost:8084/pricer");
        System.setProperty("REWARD_URL","http://localhost:8083/rewards");
        System.setProperty("USER_URL","http://localhost:8082/user");
    }

    @BeforeEach
    void setUp() {
        gpsWebClient.setBASE_URL_LOCALHOST_GPS(BASE_URL_LOCALHOST_GPS);
    }

    @Test
    void getNearByAttractionsFromGpsTest() {
        //Given
        double latitude = 10;
        double longitude =10;
        Integer limit = 5;
        List<NearbyAttractionsDTO> expected = Data.getFiveNearestLocations();
        List<NearbyAttractionsDTO> result;
        //When
        Mockito.when( restTemplate.exchange(BASE_URL_LOCALHOST_GPS+PATH_NEARBY_ATTRACTIONS+LATITUDE+latitude+LONGITUDE+longitude+LIMIT+limit,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<NearbyAttractionsDTO>>() {
                })).thenReturn(new ResponseEntity<>(expected, HttpStatus.OK));
        result = gpsWebClient.getNearByAttractionsFromGps(latitude,longitude,limit);
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void trackUserLocationFromGpsTest() {
        //Given
        UUID userId = UUID.fromString("648ed5ea-b766-4aee-a0b7-3686e166c977");
        VisitedLocation expected = Data.getLastVisitedLocationOfUser();
        VisitedLocation result;
        //When
        Mockito.when(restTemplate.getForEntity(BASE_URL_LOCALHOST_GPS+PATH_USER_LOCATION+USER_ID+userId, VisitedLocation.class)).thenReturn(new ResponseEntity<>(expected, HttpStatus.OK));
        result = gpsWebClient.trackUserLocationFromGps(userId);
        //Then
        Assertions.assertEquals(expected,result);
    }
}
