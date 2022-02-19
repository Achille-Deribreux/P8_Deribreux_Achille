package tourGuide.rewards.WebClient;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import tourGuide.rewards.Entity.Attraction;
import tourGuide.rewards.TestUtils.Data;

import java.util.List;

@SpringBootTest
class GpsWebClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
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
        List<Attraction> expected = Data.getAttractionList();
        List<Attraction> result;
        //When
        Mockito.when(restTemplate.exchange(BASE_URL_LOCALHOST_GPS+PATH_GET_ALL_ATTRACTIONS,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Attraction>>() {
                })).thenReturn(new ResponseEntity<>(expected,HttpStatus.OK));
        result = gpsWebClient.getAllAttractions();
        //Then
        Assertions.assertEquals(expected,result);
    }
}
