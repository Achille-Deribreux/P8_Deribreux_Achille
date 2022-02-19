package tourGuide.user.Controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tourGuide.user.DTO.UserPreferencesDTO;
import tourGuide.user.Entity.*;
import tourGuide.user.Service.UserService;
import tourGuide.user.Utils.Converter;
import tourGuide.user.Utils.Data;
import tourGuide.user.Utils.Mapper;

import java.net.URISyntaxException;
import java.util.*;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    void getUserLocationTest() throws Exception {
        //Given
        String username = "username";
        //When
        Mockito.when(userService.getUserLocation(username)).thenReturn(new VisitedLocation());
        //Then
        mockMvc.perform(get("/user/getUserLocation").param("userName", username)).andExpect(status().isOk());
    }

    @Test
    void getUserTest() throws Exception {
        //Given
        User user = Data.getUser1();
        String username = user.getUserName();
        //When
        Mockito.when(userService.getUser(username)).thenReturn(user);
        //Then
        mockMvc.perform(get("/user/getUser").param("userName", username)).andExpect(status().isOk());
    }


    @Test
    void getAllUsersTest() throws Exception {
        //Given
        //When
        Mockito.when(userService.getAllUsers()).thenReturn(new ArrayList<>());
        //Then
        mockMvc.perform(get("/user/getAllUsers")).andExpect(status().isOk());
    }

    @Test
    void getAllUsersWithLocationTest() throws Exception {
        //Given
        //When
        Mockito.when(userService.getAllUsersWithLocation()).thenReturn(new HashMap<>());
        //Then
        mockMvc.perform(get("/user/getAllUsersWithLocation")).andExpect(status().isOk());
    }

    @Test
    void addUserVisitedLocationTest() throws Exception {
        //Given
        String userName = "username";
        UUID userId = UUID.fromString("648ed5ea-b766-4aee-a0b7-3686e166c977");
        VisitedLocation visitedLocation = new VisitedLocation(userId,new Location(10,10),null);
        //When
        doNothing().when(userService).addUserVisitedLocation(userName,visitedLocation);
        //Then
        mockMvc.perform(post("/user/addUserVisitedLocation").param("userName",userName).contentType(MediaType.APPLICATION_JSON).content(Converter.asJsonString(visitedLocation))).andExpect(status().isOk());
    }

    @Test
    void addUserRewardTest() throws Exception {
        //Given
        String userName = "username";
        UUID userId = UUID.fromString("648ed5ea-b766-4aee-a0b7-3686e166c977");
        UserReward userReward = new UserReward();
        //When
        doNothing().when(userService).addUserReward(userName,userReward);
        //Then
        mockMvc.perform(post("/user/addUserReward").param("userName",userName).contentType(MediaType.APPLICATION_JSON).content(Converter.asJsonString(userReward))).andExpect(status().isOk());
    }

    @Test
    void addUserTripDealsTest() throws Exception {
        //Given
        String userName = "username";
        UUID userId = UUID.fromString("648ed5ea-b766-4aee-a0b7-3686e166c977");
        List<Provider> providerList = new ArrayList<>();
        //When
        doNothing().when(userService).addTripDeals(userName,providerList);
        //Then
        mockMvc.perform(post("/user/addTripDeals").param("userName",userName).contentType(MediaType.APPLICATION_JSON).content(Converter.asJsonString(providerList))).andExpect(status().isOk());
    }

    @Test
    void addUserPreferencesTest() throws Exception {
        //Given
        String userName = "username";
        UUID userId = UUID.fromString("648ed5ea-b766-4aee-a0b7-3686e166c977");
        UserPreferencesDTO userPreferences = new UserPreferencesDTO();
        //When
        doNothing().when(userService).addUserPreferences(userName, Mapper.userPreferencesDTOMapper(userPreferences));
        //Then
        mockMvc.perform(post("/user/addUserPreferences").param("userName",userName).contentType(MediaType.APPLICATION_JSON).content(Converter.asJsonString(userPreferences))).andExpect(status().isOk());
    }
}
