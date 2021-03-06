package tourGuide.rewards.WebClient;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import com.con.Entity.UserReward;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserWebClientTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    UserWebClient userWebClient = new UserWebClient();

    @Value("${tourguide.main.userurl}")
    private String BASE_URL_LOCALHOST_USER;


    private final String PATH_ADD_USER_REWARD = "/addUserReward";
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
    void addUserRewardTest() throws URISyntaxException {
        //Given
        String userName = "username";
        UserReward userRewardToAdd = new UserReward();
        RequestEntity<UserReward> request = RequestEntity
                .post(new URI(BASE_URL_LOCALHOST_USER+PATH_ADD_USER_REWARD+USER_NAME+userName))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userRewardToAdd);
        //When
        Mockito.when(restTemplate.exchange(BASE_URL_LOCALHOST_USER+PATH_ADD_USER_REWARD+USER_NAME+userName,
                HttpMethod.POST, request, void.class)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        userWebClient.addUserReward(userName,userRewardToAdd);
        //Then
        Mockito.verify(restTemplate,Mockito.times(1)).exchange(BASE_URL_LOCALHOST_USER+PATH_ADD_USER_REWARD+USER_NAME+userName,
                HttpMethod.POST, request, void.class);
    }
}

