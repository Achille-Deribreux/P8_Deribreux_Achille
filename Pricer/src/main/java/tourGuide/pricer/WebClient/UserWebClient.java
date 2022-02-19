package tourGuide.pricer.WebClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tourGuide.pricer.Entity.Provider;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class UserWebClient {
    private final Logger logger = LoggerFactory.getLogger(UserWebClient.class);

    //@Value("${tourguide.main.userurl}")
    private String BASE_URL_LOCALHOST_USER = "http://tourguide-users:8082/user";

    private final String USER_NAME = "?userName=";
    private final String PATH_ADD_USER_TRIPDEALS = "/addTripDeals";

    public void setBASE_URL_LOCALHOST_USER(String BASE_URL_LOCALHOST_USER) {
        this.BASE_URL_LOCALHOST_USER = BASE_URL_LOCALHOST_USER;
    }

    @Autowired
    RestTemplate restTemplate;

    /**
     * This method send the tripdeals to the user MS.
     * @param userName the user name of the tripdeals owner
     * @param tripDeals the tripdeals
     */
    public void addTripDeals(String userName, List<Provider> tripDeals) throws URISyntaxException {
        logger.info("add tripdeals for user : "+userName);
        RequestEntity< List<Provider>> request = RequestEntity
                .post(new URI(BASE_URL_LOCALHOST_USER+PATH_ADD_USER_TRIPDEALS+USER_NAME+userName))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(tripDeals);

        restTemplate.exchange(BASE_URL_LOCALHOST_USER+PATH_ADD_USER_TRIPDEALS+USER_NAME+userName,
                        HttpMethod.POST, request, void.class);
    }
}
