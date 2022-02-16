package tourGuide.user.DTO;

import tourGuide.user.Entity.Provider;
import tourGuide.user.Entity.UserReward;
import tourGuide.user.Entity.VisitedLocation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UserDTO {
    private  UUID userId;
    private  String userName;
    private String phoneNumber;
    private String emailAddress;
    private Date latestLocationTimestamp;
    private List<VisitedLocation> visitedLocations = new ArrayList<>();
    private List<UserReward> userRewards = new ArrayList<>();
    private List<Provider> tripDeals = new ArrayList<>();
    private UserPreferencesDTO userPreferences = new UserPreferencesDTO();

    public UserDTO() {
    }

    public UserDTO(UUID userId, String userName, String phoneNumber, String emailAddress, Date latestLocationTimestamp, List<VisitedLocation> visitedLocations, List<UserReward> userRewards, List<Provider> tripDeals, UserPreferencesDTO userPreferences) {
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.latestLocationTimestamp = latestLocationTimestamp;
        this.visitedLocations = visitedLocations;
        this.userRewards = userRewards;
        this.tripDeals = tripDeals;
        this.userPreferences = userPreferences;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getLatestLocationTimestamp(Date latestLocationTimestamp) {
        return this.latestLocationTimestamp;
    }

    public void setLatestLocationTimestamp(Date latestLocationTimestamp) {
        this.latestLocationTimestamp = latestLocationTimestamp;
    }

    public List<VisitedLocation> getVisitedLocations() {
        return visitedLocations;
    }

    public void setVisitedLocations(List<VisitedLocation> visitedLocations) {
        this.visitedLocations = visitedLocations;
    }

    public List<UserReward> getUserRewards() {
        return userRewards;
    }

    public void setUserRewards(List<UserReward> userRewards) {
        this.userRewards = userRewards;
    }

    public List<Provider> getTripDeals() {
        return tripDeals;
    }

    public void setTripDeals(List<Provider> tripDeals) {
        this.tripDeals = tripDeals;
    }

    public UserPreferencesDTO getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(UserPreferencesDTO userPreferences) {
        this.userPreferences = userPreferences;
    }
}
