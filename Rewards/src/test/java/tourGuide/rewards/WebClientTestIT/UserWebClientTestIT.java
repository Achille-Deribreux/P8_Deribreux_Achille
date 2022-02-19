package tourGuide.rewards.WebClientTestIT;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import com.con.Entity.*;
import tourGuide.rewards.TestUtils.Data;
import tourGuide.rewards.WebClient.UserWebClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@SpringBootTest
class UserWebClientTestIT {

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
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String userName = "internalUser1";
        UserReward userRewardToAdd = new UserReward(new VisitedLocation(UUID.randomUUID(),new Location(10.0,10.0), Date.from(Instant.now())), Data.getJacksonAttraction(),1);
        RequestEntity<UserReward> request = RequestEntity
                .post(new URI(BASE_URL_LOCALHOST_USER+PATH_ADD_USER_REWARD+USER_NAME+userName))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userRewardToAdd);
        //When
        ResponseEntity<String> response = testRestTemplate.exchange(BASE_URL_LOCALHOST_USER + PATH_ADD_USER_REWARD + USER_NAME + userName,
                HttpMethod.POST, request, String.class);
        //Then
        Assertions.assertEquals(response.getStatusCode(),HttpStatus.OK);
    }

}
