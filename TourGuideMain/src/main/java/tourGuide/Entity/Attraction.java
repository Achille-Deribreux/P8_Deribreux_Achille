package tourGuide.Entity;

import java.util.Objects;
import java.util.UUID;


public class Attraction {
     String attractionName;
     String city;
     String state;
     UUID attractionId;

     public Attraction(String attractionName, String city, String state, UUID attractionId) {
          this.attractionName = attractionName;
          this.city = city;
          this.state = state;
          this.attractionId = attractionId;
     }

     public Attraction() {
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

     public UUID getAttractionId() {
          return attractionId;
     }

     public void setAttractionId(UUID attractionId) {
          this.attractionId = attractionId;
     }

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          Attraction that = (Attraction) o;
          return Objects.equals(attractionName, that.attractionName) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(attractionId, that.attractionId);
     }

     @Override
     public int hashCode() {
          return Objects.hash(attractionName, city, state, attractionId);
     }

     @Override
     public String toString() {
          return "Attraction{" +
                  "attractionName='" + attractionName + '\'' +
                  ", city='" + city + '\'' +
                  ", state='" + state + '\'' +
                  ", attractionId=" + attractionId +
                  '}';
     }
}
