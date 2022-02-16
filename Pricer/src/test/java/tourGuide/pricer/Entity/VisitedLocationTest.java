package tourGuide.pricer.Entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

@SpringBootTest
public class VisitedLocationTest {

    @Test
    void visitedLocationGetterAndSetterTest() {
        //Given
        VisitedLocation visitedLocation = new VisitedLocation();
        UUID userId = UUID.fromString("648ed5ea-b766-4aee-a0b7-3686e166c977");
        Location location = new Location();
        Date timeVisited = new Date();
        //When
        visitedLocation.setLocation(location);
        visitedLocation.setTimeVisited(timeVisited);
        visitedLocation.setUserId(userId);
        //Then
        Assertions.assertEquals(userId,visitedLocation.getUserId());
        Assertions.assertEquals(location,visitedLocation.getLocation());
        Assertions.assertEquals(timeVisited,visitedLocation.getTimeVisited());
    }
}
