package tourGuide.gps.DTO;


import lombok.*;
import tourGuide.gps.Entity.Attraction;

import java.util.Objects;



public class AttractionWithDistanceFromUserDTO {
    Attraction attraction;
    Double distanceFromUser;

    public AttractionWithDistanceFromUserDTO() {
    }

    public AttractionWithDistanceFromUserDTO(Attraction attraction, Double distanceFromUser) {
        this.attraction = attraction;
        this.distanceFromUser = distanceFromUser;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public Double getDistanceFromUser() {
        return distanceFromUser;
    }

    public void setDistanceFromUser(Double distanceFromUser) {
        this.distanceFromUser = distanceFromUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttractionWithDistanceFromUserDTO that = (AttractionWithDistanceFromUserDTO) o;
        return Objects.equals(attraction, that.attraction) && Objects.equals(distanceFromUser, that.distanceFromUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attraction, distanceFromUser);
    }

    @Override
    public String toString() {
        return "AttractionWithDistanceFromUserDTO{" +
                "attraction=" + attraction +
                ", distanceFromUser=" + distanceFromUser +
                '}';
    }
}
