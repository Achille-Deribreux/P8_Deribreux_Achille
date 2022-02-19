package tourGuide.pricer.Entity;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

@SpringBootTest
class UserPreferencesTest {

    @Test
    void userPreferencesGetterAndSetterTest() {
        //Given
        UserPreferences userPreferences = new UserPreferences();
        int attractionProximity = Integer.MAX_VALUE;
        CurrencyUnit currency = Monetary.getCurrency("USD");
        Money lowerPricePoint = Money.of(0, currency);
        Money highPricePoint = Money.of(Integer.MAX_VALUE, currency);
        int tripDuration = 1;
        int ticketQuantity = 1;
        int numberOfAdults = 1;
        int numberOfChildren = 0;
        //When
        userPreferences.setAttractionProximity(attractionProximity);
        userPreferences.setHighPricePoint(highPricePoint);
        userPreferences.setLowerPricePoint(lowerPricePoint);
        userPreferences.setTripDuration(tripDuration);
        userPreferences.setTicketQuantity(ticketQuantity);
        userPreferences.setNumberOfAdults(numberOfAdults);
        userPreferences.setNumberOfChildren(numberOfChildren);
        //Then
        Assertions.assertEquals(attractionProximity,userPreferences.getAttractionProximity());
        Assertions.assertEquals(highPricePoint,userPreferences.getHighPricePoint());
        Assertions.assertEquals(lowerPricePoint,userPreferences.getLowerPricePoint());
        Assertions.assertEquals(tripDuration,userPreferences.getTripDuration());
        Assertions.assertEquals(ticketQuantity,userPreferences.getTicketQuantity());
        Assertions.assertEquals(numberOfAdults,userPreferences.getNumberOfAdults());
        Assertions.assertEquals(numberOfChildren,userPreferences.getNumberOfChildren());
    }
}
