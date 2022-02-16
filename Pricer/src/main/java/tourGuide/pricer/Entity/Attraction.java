package tourGuide.pricer.Entity;

import java.util.Objects;
import java.util.UUID;

public class Attraction {
     public UUID attractionId;
     public String attractionName;
     String city;
     String state;
     double latitude;
     double longitude;

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

     public double getLatitude() {
          return latitude;
     }

     public void setLatitude(double latitude) {
          this.latitude = latitude;
     }

     public double getLongitude() {
          return longitude;
     }

     public void setLongitude(double longitude) {
          this.longitude = longitude;
     }

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          Attraction that = (Attraction) o;
          return Double.compare(that.latitude, latitude) == 0 && Double.compare(that.longitude, longitude) == 0 && Objects.equals(attractionId, that.attractionId) && Objects.equals(attractionName, that.attractionName) && Objects.equals(city, that.city) && Objects.equals(state, that.state);
     }

     @Override
     public int hashCode() {
          return Objects.hash(attractionId, attractionName, city, state, latitude, longitude);
     }

     @Override
     public String toString() {
          return "Attraction{" +
                  "attractionId=" + attractionId +
                  ", attractionName='" + attractionName + '\'' +
                  ", city='" + city + '\'' +
                  ", state='" + state + '\'' +
                  ", latitude=" + latitude +
                  ", longitude=" + longitude +
                  '}';
     }
}
