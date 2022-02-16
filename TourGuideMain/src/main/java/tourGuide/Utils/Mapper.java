package tourGuide.Utils;

import org.javamoney.moneta.Money;
import tourGuide.DTO.UserDTO;
import tourGuide.DTO.UserPreferencesDTO;
import tourGuide.Entity.User;
import tourGuide.Entity.UserPreferences;

import javax.money.Monetary;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Mapper {

    /**
     * Method which convert an UserDTO list to a user list
     * @param userDTOList list of userDTO that we want to convert
     * @return list of users from the userDTO list
     */
    public static List<User> convertToUserList(List<UserDTO> userDTOList){
        List<User> userList = new ArrayList<>();
        userDTOList.forEach(user -> userList.add(convertUserDTOtoUser(user)));
        return userList;
    }

    /**
     * Method which convert an userDTO into a user
     * @param userDTO userDTO that we want to convert
     * @return user from userDTO
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
     * Method which convert userPreferencesDTO into userPreferences
     * @param userPreferencesDTO userPreferencesDTO that we want to convert
     * @return userPreferences from userPreferencesDTO
     */
    public static UserPreferences userPreferencesMapper(UserPreferencesDTO userPreferencesDTO){
        UserPreferences userPreferences = new UserPreferences();
        userPreferences.setAttractionProximity(userPreferencesDTO.getAttractionProximity());
        //userPreferences.setCurrency(Monetary.getCurrency(userPreferencesDTO.getCurrency()));
        userPreferences.setLowerPricePoint(Money.of(userPreferencesDTO.getLowerPricePoint(),Monetary.getCurrency("USD")));
        userPreferences.setHighPricePoint(Money.of(userPreferencesDTO.getHighPricePoint(),Monetary.getCurrency("USD")));
        userPreferences.setTripDuration(userPreferencesDTO.getTripDuration());
        userPreferences.setNumberOfAdults(userPreferencesDTO.getNumberOfAdults());
        userPreferences.setNumberOfChildren(userPreferencesDTO.getNumberOfChildren());
        userPreferences.setTicketQuantity(userPreferencesDTO.getTicketQuantity());
        return userPreferences;
    }

    /**
     * Method which convert a user to an userDTO
     * @param user user that we want to convert
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
     * Method which convert userPreferences into userPreferencesDTO
     * @param userPreferences userPreferences that we want to convert
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
        userPreferencesDTO.setTicketQuantity(userPreferences.getTicketQuantity());
        return userPreferencesDTO;
    }
}
