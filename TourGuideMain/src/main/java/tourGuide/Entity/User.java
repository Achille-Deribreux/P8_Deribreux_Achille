package tourGuide.Entity;

import java.util.*;


public class User {
	private  UUID userId;
	private  String userName;
	private String phoneNumber;
	private String emailAddress;
	private Date latestLocationTimestamp;
	private List<VisitedLocation> visitedLocations = new ArrayList<>();
	private List<UserReward> userRewards = new ArrayList<>();
	private UserPreferences userPreferences = new UserPreferences();
	private List<Provider> tripDeals = new ArrayList<>();

	public User() {
	}

	public User(UUID userId, String userName, String phoneNumber, String emailAddress) {
		this.userId = userId;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
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

	public Date getLatestLocationTimestamp() {
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

	public UserPreferences getUserPreferences() {
		return userPreferences;
	}

	public void setUserPreferences(UserPreferences userPreferences) {
		this.userPreferences = userPreferences;
	}

	public List<Provider> getTripDeals() {
		return tripDeals;
	}

	public void setTripDeals(List<Provider> tripDeals) {
		this.tripDeals = tripDeals;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(userId, user.userId) && Objects.equals(userName, user.userName) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(emailAddress, user.emailAddress) && Objects.equals(latestLocationTimestamp, user.latestLocationTimestamp) && Objects.equals(visitedLocations, user.visitedLocations) && Objects.equals(userRewards, user.userRewards) && Objects.equals(userPreferences, user.userPreferences) && Objects.equals(tripDeals, user.tripDeals);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, userName, phoneNumber, emailAddress, latestLocationTimestamp, visitedLocations, userRewards, userPreferences, tripDeals);
	}

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", userName='" + userName + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", emailAddress='" + emailAddress + '\'' +
				", latestLocationTimestamp=" + latestLocationTimestamp +
				", visitedLocations=" + visitedLocations +
				", userRewards=" + userRewards +
				", userPreferences=" + userPreferences +
				", tripDeals=" + tripDeals +
				'}';
	}
}
