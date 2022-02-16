package tourGuide.WebClientIT;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import tourGuide.DTO.UserDTO;
import tourGuide.Entity.User;
import tourGuide.Entity.UserReward;
import tourGuide.Utils.Data;
import tourGuide.Utils.Mapper;
import tourGuide.WebClient.RewardsWebClient;
import tourGuide.WebClient.UserWebClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@SpringBootTest
public class RewardWebClientTestIT {

    @Autowired
    RewardsWebClient rewardsWebClient;

    @Autowired
    UserWebClient userWebClient;

    //@Value("${tourguide.main.rewardsurl}")
    private String BASE_URL_LOCALHOST_rewards = "http://localhost:8083/rewards";
    //@Value("${tourguide.main.userurl}")
    private String BASE_URL_LOCALHOST_user  = "http://localhost:8082/user";
    private final String PATH_CALCULATE_REWARDS = "/calculateRewards";

    @BeforeEach
    void setUp() {
        rewardsWebClient.setBASE_URL_LOCALHOST_rewards(BASE_URL_LOCALHOST_rewards);
        userWebClient.setBASE_URL_LOCALHOST_user(BASE_URL_LOCALHOST_user);
    }

    @Test
    void calculateRewardsTest() throws URISyntaxException {
        //Given
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        User user = userWebClient.getUser("internalUser1");
        UserDTO userDTO = Mapper.convertUserToUserDTO(user);
        RequestEntity<UserDTO> request = RequestEntity
                .post(new URI(BASE_URL_LOCALHOST_rewards+PATH_CALCULATE_REWARDS))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userDTO);
        //When
        ResponseEntity<List<UserReward>> response = testRestTemplate.exchange(BASE_URL_LOCALHOST_rewards + PATH_CALCULATE_REWARDS, HttpMethod.POST, request, new ParameterizedTypeReference<List<UserReward>>() {
        });
        //Then
        Assertions.assertEquals(response.getStatusCode(),HttpStatus.OK);
    }
    
}
