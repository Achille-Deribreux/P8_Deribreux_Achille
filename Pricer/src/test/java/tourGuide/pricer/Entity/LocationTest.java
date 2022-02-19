package tourGuide.pricer.Entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LocationTest {

    @Test
    void locationGetterAndSetterTest() {
        //Given
        double latitude = 10.0;
        double longitude =10.0;
        Location location = new Location();
        //When
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        //Then
        Assertions.assertEquals(latitude,location.getLatitude());
        Assertions.assertEquals(longitude,location.getLongitude());
    }
}
