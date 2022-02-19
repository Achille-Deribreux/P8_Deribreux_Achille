package tourGuide.gps.Utils;

import tourGuide.gps.Entity.Attraction;

public class Mapper {

    private Mapper(){}

    /**
     * Method who convert a GpsUtil attraction to a Gps.Entity.Attraction
     * @param attractionToMap GpsUtil attraction
     * @return Gps.Entity.Attraction
     */
    public static Attraction mapAttractionFromGpsToEntity(gpsUtil.location.Attraction attractionToMap){
        return new Attraction(attractionToMap.attractionId,
                attractionToMap.attractionName,
                attractionToMap.city,
                attractionToMap.state,
                attractionToMap.latitude,
                attractionToMap.longitude
        );
    }
}
