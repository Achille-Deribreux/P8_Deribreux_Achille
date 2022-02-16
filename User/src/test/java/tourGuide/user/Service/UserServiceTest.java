package tourGuide.user.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tourGuide.user.Entity.*;
import tourGuide.user.Repository.UserRepository;
import tourGuide.user.Utils.Data;
import tourGuide.user.Utils.Mapper;
import tourGuide.user.WebClient.GpsWebClient;
import tourGuide.user.WebClient.RewardsWebClient;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.mockito.Mockito.doNothing;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    RewardsWebClient rewardsWebClient;

    @MockBean
    GpsWebClient gpsWebClient;

    @BeforeEach
    void setUp() {
        userService.setUserRepository(userRepository);
        userService.setRewardsWebClient(rewardsWebClient);
        userService.setGpsWebClient(gpsWebClient);
    }

    @Test
    void getAllUsers() {
        //When
        userService.getAllUsers();
        //Then
        Mockito.verify(userRepository,Mockito.times(1)).findAllUsers();
    }

    @Test
    void addTripDealsTest() {
        //Given
        String userName = "username";
        List<Provider> tripdeals = new ArrayList<>();
        //When
        userService.addTripDeals(userName,tripdeals);
        //Then
        Mockito.verify(userRepository,Mockito.times(1)).addTripDeals(userName,tripdeals);
    }

    @Test
    void getUserTest() {
        //Given
        String userName = "username";
        //When
        userService.getUser(userName);
        //Then
        Mockito.verify(userRepository,Mockito.times(1)).getUser(userName);
    }

    @Test
    void getUserLocation() throws URISyntaxException {
        //Given
        User user = Data.getAUser();
        VisitedLocation expected = Data.getLastVisitedLocationOfUser();
        VisitedLocation result;
        //When
        Mockito.when(userRepository.getUser(user.getUserName())).thenReturn(user);
        result = userService.getUserLocation(user.getUserName());
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void trackUserLocationTest() throws URISyntaxException {
        //Given
        User user = Data.getAUser();
        VisitedLocation expected = new VisitedLocation(user.getUserId(),new Location(10,10),null);
        List<UserReward> userRewardsList = new ArrayList<>();
        VisitedLocation result;
        //When
        Mockito.when(gpsWebClient.trackUserLocationFromGps(user.getUserId())).thenReturn(expected);
        Mockito.when(rewardsWebClient.calculateRewards(Mapper.userDTOMapper(user))).thenReturn(userRewardsList);
        result = userService.trackUserLocation(user);
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getAllUsersWithLocationTest() {
        //Given
        Map<UUID, Location> expected = Data.getAllUsersWithLocation();
        Map<UUID, Location> result;
        //When
        Mockito.when(userRepository.findAllUsers()).thenReturn(Data.userList());
        result = userService.getAllUsersWithLocation();
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void addUserVisitedLocationTest() {
        //Given
        User user = Data.getAUser();
        VisitedLocation visitedLocation = Data.getLastVisitedLocationOfUser();
        //When
        doNothing().when(userRepository).addUserVisitedLocation(user.getUserName(), visitedLocation);
        userService.addUserVisitedLocation(user.getUserName(),visitedLocation);
        //Then
        Mockito.verify(userRepository, Mockito.times(1)).addUserVisitedLocation(user.getUserName(),visitedLocation);
    }

    @Test
    void addUserRewardTest() {
        //Given
        User user = Data.getAUser();
       UserReward userReward = new UserReward();
        //When
        doNothing().when(userRepository).addUserReward(user.getUserName(), userReward);
        userService.addUserReward(user.getUserName(),userReward);
        //Then
        Mockito.verify(userRepository, Mockito.times(1)).addUserReward(user.getUserName(),userReward);
    }

    @Test
    void addUserPreferencesTest() {
        //Given
        User user = Data.getAUser();
        UserPreferences userPreferences = new UserPreferences();
        //When
        doNothing().when(userRepository).addUserPreferences(user.getUserName(), userPreferences);
        userService.addUserPreferences(user.getUserName(),userPreferences);
        //Then
        Mockito.verify(userRepository, Mockito.times(1)).addUserPreferences(user.getUserName(),userPreferences);
    }
}
