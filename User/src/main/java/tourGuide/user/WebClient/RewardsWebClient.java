package tourGuide.user.WebClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tourGuide.user.DTO.UserDTO;
import tourGuide.user.Entity.UserReward;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class RewardsWebClient {
    private final Logger logger = LoggerFactory.getLogger(RewardsWebClient.class);

    //@Value("${tourguide.main.rewardsurl}")
    private String BASE_URL_LOCALHOST_REWARDS = "http://tourguide-rewards:8083/rewards";
    // Declare the path to calculateRewards
    private final String PATH_CALCULATE_REWARDS = "/calculateRewards";

    @Autowired
    private RestTemplate restTemplate;

    public void setBASE_URL_LOCALHOST_REWARDS(String BASE_URL_LOCALHOST_REWARDS) {
        this.BASE_URL_LOCALHOST_REWARDS = BASE_URL_LOCALHOST_REWARDS;
    }

    /**
     * Method who calls the rewards app to calculate rewards for a user
     * @param userDTO user for which we want to calculate rewards
     * @return list of all the rewards of the user
     */
    public List<UserReward> calculateRewards(UserDTO userDTO) throws URISyntaxException {
        logger.info("send request to rewards app to calculate rewards for user :"+userDTO.getUserName());
        RequestEntity<UserDTO> request = RequestEntity
                .post(new URI(BASE_URL_LOCALHOST_REWARDS+PATH_CALCULATE_REWARDS))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userDTO);

        ResponseEntity<List<UserReward>> result = restTemplate.exchange(BASE_URL_LOCALHOST_REWARDS+PATH_CALCULATE_REWARDS, HttpMethod.POST,request,new ParameterizedTypeReference<List<UserReward>>() {});
        return result.getBody();
    }
}
 