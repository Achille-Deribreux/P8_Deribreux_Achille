package tourGuide.rewards.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import rewardCentral.RewardCentral;
import tourGuide.rewards.Entity.*;
import tourGuide.rewards.TestUtils.Calculator;
import tourGuide.rewards.TestUtils.Data;
import tourGuide.rewards.WebClient.GpsWebClient;
import tourGuide.rewards.WebClient.UserWebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class RewardsServiceTest {

    @Autowired
    RewardsService rewardsService;

    @MockBean
    GpsWebClient gpsWebClient;

    @MockBean
    UserWebClient userWebClient;

    @MockBean
    RewardCentral rewardCentral;

    @BeforeEach
    void setUp() {
        rewardsService.setRewardsCentral(rewardCentral);
        rewardsService.setGpsWebClient(gpsWebClient);
        rewardsService.setUserWebClient(userWebClient);
        rewardsService.setAttractions(Data.getAttractionList());
        rewardsService.setProximityBuffer(100);
    }

    @Test
    void calculateRewardsTest() {
        //Given
        RewardsService spyRewardsService = Mockito.spy(rewardsService);
        User user = Data.getAUser();
        List<UserReward> expected = new ArrayList<>();
        List<UserReward> result;
        //When
        Mockito.doReturn(false).when(spyRewardsService).nearAttraction(Mockito.any(),Mockito.any());
        result = spyRewardsService.calculateRewards(user);
        Assertions.assertEquals(expected,result);
    }

    @Test
    void nearAttractionTest() {
        //Given
        User user = Data.getAUser();
        Location location = new Location(10,10);
        Attraction attraction = Data.getOneAttraction();
        VisitedLocation visitedLocation = new VisitedLocation(user.getUserId(),location, null);
        // Then
        Assertions.assertFalse(rewardsService.nearAttraction(visitedLocation,attraction));
    }

    @Test
    void getRewardPointsTest() {
        //Given
        Attraction attraction = Data.getOneAttraction();
        User user = Data.getAUser();
        int expected = 10;
        int result;
        //When
        Mockito.when(rewardCentral.getAttractionRewardPoints(attraction.getAttractionId(),user.getUserId())).thenReturn(expected);
        result = rewardsService.getRewardPoints(attraction,user);
        //Then
        Mockito.verify(rewardCentral,Mockito.times(1)).getAttractionRewardPoints(attraction.getAttractionId(),user.getUserId());
        Assertions.assertEquals(expected,result);
    }
}
