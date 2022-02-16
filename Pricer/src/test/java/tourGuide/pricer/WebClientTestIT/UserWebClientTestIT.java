package tourGuide.pricer.WebClientTestIT;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import tourGuide.pricer.Entity.Provider;
import tourGuide.pricer.WebClient.UserWebClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserWebClientTestIT {

    //@Value("${tourguide.main.userurl}")
    private String BASE_URL_LOCALHOST_user  = "http://localhost:8082/user";

    private final String USER_NAME = "?userName=";
    private final String PATH_ADD_USER_TRIPDEALS = "/addTripDeals";

    UserWebClient userWebClient = new UserWebClient();

    @BeforeEach
    void setUp() {
        userWebClient.setBASE_URL_LOCALHOST_user(BASE_URL_LOCALHOST_user);
    }

    @Test
    void addTripDealsTest() throws URISyntaxException {
        //Given
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        List<Provider> tripDeals = new ArrayList<>();
        String userName = "internalUser1";
        RequestEntity< List<Provider>> request = RequestEntity
                .post(new URI(BASE_URL_LOCALHOST_user+PATH_ADD_USER_TRIPDEALS+USER_NAME+userName))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(tripDeals);
        //When
        ResponseEntity<Void> response = testRestTemplate.exchange(BASE_URL_LOCALHOST_user + PATH_ADD_USER_TRIPDEALS + USER_NAME + userName,
                HttpMethod.POST, request, void.class);
        //Then
        Assertions.assertEquals(response.getStatusCode(),HttpStatus.OK);
    }
}
