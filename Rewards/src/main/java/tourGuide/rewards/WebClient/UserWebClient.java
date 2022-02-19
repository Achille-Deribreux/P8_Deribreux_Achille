package tourGuide.rewards.WebClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.con.Entity.UserReward;


import java.net.URI;
import java.net.URISyntaxException;

@Service
public class UserWebClient {
    private Logger logger = LoggerFactory.getLogger(UserWebClient.class);

    @Value("${tourguide.main.userurl}")
    private String BASE_URL_LOCALHOST_USER;
    //Declare the path to addUserReward
    private final String PATH_ADD_USER_REWARD = "/addUserReward";
    //Declare userName param
    private final String USER_NAME = "?userName=";

    @Autowired
    private RestTemplate restTemplate;

    public void setBASE_URL_LOCALHOST_USER(String BASE_URL_LOCALHOST_USER) {
        this.BASE_URL_LOCALHOST_USER = BASE_URL_LOCALHOST_USER;
    }

    /**
     * This method send an userReward to the user app to add the reward to the user, in the list
     * @param userName the name of the user
     * @param userRewardToAdd the reward to add
     */
    public void addUserReward(String userName, UserReward userRewardToAdd) throws URISyntaxException {
        logger.info("add reward to user :"+userName);
        RequestEntity<UserReward> request = RequestEntity
                .post(new URI(BASE_URL_LOCALHOST_USER+PATH_ADD_USER_REWARD+USER_NAME+userName))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userRewardToAdd);

        ResponseEntity<Void> result  =
                restTemplate.exchange(BASE_URL_LOCALHOST_USER+PATH_ADD_USER_REWARD+USER_NAME+userName,
                        HttpMethod.POST, request, void.class);
    }
}
