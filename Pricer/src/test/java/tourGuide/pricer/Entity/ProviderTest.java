package tourGuide.pricer.Entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class ProviderTest {

    @Test
    void providerGetterAndSetterTest() {
        //Given
        UUID tripId = UUID.fromString("648ed5ea-b766-4aee-a0b7-3686e166c977");
        String name = "aName";
        double price = 12.0;
        Provider provider = new Provider();
        //When
        provider.setName(name);
        provider.setPrice(price);
        provider.setTripId(tripId);
        //Then
        Assertions.assertEquals(price, provider.getPrice());
        Assertions.assertEquals(name, provider.getName());
        Assertions.assertEquals(tripId, provider.getTripId());
    }
}
