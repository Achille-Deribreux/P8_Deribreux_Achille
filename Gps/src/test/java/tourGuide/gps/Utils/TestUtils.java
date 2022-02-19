package tourGuide.gps.Utils;

import com.con.DTO.NearbyAttractionsDTO;
import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestUtils {

    static GpsUtil gpsUtil = new GpsUtil();

    public static List<Attraction> getAttractions(){
        return gpsUtil.getAttractions();
    }

    public static List<com.con.Entity.Attraction> mapedAttractionList(){
        return mapAttraction(getAttractions());
    }

    public static List<com.con.Entity.Attraction> mapAttraction(List<Attraction> attractionList){
        List<com.con.Entity.Attraction> mappedAttractionList = new ArrayList<>();

        for(Attraction attraction : attractionList){
            mappedAttractionList.add(
                    new com.con.Entity.Attraction(
                            attraction.attractionName,
                            attraction.city,
                            attraction.state,
                            attraction.attractionId,
                            attraction.longitude,
                            attraction.latitude
                    )
            );
        }
        return  mappedAttractionList;
    }

    public static Attraction reverseMapping(com.con.Entity.Attraction attraction){
        return new Attraction(attraction.getAttractionName(),attraction.getCity(),attraction.getState(), attraction.getLatitude(), attraction.getLongitude());
    }

    public static List<NearbyAttractionsDTO> getFiveNearestLocations(){
        com.con.Entity.Location location = new com.con.Entity.Location(7.5381817317681765,117.45241599562848);
        List<NearbyAttractionsDTO> expected = new ArrayList<>();
        expected.add(new NearbyAttractionsDTO(new com.con.Entity.Attraction(
                "McKinley Tower",
                "Anchorage",
                "AK",
                UUID.fromString("203873ae-3673-4389-a76d-75ddc49f0eab"),
                -149.877502,61.218887),
                location,
                737.1848933242989,
                1)
        );

        expected.add(new NearbyAttractionsDTO(new com.con.Entity.Attraction(
                "Jackson Hole",
                "Jackson Hole",
                "WY",
                UUID.fromString("9ccdd0bf-83d8-4cd6-bb29-022e0463a049"),
                -110.821999,43.582767),
                location,
                2736.665927044635,
                1)
        );

        expected.add(new NearbyAttractionsDTO(new com.con.Entity.Attraction(
                "Mojave National Preserve",
                "Kelso",
                "CA",
                UUID.fromString("0712fb6d-e22c-4936-9e32-8144095af7fc"),

                -115.510399,35.141689),
                location,
                3056.832920733511,
                1)
        );

        expected.add(new NearbyAttractionsDTO(new com.con.Entity.Attraction(
                "Disneyland",
                "Anaheim",
                "CA",
                UUID.fromString("770b82d4-2c78-4214-b320-324a55f3822f"),
                -117.922008,33.817595),
                location,
                3058.485830744802,
                1)
        );

        expected.add(new NearbyAttractionsDTO(new com.con.Entity.Attraction(
                "Joshua Tree National Park",
               "Joshua Tree National Park",
                "CA",
                UUID.fromString("f9a2cb62-dee7-4851-a7ae-131f7b0eca93"),
                -115.90065,33.881866),
                location,
                3117.438569890783,
                1)
        );

        return expected;
    }
}
