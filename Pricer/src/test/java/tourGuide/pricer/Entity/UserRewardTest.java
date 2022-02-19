package tourGuide.pricer.Entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRewardTest {

    @Test
    void userRewardGetterAndSetterTest() {
        //Given
        UserReward userReward = new UserReward();
        VisitedLocation visitedLocation = new VisitedLocation();
        Attraction attraction =new Attraction();
        int rewardPoints = 10;
        //When
        userReward.setRewardPoints(rewardPoints);
        userReward.setAttraction(attraction);
        userReward.setVisitedLocation(visitedLocation);
        //Then
        Assertions.assertEquals(visitedLocation,userReward.getVisitedLocation());
        Assertions.assertEquals(attraction,userReward.getAttraction());
        Assertions.assertEquals(rewardPoints,userReward.getRewardPoints());
    }
}
