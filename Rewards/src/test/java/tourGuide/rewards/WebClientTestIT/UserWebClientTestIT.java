package tourGuide.rewards.WebClientTestIT;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import tourGuide.rewards.Entity.Location;
import tourGuide.rewards.Entity.UserReward;
import tourGuide.rewards.Entity.VisitedLocation;
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

    //@Value("${tourguide.main.userurl}")
    private String BASE_URL_LOCALHOST_USER  = "http://localhost:8082/user";
    private final String PATH_ADD_USER_REWARD = "/addUserReward";
    private final String USER_NAME = "?userName=";

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
