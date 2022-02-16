package tourGuide.WebClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tourGuide.DTO.UserDTO;
import tourGuide.DTO.UserPreferencesDTO;
import tourGuide.Entity.Location;
import tourGuide.Entity.User;
import tourGuide.Entity.VisitedLocation;
import tourGuide.Utils.Mapper;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserWebClient {

    @Autowired
    RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //@Value("${tourguide.main.userurl}")
    private String BASE_URL_LOCALHOST_user = "http://tourguide-users:8082/user";
    //Declare the path to userLocation
    private final String PATH_USER_LOCATION = "/getUserLocation";
    //Declare the path to getUser
    private final String PATH_GET_USER = "/getUser";
    //Declare the path to getAllUsers
    private final String PATH_GET_ALL_USERS = "/getAllUsers";
    //Declare the path to getAllUsersWithLocation
    private final String PATH_GET_ALL_USERS_WITH_LOCATION = "/getAllUsersWithLocation";
    //Declare the path to addUserPreferences
    private final String PATH_ADD_USER_PREFERENCES = "/addUserPreferences";
    //Declare the path to addUserVisitedLocation
    private final String PATH_ADD_USER_VISITED_LOCATION = "/addUserVisitedLocation";
    //Declare userName param
    private final String USER_NAME = "?userName=";

    private Logger logger = LoggerFactory.getLogger(UserWebClient.class);

    public void setBASE_URL_LOCALHOST_user(String BASE_URL_LOCALHOST_user) {
        this.BASE_URL_LOCALHOST_user = BASE_URL_LOCALHOST_user;
    }

    /**
     * Method which calls the user app to get the location from a user
     * @param userName the username of the user that we want the location
     * @return last visited location of the user
     */
    public VisitedLocation getUserLocationFromUser(String userName) {
        logger.info("call the user app to get the location of user : "+userName);
        ResponseEntity<VisitedLocation> result  = restTemplate.getForEntity(BASE_URL_LOCALHOST_user+PATH_USER_LOCATION+USER_NAME+userName, VisitedLocation.class);
        return result.getBody();
    }

    /**
     * Method which calls the user app to get a user based on his username
     * @param userName username of the user that we want
     * @return the wanted user
     */
    public User getUser(String userName) {
        logger.info("call the user app to get the user for username :"+userName);
        ResponseEntity<UserDTO> result  = restTemplate.getForEntity(BASE_URL_LOCALHOST_user+PATH_GET_USER+USER_NAME+userName, UserDTO.class);
        return Mapper.convertUserDTOtoUser(result.getBody());
    }

    /**
     * method which calls the user app to get all the users
     * @return list of all the users
     */
    public List<User> getAllUsers(){
        logger.info("call the user app to get all users");
        logger.info(BASE_URL_LOCALHOST_user+PATH_GET_ALL_USERS);
        ResponseEntity<List<UserDTO>> result  =
                restTemplate.exchange(BASE_URL_LOCALHOST_user+PATH_GET_ALL_USERS,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<UserDTO>>() {
                        });
        return Mapper.convertToUserList(result.getBody());
    }

    /**
     * Method which calls the user app to get all the user with their location
     * @return map with user id and last known location
     */
    public Map<UUID, Location> getAllUsersWithLocation(){
        logger.info("call the user app to get all the users with their location");
        ResponseEntity<Map<UUID, Location>> result  =
                restTemplate.exchange(BASE_URL_LOCALHOST_user+PATH_GET_ALL_USERS_WITH_LOCATION,
                        HttpMethod.GET, null, new ParameterizedTypeReference<Map<UUID, Location>>() {
                        });
        return result.getBody();
    }

    /**
     * Method which calls the user app to add a visited location to a user
     * @param userName username of the user for which we want to add a visitedlocation
     * @param lastVisitedLocation visitedLocation that we want to add
     */
    public void addUserVisitedLocation(String userName, VisitedLocation lastVisitedLocation) throws URISyntaxException {
        logger.info("call the user app to add a visited location for user : "+userName);
        RequestEntity<VisitedLocation> request = RequestEntity
                .post(new URI(BASE_URL_LOCALHOST_user+PATH_ADD_USER_VISITED_LOCATION+USER_NAME+userName))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(lastVisitedLocation);

                restTemplate.exchange(BASE_URL_LOCALHOST_user+PATH_ADD_USER_VISITED_LOCATION+USER_NAME+userName,
                        HttpMethod.POST, request, String.class);
    }

    /**
     * Method which calls the user app to add user preferences to a user
     * @param userName username of the user for which we want to add  user preferences
     * @param userPreferencesDTO user preferences that we want to add
     */
    public void addUserPreferences(String userName, UserPreferencesDTO userPreferencesDTO) throws URISyntaxException {
        logger.info("call the user app to add a user preferences for user : "+userName);
        RequestEntity<UserPreferencesDTO> request = RequestEntity
                .post(new URI(BASE_URL_LOCALHOST_user+PATH_ADD_USER_PREFERENCES+USER_NAME+userName))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userPreferencesDTO);

        restTemplate.exchange(BASE_URL_LOCALHOST_user+PATH_ADD_USER_PREFERENCES+USER_NAME+userName,
                HttpMethod.POST, request, String.class);
    }
}
