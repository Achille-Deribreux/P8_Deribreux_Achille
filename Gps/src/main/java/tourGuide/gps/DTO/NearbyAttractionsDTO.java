package tourGuide.gps.DTO;


import tourGuide.gps.Entity.Attraction;
import tourGuide.gps.Entity.Location;

import java.util.Objects;


public class NearbyAttractionsDTO {
    private Attraction attraction;
    private Location userLocation;
    private Double distance;
    private int rewardPoints;

    public NearbyAttractionsDTO(Attraction attraction, Location userLocation, Double distance, int rewardPoints) {
        this.attraction = attraction;
        this.userLocation = userLocation;
        this.distance = distance;
        this.rewardPoints = rewardPoints;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public Location getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(Location userLocation) {
        this.userLocation = userLocation;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
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
        NearbyAttractionsDTO that = (NearbyAttractionsDTO) o;
        return rewardPoints == that.rewardPoints && Objects.equals(attraction, that.attraction) && Objects.equals(userLocation, that.userLocation) && Objects.equals(distance, that.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attraction, userLocation, distance, rewardPoints);
    }

    @Override
    public String toString() {
        return "NearbyAttractionsDTO{" +
                "attraction=" + attraction +
                ", userLocation=" + userLocation +
                ", distance=" + distance +
                ", rewardPoints=" + rewardPoints +
                '}';
    }
}

