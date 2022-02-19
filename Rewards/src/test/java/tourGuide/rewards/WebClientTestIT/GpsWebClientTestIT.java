package tourGuide.rewards.WebClientTestIT;

import org.junit.jupiter.api.Assertions;
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

    //@Value("${tourguide.main.gpsurl}")
    private String BASE_URL_LOCALHOST_gps = "http://localhost:8081/gps";
    private final String PATH_GET_ALL_ATTRACTIONS = "/getAllAttractions";

    @BeforeEach
    void setUp() {
        gpsWebClient.setBASE_URL_LOCALHOST_gps(BASE_URL_LOCALHOST_gps);
    }

    @Test
    void getAllAttractions() {
        //Given
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        //When
        ResponseEntity<List<Attraction>> response = testRestTemplate.exchange(BASE_URL_LOCALHOST_gps + PATH_GET_ALL_ATTRACTIONS,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Attraction>>() {
                });
        //Then
        Assertions.assertEquals(response.getStatusCode(),HttpStatus.OK);
    }
}
