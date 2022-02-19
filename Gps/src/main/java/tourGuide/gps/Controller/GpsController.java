package tourGuide.gps.Controller;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tourGuide.gps.DTO.NearbyAttractionsDTO;
import tourGuide.gps.Service.GpsService;
import tourGuide.gps.Utils.DistanceCalculator;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/gps")
public class GpsController {

    @Autowired
    private GpsService gpsService;

    private Logger logger = LoggerFactory.getLogger(GpsController.class);

    /**
     * This method answer to the get request at /getUserLocation in order to return the user's Position
     * @param userId id of the user from which you want the position
     * @return VisitedLocation the position of the user with timestamp
     */
    @GetMapping("/getUserLocation")
    public VisitedLocation getUserLocation(@RequestParam(value="userId") UUID userId){
        logger.info("request at /getUserLocation with userId "+userId);
        return gpsService.trackUserLocation(userId);
    }

    /**
     * This method answer to the get request /getNearbyAttractions in order to return the 5 nearest attraction of the user
     * @param latitude latitude of the user's position
     * @param longitude longitude of the user's position
     * @param limit the amount of nearByAttraction that you want
     * @return a list of the nearest Attractions, based on the user's position
     */
    @GetMapping("/getNearbyAttractions")
    public List<NearbyAttractionsDTO> getNearbyAttractions(@RequestParam(value="latitude")Double latitude,@RequestParam(value="longitude")Double longitude, @RequestParam(value="limit")Integer limit){
        logger.info("request at /getNearbyAttractions with latitude : "+latitude+" and longitude "+longitude);
        return gpsService.getNearByAttractions(latitude, longitude, limit);
    }

    /**
     * This method answer to the get request at /getAllAttractions and returns all the Attractions
     * @return List of all the Attractions
     */
    @GetMapping("/getAllAttractions")
    public List<Attraction> getAllAttractions(){
        logger.info("request at /getAllAttractions");
        return gpsService.getAllAttractions();
    }

    /**
     * This method answer to a get request at /calculateDistance and returns the distance between 2 positions
     * @param lat1 latitude of the 1st position
     * @param lon1 longitude of the 1st position
     * @param lat2 latitude of the 2nd position
     * @param lon2 longitude of the 2nd position
     * @return the distance in miles between the two positions
     */
    @GetMapping("/calculateDistance")
    public double calculateDistance(@RequestParam(value = "lat1")Double lat1, @RequestParam(value = "lon1")Double lon1,@RequestParam(value = "lat2")Double lat2, @RequestParam(value = "lon2")Double lon2){
        logger.info("request at /calculateDistance");
        return DistanceCalculator.distance(lat1,lon1,lat2,lon2);
    }
}
