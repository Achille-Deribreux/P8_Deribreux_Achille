package tourGuide;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.con.DTO.*;
import com.con.Entity.*;
import tourGuide.Utils.Data;
import tourGuide.Utils.Mapper;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

@SpringBootTest
class MapperTest {

    @Test
    void userPreferencesMapperToDTOTest() {
        //Given
        UserPreferences userPreferences= Data.getUserPreferences();
        UserPreferencesDTO expected = Data.getUserPreferencesDTO();
        UserPreferencesDTO result;
        //When
        result = Mapper.userPreferencesMapperToDTO(userPreferences);
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void userPreferencesMapperTest() {
        //Given
        UserPreferencesDTO userPreferencesDTO = Data.getUserPreferencesDTO();
        UserPreferences expected = Data.getUserPreferences();
        UserPreferences result;
        //When
        result = Mapper.userPreferencesMapper(userPreferencesDTO);
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void convertUserDTOtoUserTest() {
        //Given
        User user = Data.getUser1();
        UserDTO expected = Data.getUserDTO1();
        UserDTO result;
        //When
        result = Mapper.convertUserToUserDTO(user);
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void convertUserDTOtoUser() {
        //Given
        UserDTO userDTO = Data.getUserDTO1();
        User expected = Data.getUser1();
        User result;
        //When
        result = Mapper.convertUserDTOtoUser(userDTO);
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void convertToUserListTest() {
        //Given
        List<UserDTO> userDTOList = new ArrayList<>(Arrays.asList(Data.getUserDTO1(),Data.getUserDTO2()));
        List<User> expected = new ArrayList<>(Arrays.asList(Data.getUser1(),Data.getUser2()));
        List<User> result;
        //When
        result = Mapper.convertToUserList(userDTOList);
        //Then
        Assertions.assertEquals(expected,result);
    }
}
