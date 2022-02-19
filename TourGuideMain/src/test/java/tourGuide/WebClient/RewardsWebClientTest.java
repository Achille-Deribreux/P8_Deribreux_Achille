package tourGuide.WebClient;

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
import java.util.List;

@SpringBootTest
class RewardsWebClientTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    RewardsWebClient rewardsWebClient;

    @Value("${tourguide.main.rewardsurl}")
    private String BASE_URL_LOCALHOST_REWARDS;
    private final String PATH_CALCULATE_REWARDS = "/calculateRewards";

    @BeforeAll
    static void setUpBeforeAll() {
        System.setProperty("GPS_URL","http://localhost:8081/gps");
        System.setProperty("PRICER_URL","http://localhost:8084/pricer");
        System.setProperty("REWARD_URL","http://localhost:8083/rewards");
        System.setProperty("USER_URL","http://localhost:8082/user");
    }

    @BeforeEach
    void setUp() {
        rewardsWebClient.setBASE_URL_LOCALHOST_REWARDS(BASE_URL_LOCALHOST_REWARDS);
    }

    @Test
    void calculateRewardsTest() throws URISyntaxException {
        //Given
        User user = Data.getUser1();
        UserDTO userDTO = Mapper.convertUserToUserDTO(user);
        RequestEntity<UserDTO> request = RequestEntity
                .post(new URI(BASE_URL_LOCALHOST_REWARDS+PATH_CALCULATE_REWARDS))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userDTO);
        List<UserReward> expected = Data.getUser1().getUserRewards();
        //When
        Mockito.when(restTemplate.exchange(BASE_URL_LOCALHOST_REWARDS+PATH_CALCULATE_REWARDS, HttpMethod.POST,request,new ParameterizedTypeReference<List<UserReward>>(){})).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        rewardsWebClient.calculateRewards(user);
        //Then
        Mockito.verify(restTemplate,Mockito.times(1)).exchange(BASE_URL_LOCALHOST_REWARDS+PATH_CALCULATE_REWARDS, HttpMethod.POST,request,new ParameterizedTypeReference<List<UserReward>>(){});
    }
}
