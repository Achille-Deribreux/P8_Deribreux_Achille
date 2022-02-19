package tourGuide.WebClientIT;

import org.junit.jupiter.api.Assertions;
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
import tourGuide.DTO.NearbyAttractionsDTO;
import tourGuide.Entity.VisitedLocation;
import tourGuide.Utils.Data;
import tourGuide.WebClient.GpsWebClient;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class GpsWebClientTestIT {

    @InjectMocks
    GpsWebClient gpsWebClient = new GpsWebClient();

    // Declare the base url (for localhost)
    //@Value("${tourguide.main.gpsurl}")
    private String BASE_URL_LOCALHOST_GPS = "http://localhost:8081/gps";
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

    @BeforeEach
    void setUp() {
        gpsWebClient.setBASE_URL_LOCALHOST_GPS(BASE_URL_LOCALHOST_GPS);
    }

    @Test
    void getNearByAttractionsFromGpsTestIT() {
        //Given
        double latitude = 10;
        double longitude = 10;
        Integer limit = 5;
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        List<NearbyAttractionsDTO> expected = Data.getFiveNearestLocations();
        //When
        ResponseEntity<List<NearbyAttractionsDTO>> result = testRestTemplate.exchange(BASE_URL_LOCALHOST_GPS + PATH_NEARBY_ATTRACTIONS + LATITUDE + latitude + LONGITUDE + longitude + LIMIT + limit,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<NearbyAttractionsDTO>>() {
                });
        //Then
        Assertions.assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void trackUserLocationFromGpsTestIT() {
        //Given
        UUID userId = UUID.fromString("648ed5ea-b766-4aee-a0b7-3686e166c977");
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        //When
        ResponseEntity<VisitedLocation> response  = testRestTemplate.getForEntity(BASE_URL_LOCALHOST_GPS+PATH_USER_LOCATION+USER_ID+userId, VisitedLocation.class);
        //Then
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
