package tourGuide.pricer.Entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class UserTest {

    @Test
    void userGetterAndSetterTest() {
        //Given
        User user = new User();
        UUID userId = UUID.fromString("648ed5ea-b766-4aee-a0b7-3686e166c977");
        String userName = "username";
        String phoneNumber ="09876";
        String emailAddress = "a@a.be";
        Date latestLocationTimestamp = new Date();
        List<VisitedLocation> visitedLocations = new ArrayList<>();
        List<UserReward> userRewards = new ArrayList<>();
        UserPreferences userPreferences = new UserPreferences();
        List<Provider> tripDeals = new ArrayList<>();
        //When
        user.setUserId(userId);
        user.setUserName(userName);
        user.setPhoneNumber(phoneNumber);
        user.setEmailAddress(emailAddress);
        user.setLatestLocationTimestamp(latestLocationTimestamp);
        user.setVisitedLocations(visitedLocations);
        user.setUserRewards(userRewards);
        user.setUserPreferences(userPreferences);
        user.setTripDeals(tripDeals);
        //Then
        Assertions.assertEquals(userId,user.getUserId());
        Assertions.assertEquals(userName,user.getUserName());
        Assertions.assertEquals(phoneNumber,user.getPhoneNumber());
        Assertions.assertEquals(emailAddress,user.getEmailAddress());
        Assertions.assertEquals(latestLocationTimestamp,user.getLatestLocationTimestamp());
        Assertions.assertEquals(visitedLocations,user.getVisitedLocations());
        Assertions.assertEquals(userRewards,user.getUserRewards());
        Assertions.assertEquals(userPreferences,user.getUserPreferences());
        Assertions.assertEquals(tripDeals,user.getTripDeals());
    }
}
