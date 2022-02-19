package tourGuide.pricer.Controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import tourGuide.pricer.Entity.Provider;
import tourGuide.pricer.Service.PricerService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PricerController.class)
class PricerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PricerService pricerService;


    @Test
    void getAllAttractionsTest() throws Exception {
        //Given
        List<Provider> providerList = new ArrayList<>();
        String username = "usertestName";
        UUID uuid = UUID.randomUUID();
        int adults = 1;
        int children = 0;
        int nightstay = 7;
        int rewardspoints = 10;
        //When
        Mockito.when(pricerService.getTripDeals(username,uuid,adults,children,nightstay,rewardspoints)).thenReturn(providerList);
        //Then
        mockMvc.perform(get("/pricer/getTripDeals")
                .param("attractionId", String.valueOf(uuid))
                .param("userName",username)
                .param("adults", String.valueOf(adults))
                .param("children", String.valueOf(children))
                .param("nightsStay", String.valueOf(nightstay))
                .param("rewardsPoints", String.valueOf(rewardspoints)))
                .andExpect(status().isOk());
    }
}
