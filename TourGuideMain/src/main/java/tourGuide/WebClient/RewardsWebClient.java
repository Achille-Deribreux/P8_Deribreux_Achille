package tourGuide.WebClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.con.DTO.*;
import com.con.Entity.*;
import tourGuide.Utils.Mapper;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class RewardsWebClient {

    @Autowired
    private RestTemplate restTemplate = new RestTemplate();

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${tourguide.main.rewardsurl}")
    private String BASE_URL_LOCALHOST_REWARDS;
    // Declare the path to calculateRewards
    private final String PATH_CALCULATE_REWARDS = "/calculateRewards";

    private final Logger logger = LoggerFactory.getLogger(RewardsWebClient.class);

    public void setBASE_URL_LOCALHOST_REWARDS(String BASE_URL_LOCALHOST_REWARDS) {
        this.BASE_URL_LOCALHOST_REWARDS = BASE_URL_LOCALHOST_REWARDS;
    }

    /**
     * method which calls the reward app to calculate rewards for a given user
     * @param user user for which we want the rewards
     */
    public void calculateRewards(User user) throws URISyntaxException {
        logger.info("call rewards app to calculate rewards for user : "+user.getUserName());
        UserDTO userDTO = Mapper.convertUserToUserDTO(user);
        RequestEntity<UserDTO> request = RequestEntity
                .post(new URI(BASE_URL_LOCALHOST_REWARDS+PATH_CALCULATE_REWARDS))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userDTO);

        restTemplate.exchange(BASE_URL_LOCALHOST_REWARDS+PATH_CALCULATE_REWARDS, HttpMethod.POST,request,new ParameterizedTypeReference<List<UserReward>>() {});
        //return result.getBody();
    }
}
