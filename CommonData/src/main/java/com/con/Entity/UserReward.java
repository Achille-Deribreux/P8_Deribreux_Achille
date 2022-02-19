package com.con.Entity;


import java.util.Objects;

public class UserReward {

	private VisitedLocation visitedLocation;
	private Attraction attraction;
	private int rewardPoints;

	public UserReward(VisitedLocation visitedLocation, Attraction attraction, int rewardPoints) {
		this.visitedLocation = visitedLocation;
		this.attraction = attraction;
		this.rewardPoints = rewardPoints;
	}

	public UserReward(VisitedLocation visitedLocation, Attraction attraction) {
		this.visitedLocation = visitedLocation;
		this.attraction = attraction;
	}

	public UserReward() {
	}

	public VisitedLocation getVisitedLocation() {
		return visitedLocation;
	}

	public void setVisitedLocation(VisitedLocation visitedLocation) {
		this.visitedLocation = visitedLocation;
	}

	public Attraction getAttraction() {
		return attraction;
	}

	public void setAttraction(Attraction attraction) {
		this.attraction = attraction;
	}

	public int getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserReward that = (UserReward) o;
		return rewardPoints == that.rewardPoints && Objects.equals(visitedLocation, that.visitedLocation) && Objects.equals(attraction, that.attraction);
	}

	@Override
	public int hashCode() {
		return Objects.hash(visitedLocation, attraction, rewardPoints);
	}

	@Override
	public String toString() {
		return "UserReward{" +
				"visitedLocation=" + visitedLocation +
				", attraction=" + attraction +
				", rewardPoints=" + rewardPoints +
				'}';
	}
}
