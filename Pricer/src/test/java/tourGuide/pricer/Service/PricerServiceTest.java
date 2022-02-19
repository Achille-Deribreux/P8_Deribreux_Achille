package tourGuide.pricer.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tourGuide.pricer.Entity.Provider;
import tourGuide.pricer.WebClient.UserWebClient;
import tripPricer.TripPricer;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.doNothing;

@SpringBootTest
class PricerServiceTest {

    @Autowired
    PricerService pricerService;

    @MockBean
    TripPricer tripPricer;

    @MockBean
    UserWebClient userWebClient;

    @BeforeEach
    void setUp() {
        pricerService.setTripPricer(tripPricer);
        pricerService.setUserWebClient(userWebClient);
    }

    @Test
    void getTripDealsCallTest() throws URISyntaxException {
        //Given
        String tripPricerApiKey = "test-server-api-key";
        String username = "usertestName";
        UUID uuid = UUID.randomUUID();
        int adults = 1;
        int children = 0;
        int nightstay = 7;
        int rewardspoints = 10;
        List<Provider> tripDeals = new ArrayList<>();
        //When
        Mockito.when(tripPricer.getPrice(tripPricerApiKey,uuid,adults,children,nightstay,rewardspoints)).thenReturn(new ArrayList<>());
        doNothing().when(userWebClient).addTripDeals(username,tripDeals);

        pricerService.getTripDeals(username,uuid,adults,children,nightstay,rewardspoints);
        //Then
        Mockito.verify(tripPricer,Mockito.times(1)).getPrice(tripPricerApiKey,uuid,adults,children,nightstay,rewardspoints);
        Mockito.verify(userWebClient,Mockito.times(1)).addTripDeals(username,tripDeals);
    }
}
