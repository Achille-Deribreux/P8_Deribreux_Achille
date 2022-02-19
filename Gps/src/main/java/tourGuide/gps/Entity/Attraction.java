package tourGuide.gps.Entity;

import java.util.Objects;
import java.util.UUID;

public class Attraction {
     private UUID attractionId;
     private String attractionName;
     private String city;
     private String state;
     private double latitude;
     private double longitude;

     public Attraction() {
     }

     public Attraction(UUID attractionId, String attractionName, String city, String state, double latitude, double longitude) {
          this.attractionId = attractionId;
          this.attractionName = attractionName;
          this.city = city;
          this.state = state;
          this.latitude = latitude;
          this.longitude = longitude;
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
          return Double.compare(that.latitude, latitude) == 0 && Double.compare(that.longitude, longitude) == 0 && Objects.equals(attractionName, that.attractionName) && Objects.equals(city, that.city) && Objects.equals(state, that.state);
     }

     @Override
     public int hashCode() {
          return Objects.hash(attractionName, city, state, latitude, longitude);
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
