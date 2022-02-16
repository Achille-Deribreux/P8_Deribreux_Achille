package tourGuide.pricer.Entity;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;


public class VisitedLocation {
    public UUID userId;
    public Location location;
    public Date timeVisited;

    public VisitedLocation() {
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getTimeVisited() {
        return timeVisited;
    }

    public void setTimeVisited(Date timeVisited) {
        this.timeVisited = timeVisited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitedLocation that = (VisitedLocation) o;
        return Objects.equals(userId, that.userId) && Objects.equals(location, that.location) && Objects.equals(timeVisited, that.timeVisited);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, location, timeVisited);
    }

    @Override
    public String toString() {
        return "VisitedLocation{" +
                "userId=" + userId +
                ", location=" + location +
                ", timeVisited=" + timeVisited +
                '}';
    }
}
