package tourGuide.WebClientIT;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import tourGuide.Entity.Provider;
import tourGuide.Utils.Data;
import tourGuide.WebClient.PricerWebClient;

import java.util.List;
import java.util.UUID;

@SpringBootTest
public class PricerWebClientTestIT {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    PricerWebClient pricerWebClient = new PricerWebClient();

    //@Value("${tourguide.main.pricerurl}")
    private String BASE_URL_LOCALHOST_pricer = "http://localhost:8084/pricer";
    // Declare the path to calculateRewards
    private final String PATH_GET_TRIPDEALS = "/getTripDeals";

    private final String PARAM_ATTRACTIONID = "?attractionId=";

    private final String PARAM_ADULTS = "&adults=";

    private final String PARAM_USERNAME = "&userName=";

    private final String PARAM_CHILDREN = "&children=";

    private final String PARAM_NIGHTSSTAY = "&nightsStay=";

    private final String PARAM_REWARDS_POINTS = "&rewardsPoints=";

    @BeforeEach
    void setUp() {
        pricerWebClient.setBASE_URL_LOCALHOST_pricer(BASE_URL_LOCALHOST_pricer);
    }

    @Test
    void generateTripDealsTestIT() {
        //Given
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        List<Provider> expected = Data.getProviderList();
        UUID attractionId = UUID.fromString("f9a2cb62-dee7-4851-a7ae-131f7b0eca93");
        String username = "internalUser1";
        int adults = 2;
        int children = 3;
        int nightsStay = 7;
        int rewardsPoints = 10;
        //When
        ResponseEntity<List<Provider>> result = testRestTemplate.exchange(
                BASE_URL_LOCALHOST_pricer+PATH_GET_TRIPDEALS+
                        PARAM_ATTRACTIONID+attractionId+
                        PARAM_USERNAME+username+
                        PARAM_ADULTS+adults+
                        PARAM_CHILDREN+children+
                        PARAM_NIGHTSSTAY+nightsStay+
                        PARAM_REWARDS_POINTS+rewardsPoints
                , HttpMethod.GET,null,new ParameterizedTypeReference<List<Provider>>() {});
        //Then
        Assertions.assertEquals(result.getStatusCode(), HttpStatus.OK);
    }
}
