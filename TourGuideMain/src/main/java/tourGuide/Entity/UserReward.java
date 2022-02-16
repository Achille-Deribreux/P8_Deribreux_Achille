package tourGuide.Entity;


public class UserReward {

	public VisitedLocation visitedLocation;
	public Attraction attraction;
	private int rewardPoints;

	public UserReward(VisitedLocation visitedLocation, Attraction attraction) {
		this.visitedLocation = visitedLocation;
		this.attraction = attraction;
	}

	public UserReward() {
	}

	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	
	public int getRewardPoints() {
		return rewardPoints;
	}
	
}
