package tourGuide.pricer.Entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class AttractionTest {

    @Test
    void attractionGetterAndSetterTest() {
        //Given
        UUID attractionId = UUID.fromString("648ed5ea-b766-4aee-a0b7-3686e166c977");
        String attractionName = "attraction";
        String city ="Brussels";
        String state = "Be";
        double latitude = 10.0;
        double longitude =10.0;
        Attraction attraction = new Attraction();
        //When
        attraction.setAttractionId(attractionId);
        attraction.setAttractionName(attractionName);
        attraction.setCity(city);
        attraction.setState(state);
        attraction.setLatitude(latitude);
        attraction.setLongitude(longitude);
        //Then
        Assertions.assertEquals(attractionId,attraction.getAttractionId());
        Assertions.assertEquals(attractionName,attraction.getAttractionName());
        Assertions.assertEquals(city,attraction.getCity());
        Assertions.assertEquals(state,attraction.getState());
        Assertions.assertEquals(longitude,attraction.getLongitude());
        Assertions.assertEquals(latitude,attraction.getLatitude());
    }
}
