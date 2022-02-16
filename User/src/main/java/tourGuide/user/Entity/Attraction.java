package tourGuide.user.Entity;

import lombok.*;

import java.util.Objects;
import java.util.UUID;


public class Attraction {
     UUID attractionId;
     String attractionName;
     String city;
     String state;

     public Attraction(UUID attractionId, String attractionName, String city, String state) {
          this.attractionId = attractionId;
          this.attractionName = attractionName;
          this.city = city;
          this.state = state;
     }

     public Attraction() {
     }

     public UUID getAttractionId() {
          return attractionId;
     }

     public void setAttractionId(UUID attractionId) {
          this.attractionId = attractionId;
     }

     public String getAttractionName() {
          return attractionName;
     }

     public void setAttractionName(String attractionName) {
          this.attractionName = attractionName;
     }

     public String getCity() {
          return city;
     }

     public void setCity(String city) {
          this.city = city;
     }

     public String getState() {
          return state;
     }

     public void setState(String state) {
          this.state = state;
     }

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          Attraction that = (Attraction) o;
          return Objects.equals(attractionId, that.attractionId) && Objects.equals(attractionName, that.attractionName) && Objects.equals(city, that.city) && Objects.equals(state, that.state);
     }

     @Override
     public int hashCode() {
          return Objects.hash(attractionId, attractionName, city, state);
     }

     @Override
     public String toString() {
          return "Attraction{" +
                  "attractionId=" + attractionId +
                  ", attractionName='" + attractionName + '\'' +
                  ", city='" + city + '\'' +
                  ", state='" + state + '\'' +
                  '}';
     }
}
