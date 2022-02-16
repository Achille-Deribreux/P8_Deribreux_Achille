package tourGuide.rewards.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;


public class Provider {
    public  String name;
    public  double price;
    public  UUID tripId;

    public Provider(String name, double price, UUID tripId) {
        this.name = name;
        this.price = price;
        this.tripId = tripId;
    }

    public Provider() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public UUID getTripId() {
        return tripId;
    }

    public void setTripId(UUID tripId) {
        this.tripId = tripId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider provider = (Provider) o;
        return Double.compare(provider.price, price) == 0 && Objects.equals(name, provider.name) && Objects.equals(tripId, provider.tripId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, tripId);
    }

    @Override
    public String toString() {
        return "Provider{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", tripId=" + tripId +
                '}';
    }
}
