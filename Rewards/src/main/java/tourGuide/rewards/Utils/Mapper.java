package tourGuide.rewards.Utils;

import org.javamoney.moneta.Money;
import com.con.DTO.*;
import com.con.Entity.*;

import javax.money.Monetary;


import static java.lang.Integer.parseInt;

public class Mapper {

    private Mapper() {
        //Empty constructor
    }

    /**
     * method that converts userDTO to user
     * @param userDTO the userDTO to convert
     * @return User from userDTO
     */
    public static User convertUserDTOtoUser(UserDTO userDTO){
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUserName(userDTO.getUserName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmailAddress(userDTO.getEmailAddress());
        user.setLatestLocationTimestamp(userDTO.getLatestLocationTimestamp());
        user.setUserPreferences(userPreferencesMapper(userDTO.getUserPreferences()));
        user.setVisitedLocations(userDTO.getVisitedLocations());
        user.setUserRewards(userDTO.getUserRewards());
        user.setTripDeals(userDTO.getTripDeals());
        return user;
    }

    /**
     * method that converts userPreferencesDTO into userPreferences
     * @param userPreferencesDTO userPreferencesDTO to convert.
     * @return userPreferences from userPreferencesDTO
     */
    public static UserPreferences userPreferencesMapper(UserPreferencesDTO userPreferencesDTO){
        UserPreferences userPreferences = new UserPreferences();
        userPreferences.setAttractionProximity(userPreferencesDTO.getAttractionProximity());
        userPreferences.setCurrency(Monetary.getCurrency(userPreferencesDTO.getCurrency()));
        userPreferences.setLowerPricePoint(Money.of(userPreferencesDTO.getLowerPricePoint(),userPreferencesDTO.getCurrency()));
        userPreferences.setHighPricePoint(Money.of(userPreferencesDTO.getHighPricePoint(),userPreferencesDTO.getCurrency()));
        userPreferences.setTripDuration(userPreferencesDTO.getTripDuration());
        userPreferences.setNumberOfAdults(userPreferencesDTO.getNumberOfAdults());
        userPreferences.setNumberOfChildren(userPreferencesDTO.getNumberOfChildren());
        return userPreferences;
    }

    /**
     * method that converts user to userDTO
     * @param user to convert
     * @return userDTO from user
     */
    public static UserDTO convertUserToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUserName(user.getUserName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setEmailAddress(user.getEmailAddress());
        userDTO.setLatestLocationTimestamp(user.getLatestLocationTimestamp());
        userDTO.setUserPreferences(userPreferencesMapperToDTO(user.getUserPreferences()));
        userDTO.setVisitedLocations(user.getVisitedLocations());
        userDTO.setUserRewards(user.getUserRewards());
        userDTO.setTripDeals(user.getTripDeals());
        return userDTO;
    }

    /**
     * method that converts userPreferences to userPreferencesDTO
     * @param userPreferences userPreferences to convert
     * @return userPreferencesDTO from userPreferences
     */
    public static UserPreferencesDTO userPreferencesMapperToDTO(UserPreferences userPreferences){
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
}
