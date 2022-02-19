package tourGuide.user.WebClient;

import org.junit.jupiter.api.Assertions;
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
import tourGuide.user.DTO.UserDTO;
import tourGuide.user.Entity.User;
import tourGuide.user.Entity.UserReward;
import tourGuide.user.Utils.Data;
import tourGuide.user.Utils.Mapper;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@SpringBootTest
class RewardsWebClientTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    RewardsWebClient rewardsWebClient;

    //@Value("${tourguide.main.rewardsurl}")
    private String BASE_URL_LOCALHOST_REWARDS = "http://localhost:8083/rewards";

    private final String PATH_CALCULATE_REWARDS = "/calculateRewards";

    @BeforeEach
    void setUp() {
        rewardsWebClient.setBASE_URL_LOCALHOST_REWARDS(BASE_URL_LOCALHOST_REWARDS);
    }

    @Test
    void calculateRewardsTest() throws URISyntaxException {
        //Given
        User user = Data.getAUser();
        UserDTO userDTO = Mapper.userDTOMapper(user);
        RequestEntity<UserDTO> request = RequestEntity
                .post(new URI(BASE_URL_LOCALHOST_REWARDS+PATH_CALCULATE_REWARDS))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userDTO);
        List<UserReward> expected = Data.getAUser().getUserRewards();
        List<UserReward> result;
        //When
        Mockito.when(restTemplate.exchange(BASE_URL_LOCALHOST_REWARDS+PATH_CALCULATE_REWARDS, HttpMethod.POST,request,new ParameterizedTypeReference<List<UserReward>>(){})).thenReturn(new ResponseEntity<>(expected,HttpStatus.OK));
        result = rewardsWebClient.calculateRewards(userDTO);
        //Then
        Assertions.assertEquals(expected,result);
    }
}
