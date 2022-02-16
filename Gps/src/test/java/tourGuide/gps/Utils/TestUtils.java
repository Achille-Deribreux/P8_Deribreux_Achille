package tourGuide.gps.Utils;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import tourGuide.gps.DTO.NearbyAttractionsDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestUtils {

    static GpsUtil gpsUtil = new GpsUtil();

    public static List<Attraction> getAttractions(){
        return gpsUtil.getAttractions();
    }

    public static List<tourGuide.gps.Entity.Attraction> mapedAttractionList(){
        return mapAttraction(getAttractions());
    }

    public static List<tourGuide.gps.Entity.Attraction> mapAttraction(List<Attraction> attractionList){
        List<tourGuide.gps.Entity.Attraction> mappedAttractionList = new ArrayList<>();

        for(Attraction attraction : attractionList){
            mappedAttractionList.add(
                    new tourGuide.gps.Entity.Attraction(
                            attraction.attractionId,
                            attraction.attractionName,
                            attraction.city,
                            attraction.state,
                            attraction.latitude,
                            attraction.longitude
                    )
            );
        }
        return  mappedAttractionList;
    }

    public static Attraction reverseMapping(tourGuide.gps.Entity.Attraction attraction){
        return new Attraction(attraction.getAttractionName(),attraction.getCity(),attraction.getState(), attraction.getLatitude(), attraction.getLongitude());
    }

    public static List<NearbyAttractionsDTO> getFiveNearestLocations(){
        tourGuide.gps.Entity.Location location = new tourGuide.gps.Entity.Location(7.5381817317681765,117.45241599562848);
        List<NearbyAttractionsDTO> expected = new ArrayList<>();
        expected.add(new NearbyAttractionsDTO(new tourGuide.gps.Entity.Attraction(
                UUID.fromString("203873ae-3673-4389-a76d-75ddc49f0eab"),
                "McKinley Tower",
                "Anchorage",
                "AK",
                61.218887,
                -149.877502),
                location,
                737.1848933242989,
                1)
        );

        expected.add(new NearbyAttractionsDTO(new tourGuide.gps.Entity.Attraction(
                UUID.fromString("9ccdd0bf-83d8-4cd6-bb29-022e0463a049"),
                "Jackson Hole",
                "Jackson Hole",
                "WY",
                43.582767,
                -110.821999),
                location,
                2736.665927044635,
                1)
        );

        expected.add(new NearbyAttractionsDTO(new tourGuide.gps.Entity.Attraction(
                UUID.fromString("0712fb6d-e22c-4936-9e32-8144095af7fc"),
                "Mojave National Preserve",
                "Kelso",
                "CA",
                35.141689,
                -115.510399),
                location,
                3056.832920733511,
                1)
        );

        expected.add(new NearbyAttractionsDTO(new tourGuide.gps.Entity.Attraction(
                UUID.fromString("770b82d4-2c78-4214-b320-324a55f3822f"),
                "Disneyland",
                "Anaheim",
                "CA",
                33.817595,
                -117.922008),
                location,
                3058.485830744802,
                1)
        );

        expected.add(new NearbyAttractionsDTO(new tourGuide.gps.Entity.Attraction(
                UUID.fromString("f9a2cb62-dee7-4851-a7ae-131f7b0eca93"),
                "Joshua Tree National Park",
               "Joshua Tree National Park",
                "CA",
                33.881866,
                -115.90065),
                location,
                3117.438569890783,
                1)
        );

        return expected;
    }
}
