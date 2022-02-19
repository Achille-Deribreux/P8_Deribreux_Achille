package tourGuide.gps.DTO;

import com.con.DTO.AttractionWithDistanceFromUserDTO;
import com.con.Entity.Attraction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AttractionWithDistanceFromUserDTOTest {

    @Test
    void attractionSetterAndGetterTest() {
        //Given
        Attraction expected = new Attraction();
        Attraction result;
        Double expectedDouble = 10.0;
        Double resultDouble;
        AttractionWithDistanceFromUserDTO attractionWithDistanceFromUserDTO = new AttractionWithDistanceFromUserDTO();
        //When
        attractionWithDistanceFromUserDTO.setDistanceFromUser(expectedDouble);
        attractionWithDistanceFromUserDTO.setAttraction(expected);
        result = attractionWithDistanceFromUserDTO.getAttraction();
        resultDouble = attractionWithDistanceFromUserDTO.getDistanceFromUser();
        //Then
        Assertions.assertEquals(expectedDouble,resultDouble);
        Assertions.assertEquals(expected,result);
    }
}
