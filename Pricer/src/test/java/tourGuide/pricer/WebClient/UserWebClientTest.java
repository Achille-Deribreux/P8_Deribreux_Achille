package tourGuide.pricer.WebClient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import tourGuide.pricer.Entity.Provider;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserWebClientTest {

    @Mock
    private RestTemplate restTemplate;

    //@Value("${tourguide.main.userurl}")
    private String BASE_URL_LOCALHOST_user  = "http://localhost:8082/user";

    private final String USER_NAME = "?userName=";
    private final String PATH_ADD_USER_TRIPDEALS = "/addTripDeals";

    @InjectMocks
    UserWebClient userWebClient = new UserWebClient();

    @BeforeEach
    void setUp() {
        userWebClient.setBASE_URL_LOCALHOST_user(BASE_URL_LOCALHOST_user);
    }

    @Test
    void addTripDealsTest() throws URISyntaxException {
        //Given
        List<Provider> tripDeals = new ArrayList<>();
        String userName = "username";
        RequestEntity< List<Provider>> request = RequestEntity
                .post(new URI(BASE_URL_LOCALHOST_user+PATH_ADD_USER_TRIPDEALS+USER_NAME+userName))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(tripDeals);
        //When
        Mockito.when(restTemplate.exchange(BASE_URL_LOCALHOST_user+PATH_ADD_USER_TRIPDEALS+USER_NAME+userName,
                HttpMethod.POST, request, void.class)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        userWebClient.addTripDeals(userName,tripDeals);
        //Then
        Mockito.verify(restTemplate,Mockito.times(1)).exchange(BASE_URL_LOCALHOST_user+PATH_ADD_USER_TRIPDEALS+USER_NAME+userName,
                HttpMethod.POST, request, void.class);
    }
}
