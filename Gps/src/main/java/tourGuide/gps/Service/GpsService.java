package tourGuide.gps.Service;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tourGuide.gps.DTO.AttractionWithDistanceFromUserDTO;
import tourGuide.gps.DTO.NearbyAttractionsDTO;
import tourGuide.gps.Entity.Location;
import tourGuide.gps.Utils.DistanceCalculator;
import tourGuide.gps.Utils.Mapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GpsService {

    private final Logger logger = LoggerFactory.getLogger(GpsService.class);

    private GpsUtil gpsUtil = new GpsUtil();

    private List<Attraction> attractionList = gpsUtil.getAttractions();

    /**
     *  This method is a setter of gpsUtil
     * @param gpsUtil gpsUtil app
     */
    public void setGpsUtil(GpsUtil gpsUtil) {
        this.gpsUtil = gpsUtil;
    }

    /**
     * This method search the nearby attractions, based on the user's location
     * @param latitude postion (latitude) of the user
     * @param longitude postion (longitude) of the user
     * @param limit how many NearbyAttractionsDTO you want in the list
     * @return List of NearbyAttractionsDTO
     */
    public List<NearbyAttractionsDTO> getNearByAttractions(Double latitude, Double longitude, Integer limit) {
        logger.info("Search the "+limit+" nearbyAttractions for with latitude : "+latitude+" and longitude "+longitude);
        List<NearbyAttractionsDTO> nearbyAttractionsDTOList = new ArrayList<>();
        Location location =  new Location(latitude, longitude);

        List<AttractionWithDistanceFromUserDTO> nearbyAttractions = getAttractionListWithDistanceFromUser(location).stream().limit(limit).collect(Collectors.toList());

        for(AttractionWithDistanceFromUserDTO attraction : nearbyAttractions){
            logger.info("add attraction : "+attraction.getAttraction().getAttractionName()+" to the list");
            nearbyAttractionsDTOList.add(new NearbyAttractionsDTO(attraction.getAttraction(), location,attraction.getDistanceFromUser(),1));
        }
        return nearbyAttractionsDTOList;
    }

    /**
     * Method who calls the gpsUtil.getUserLocation method to get the location of a User
     * @param userId the id of the user
     * @return VisitedLocation, the current user's position
     */
    public VisitedLocation trackUserLocation(UUID userId) {
        logger.info("track location for userId : "+userId);
        return gpsUtil.getUserLocation(userId);
    }

    /**
     *Method who creates a sorted list of all the attractions with the distance between the user and the attraction
     * @param location user's location
     * @return sorted list of all the attractions with the distance between the user and the attraction
     */
    public List<AttractionWithDistanceFromUserDTO> getAttractionListWithDistanceFromUser(Location location){
        List<AttractionWithDistanceFromUserDTO> attractionWithDistanceFromUserList = new ArrayList<>();

        for(Attraction attraction : getAllAttractions()) {
            attractionWithDistanceFromUserList.add(new AttractionWithDistanceFromUserDTO(Mapper.mapAttractionFromGpsToEntity(attraction), DistanceCalculator.distance(location.getLatitude(), location.getLongitude(), attraction.latitude, attraction.longitude)));
        }

        return attractionWithDistanceFromUserList.stream().sorted(Comparator.comparing(AttractionWithDistanceFromUserDTO::getDistanceFromUser)).collect(Collectors.toList());
    }

    /**
     * Method who get all the attractions from GpsUtil
     * @return A list of all the attractions
     */
    public List<Attraction> getAllAttractions(){
        logger.info("getAllAttractions from gpsUtil");
        return attractionList;
    }
}
