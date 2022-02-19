package tourGuide.rewards.WebClientTestIT;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tourGuide.rewards.Entity.Attraction;
import tourGuide.rewards.TestUtils.Data;
import tourGuide.rewards.WebClient.GpsWebClient;

import java.util.List;

@SpringBootTest
class GpsWebClientTestIT {

    GpsWebClient gpsWebClient = new GpsWebClient();

    @Value("${tourguide.main.gpsurl}")
    private String BASE_URL_LOCALHOST_GPS;
    private final String PATH_GET_ALL_ATTRACTIONS = "/getAllAttractions";

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
    void getAllAttractions() {
        //Given
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        //When
        ResponseEntity<List<Attraction>> response = testRestTemplate.exchange(BASE_URL_LOCALHOST_GPS + PATH_GET_ALL_ATTRACTIONS,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Attraction>>() {
                });
        //Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
