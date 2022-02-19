package tourGuide.user.WebClient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import tourGuide.user.Entity.VisitedLocation;
import tourGuide.user.Utils.Data;

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
    //Declare the path to userLocation
    private final String PATH_USER_LOCATION = "/getUserLocation";
    //Declare userId param
    private final String USER_ID = "?userId=";

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
