package tourGuide.user.Utils;

import org.javamoney.moneta.Money;
import com.con.Entity.*;
import com.con.DTO.*;

import javax.money.Monetary;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Mapper {

    private Mapper() {
    }

    /**
     * Method who converts UserPreferences into userPreferencesDTO
     * @param userPreferences userPreferences to convert
     * @return userPreferencesDTO from userPreferences
     */
    public static UserPreferencesDTO userPreferencesMapper(UserPreferences userPreferences){
        UserPreferencesDTO userPreferencesDTO = new UserPreferencesDTO();
        userPreferencesDTO.setAttractionProximity(userPreferences.getAttractionProximity());
        userPreferencesDTO.setCurrency(String.valueOf(userPreferences.getCurrency()));
        userPreferencesDTO.setLowerPricePoint(parseInt(String.valueOf(userPreferences.getLowerPricePoint().getNumber())));
        userPreferencesDTO.setHighPricePoint(parseInt(String.valueOf(userPreferences.getHighPricePoint().getNumber())));
        userPreferencesDTO.setTripDuration(userPreferences.getTripDuration());
        userPreferencesDTO.setNumberOfAdults(userPreferences.getNumberOfAdults());
        userPreferencesDTO.setNumberOfChildren(userPreferences.getNumberOfChildren());
        return userPreferencesDTO;
    }

    /**
     * Method who converts a User into a userDTO
     * @param user user that we want to convert
     * @return userDTO from user
     */
    public static UserDTO userDTOMapper(User user){
       UserDTO userDTO = new UserDTO();
       userDTO.setUserId(user.getUserId());
       userDTO.setUserName(user.getUserName());
       userDTO.setPhoneNumber(user.getPhoneNumber());
       userDTO.setEmailAddress(user.getEmailAddress());
       userDTO.setUserPreferences(userPreferencesMapper(user.getUserPreferences()));
       userDTO.setVisitedLocations(user.getVisitedLocations());
       userDTO.setUserRewards(user.getUserRewards());
       userDTO.setTripDeals(user.getTripDeals());
        return userDTO;
    }

    /**
     * Method who converts a whole list of user into a userDTO list
     * @param userList list of users that we want to convert
     * @return an userDTO list from the users list
     */
    public static List<UserDTO> userListMapper(List<User> userList){
        List<UserDTO> userDTOList = new ArrayList<>();
        userList.forEach(user -> userDTOList.add(userDTOMapper(user)));
        return userDTOList;
    }

    /**
     * Method which convert userPreferencesDTO into userPreferences
     * @param userPreferencesDTO userPreferencesDTO that we want to convert
     * @return userPreferences from userPreferencesDTO
     */
    public static UserPreferences userPreferencesDTOMapper(UserPreferencesDTO userPreferencesDTO){
        UserPreferences userPreferences = new UserPreferences();
        userPreferences.setAttractionProximity(userPreferencesDTO.getAttractionProximity());
        userPreferences.setCurrency(Monetary.getCurrency(userPreferencesDTO.getCurrency()));
        userPreferences.setLowerPricePoint(Money.of(userPreferencesDTO.getLowerPricePoint(),userPreferencesDTO.getCurrency()));
        userPreferences.setHighPricePoint(Money.of(userPreferencesDTO.getHighPricePoint(),userPreferencesDTO.getCurrency()));
        userPreferences.setTripDuration(userPreferencesDTO.getTripDuration());
        userPreferences.setNumberOfAdults(userPreferencesDTO.getNumberOfAdults());
        userPreferences.setNumberOfChildren(userPreferencesDTO.getNumberOfChildren());
        userPreferences.setTicketQuantity(userPreferencesDTO.getTicketQuantity());
        return userPreferences;
    }
}
