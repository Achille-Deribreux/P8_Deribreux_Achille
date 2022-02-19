package tourGuide.WebClientIT;

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
import org.springframework.http.*;
import com.con.DTO.*;
import com.con.Entity.*;
import tourGuide.Utils.Data;
import tourGuide.WebClient.UserWebClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
class UserWebClientTestIT {

    @InjectMocks
    UserWebClient userWebClient;

    @Value("${tourguide.main.userurl}")
    private String BASE_URL_LOCALHOST_USER;
    private final String PATH_USER_LOCATION = "/getUserLocation";
    private final String PATH_GET_USER = "/getUser";
    private final String PATH_GET_ALL_USERS = "/getAllUsers";
    private final String PATH_ADD_USER_PREFERENCES = "/addUserPreferences";
    private final String PATH_GET_ALL_USERS_WITH_LOCATION = "/getAllUsersWithLocation";
    private final String PATH_ADD_USER_VISITED_LOCATION = "/addUserVisitedLocation";
    private final String USER_NAME = "?userName=";

    @BeforeAll
    static void setUpBeforeAll() {
        System.setProperty("GPS_URL","http://localhost:8081/gps");
        System.setProperty("PRICER_URL","http://localhost:8084/pricer");
        System.setProperty("REWARD_URL","http://localhost:8083/rewards");
        System.setProperty("USER_URL","http://localhost:8082/user");
    }

    @BeforeEach
    void setUp() {
        userWebClient.setBASE_URL_LOCALHOST_USER(BASE_URL_LOCALHOST_USER);
    }

    @Test
    void getUserLocationFromUserTest() {
        //Given
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String userName = "internalUser1";
        //When
        ResponseEntity<VisitedLocation> response = testRestTemplate.getForEntity(BASE_URL_LOCALHOST_USER + PATH_USER_LOCATION + USER_NAME + userName, VisitedLocation.class);
        //Then
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void getUserTestIT() {
        //Given
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String userName = "internalUser1";
        //When
        ResponseEntity<UserDTO> response = testRestTemplate.getForEntity(BASE_URL_LOCALHOST_USER + PATH_GET_USER + USER_NAME + userName, UserDTO.class);
        //Then
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void getAllUsersWithLocationTest() {
        //Given
        Map<UUID, Location> expected =  Data.getAllUsersWithLocation();
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        //When
        ResponseEntity<Map<UUID, Location>> response = testRestTemplate.exchange(BASE_URL_LOCALHOST_USER + PATH_GET_ALL_USERS_WITH_LOCATION,
                HttpMethod.GET, null, new ParameterizedTypeReference<Map<UUID, Location>>() {
                });
        //Then
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void addUserVisitedLocationTest() throws URISyntaxException {
        //Given
        String userName = "internalUser1";
        VisitedLocation lastVisitedLocation = Data.getLastVisitedLocationOfUser();
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        RequestEntity<VisitedLocation> request = RequestEntity
                .post(new URI(BASE_URL_LOCALHOST_USER+PATH_ADD_USER_VISITED_LOCATION+USER_NAME+userName))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(lastVisitedLocation);
        //When
        ResponseEntity<String> response = testRestTemplate.exchange(BASE_URL_LOCALHOST_USER + PATH_ADD_USER_VISITED_LOCATION + USER_NAME + userName,
                HttpMethod.POST, request, String.class);
        //Then
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void addUserPreferencesTest() throws URISyntaxException {
        //Given
        String userName = "internalUser1";
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        UserPreferencesDTO userPreferencesDTO = new UserPreferencesDTO();
        RequestEntity<UserPreferencesDTO> request = RequestEntity
                .post(new URI(BASE_URL_LOCALHOST_USER+PATH_ADD_USER_PREFERENCES+USER_NAME+userName))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userPreferencesDTO);
        //When
        ResponseEntity<String> response = testRestTemplate.exchange(BASE_URL_LOCALHOST_USER + PATH_ADD_USER_PREFERENCES + USER_NAME + userName,
                HttpMethod.POST, request, String.class);
        //Then
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    
    
}
