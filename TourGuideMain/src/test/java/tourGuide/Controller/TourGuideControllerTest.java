package tourGuide.Controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tourGuide.DTO.UserPreferencesDTO;
import tourGuide.Entity.VisitedLocation;
import tourGuide.Utils.Converter;
import tourGuide.WebClient.UserWebClient;
import tourGuide.service.TourGuideService;

import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TourGuideController.class)
public class TourGuideControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TourGuideService tourGuideService;

    @MockBean
    UserWebClient userWebClient;

    @Test
    public void indexTest() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    public void getLocation() throws Exception {
        //Given
        String userName = "username";
        //When
        Mockito.when(tourGuideService.getLocation(userName)).thenReturn(new VisitedLocation());
        //Then
        mockMvc.perform(get("/getLocation").param("userName", userName)).andExpect(status().isOk());
    }

    @Test
    void getNearbyAttractionsTest() throws Exception {
        //Given
        String userName = "username";
        //When
        Mockito.when(tourGuideService.getNearByAttractions(userName)).thenReturn(new ArrayList<>());
        //Then
        mockMvc.perform(get("/getNearbyAttractions").param("userName", userName)).andExpect(status().isOk());
    }

    @Test
    void getRewardsTest() throws Exception {
        //Given
        String userName = "username";
        //When
        Mockito.when(tourGuideService.getUserRewards(userName)).thenReturn(new ArrayList<>());
        //Then
        mockMvc.perform(get("/getRewards").param("userName", userName)).andExpect(status().isOk());
    }

    @Test
    void getAllCurrentLocationsTest() throws Exception {
        //Given
        //When
        Mockito.when(tourGuideService.getAllUsersWithLocation()).thenReturn(new HashMap<>());
        //Then
        mockMvc.perform(get("/getAllCurrentLocations")).andExpect(status().isOk());
    }

    @Test
    void getTripDealsTest() throws Exception {
        //Given
        String userName = "username";
        //When
        Mockito.when(tourGuideService.getTripDeals(userName)).thenReturn(new ArrayList<>());
        //Then
        mockMvc.perform(get("/getTripDeals").param("userName", userName)).andExpect(status().isOk());
    }

    @Test
    void addUserPreferencesTest() throws Exception {
        //Given
        String userName = "username";
        UserPreferencesDTO userPreferencesDTO = new UserPreferencesDTO();
        //When
        doNothing().when(tourGuideService).addUserPreferences(userName,userPreferencesDTO);
        //Then
        mockMvc.perform(post("/addUserPreferences").param("userName",userName).contentType(MediaType.APPLICATION_JSON).content(Converter.asJsonString(userPreferencesDTO))).andExpect(status().isOk());
    }
}
