package tourGuide.gps.Controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import tourGuide.gps.Service.GpsService;
import tourGuide.gps.Utils.TestUtils;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GpsController.class)
class GpsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GpsService gpsService;

    @Test
    void getAllAttractionsTest() throws Exception {
        Mockito.when(gpsService.getAllAttractions()).thenReturn(TestUtils.getAttractions());
        mockMvc.perform(get("/gps/getAllAttractions")).andExpect(status().isOk());
    }

    @Test
    void calculateDistanceTest() throws Exception {
        mockMvc.perform(get("/gps/calculateDistance").param("lat1",String.valueOf(100)).param("lon1",String.valueOf(100)).param("lat2",String.valueOf(100)).param("lon2",String.valueOf(100))).andExpect(status().isOk());
    }

    @Test
    void getNearbyAttractionsTest() throws Exception {
        mockMvc.perform(get("/gps/getNearbyAttractions").param("latitude",String.valueOf(100)).param("longitude",String.valueOf(100)).param("limit",String.valueOf(5))).andExpect(status().isOk());
    }

    @Test
    void getUserLocationTest() throws Exception {
        mockMvc.perform(get("/gps/getUserLocation").param("userId",String.valueOf(UUID.randomUUID()))).andExpect(status().isOk());
    }
}
