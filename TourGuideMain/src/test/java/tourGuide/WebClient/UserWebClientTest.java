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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import com.con.DTO.*;
import com.con.Entity.*;
import tourGuide.Utils.Data;
import tourGuide.Utils.Mapper;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
class UserWebClientTest {

    @Mock
    RestTemplate restTemplate;

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
        userWebClient.setRestTemplate(restTemplate);
        userWebClient.setBASE_URL_LOCALHOST_USER(BASE_URL_LOCALHOST_USER);
    }

    @Test
    void getUserLocationFromUserTest() {
        //Given
        User user = Data.getUser1();
        VisitedLocation expected = Data.getLastVisitedLocationOfUser();
        VisitedLocation result;
        //When
        Mockito.when(restTemplate.getForEntity(BASE_URL_LOCALHOST_USER+PATH_USER_LOCATION+USER_NAME+user.getUserName(), VisitedLocation.class)).thenReturn(new ResponseEntity<>(expected, HttpStatus.OK));
        result = userWebClient.getUserLocationFromUser(user.getUserName());
        //Then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void getUserTest() {
        //Given
        User expected = Data.getUser1();
        UserDTO userDTO = Mapper.convertUserToUserDTO(expected);
        User result;
        //When
        Mockito.when(restTemplate.getForEntity(BASE_URL_LOCALHOST_USER+PATH_GET_USER+USER_NAME+expected.getUserName(), UserDTO.class)).thenReturn(new ResponseEntity<>(userDTO,HttpStatus.OK));
        result = userWebClient.getUser(expected.getUserName());
        //Then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void getAllUsersTest() {
        //Given
        List<UserDTO> userDTOList = Data.getAllUsersDTO();
        List<User> expected = Data.getAllUsers();
        List<User> result;
        //When
        Mockito.when(restTemplate.exchange(BASE_URL_LOCALHOST_USER+PATH_GET_ALL_USERS,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<UserDTO>>() {
                })).thenReturn(new ResponseEntity<>(userDTOList,HttpStatus.OK));
        result = userWebClient.getAllUsers();
        //Then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void getAllUsersWithLocationTest() {
        //Given
        Map<UUID, Location> expected =  Data.getAllUsersWithLocation();
        Map<UUID, Location> result;
        //When
        Mockito.when( restTemplate.exchange(BASE_URL_LOCALHOST_USER+PATH_GET_ALL_USERS_WITH_LOCATION,
                HttpMethod.GET, null, new ParameterizedTypeReference<Map<UUID, Location>>() {
                }))
                .thenReturn(new ResponseEntity<>(expected,HttpStatus.OK));
        result = userWebClient.getAllUsersWithLocation();
        //Then
        Assertions.assertEquals(expected, result);
    }


    @Test
    void addUserVisitedLocationTest() throws URISyntaxException {
        //Given
        String userName = "anUserName";
        VisitedLocation lastVisitedLocation = Data.getLastVisitedLocationOfUser();
        RequestEntity<VisitedLocation> request = RequestEntity
                .post(new URI(BASE_URL_LOCALHOST_USER+PATH_ADD_USER_VISITED_LOCATION+USER_NAME+userName))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(lastVisitedLocation);
        //When
        Mockito.when(restTemplate.exchange(BASE_URL_LOCALHOST_USER+PATH_ADD_USER_VISITED_LOCATION+USER_NAME+userName,
                HttpMethod.POST, request, String.class)).thenReturn(new ResponseEntity<>("string",HttpStatus.OK));
        userWebClient.addUserVisitedLocation(userName,lastVisitedLocation);
        //Then
        Mockito.verify(restTemplate,Mockito.times(1)).exchange(BASE_URL_LOCALHOST_USER+PATH_ADD_USER_VISITED_LOCATION+USER_NAME+userName,
                HttpMethod.POST, request, String.class);
    }

    @Test
    void addUserPreferencesTest() throws URISyntaxException {
        //Given
        String userName = "anUserName";
        UserPreferencesDTO userPreferencesDTO = new UserPreferencesDTO();
        RequestEntity<UserPreferencesDTO> request = RequestEntity
                .post(new URI(BASE_URL_LOCALHOST_USER+PATH_ADD_USER_PREFERENCES+USER_NAME+userName))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userPreferencesDTO);
        //When
        Mockito.when(restTemplate.exchange(BASE_URL_LOCALHOST_USER+PATH_ADD_USER_PREFERENCES+USER_NAME+userName,
                HttpMethod.POST, request, String.class)).thenReturn(new ResponseEntity<>("string",HttpStatus.OK));
        userWebClient.addUserPreferences(userName,userPreferencesDTO);
        //Then
        Mockito.verify(restTemplate,Mockito.times(1)).exchange(BASE_URL_LOCALHOST_USER+PATH_ADD_USER_PREFERENCES+USER_NAME+userName,
                HttpMethod.POST, request, String.class);;
    }
}
