package tourGuide.Utils;

import org.javamoney.moneta.Money;
import tourGuide.DTO.*;
import tourGuide.Entity.*;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.*;
import java.util.stream.Collectors;

public class Data {

    public static Attraction getOneAttraction1(){
        return new Attraction("Disneyland", "Anaheim", "CA",UUID.fromString("648ed5ea-b766-4aee-a0b7-3686e166c977"));
    }

    public static Attraction getOneAttraction2(){
        return new Attraction("Jackson Hole", "Jackson Hole", "WY",UUID.fromString("166e2162-f208-4fa5-9811-5e15da14839b"));
    }

    public static UserReward getUserReward1(UUID userId){
        return new UserReward(new VisitedLocation(userId,new Location(10,10),null), Data.getOneAttraction1());
    }

    public static UserReward getUserReward2(UUID userId){
        return new UserReward(new VisitedLocation(userId,new Location(100,100),null), Data.getOneAttraction2());
    }

    public static UserPreferences getUserPreferences(){
        CurrencyUnit currency = Monetary.getCurrency("USD");
        return new UserPreferences(10, currency, Money.of(100,currency),Money.of(200,currency),7,4,2,3);
    }

    public static UserPreferencesDTO getUserPreferencesDTO(){
        return new UserPreferencesDTO(10,"USD",100,200,7,4,2,3);
    }

    public static User getUser1(){
        User user1 = new User(UUID.fromString("648ed5ea-b766-4aee-a0b7-3686e166c977"),"username","012345","user@user.user");
        user1.getVisitedLocations().add(new VisitedLocation(user1.getUserId(),new Location(-117.922008D,33.817595D),null));
        user1.getVisitedLocations().add(new VisitedLocation(user1.getUserId(),new Location(-110.821999D,43.582767D),null));
        user1.setUserPreferences(getUserPreferences());
        return user1;
    }

    public static UserDTO getUserDTO1(){
        UserDTO user1 = new UserDTO(UUID.fromString("648ed5ea-b766-4aee-a0b7-3686e166c977"),"username","012345","user@user.user", getUserPreferencesDTO());
        user1.getVisitedLocations().add(new VisitedLocation(user1.getUserId(),new Location(-117.922008D,33.817595D),null));
        user1.getVisitedLocations().add(new VisitedLocation(user1.getUserId(),new Location(-110.821999D,43.582767D),null));
        return user1;
    }

    public static User getUser2(){
        User user2 = new User(UUID.fromString("648ed5ea-b766-4aee-a0b7-3677e166c977"),"username2","0123453","user2@user2.user");
        user2.getVisitedLocations().add(new VisitedLocation(user2.getUserId(),new Location(-110.922008D,93.817595D),null));
        user2.getVisitedLocations().add(new VisitedLocation(user2.getUserId(),new Location(-110.821999D,93.582767D),null));
        user2.setUserPreferences(getUserPreferences());
        return user2;
    }

    public static UserDTO getUserDTO2(){
        UserDTO user2 = new UserDTO(UUID.fromString("648ed5ea-b766-4aee-a0b7-3677e166c977"),"username2","0123453","user2@user2.user",getUserPreferencesDTO());
        user2.getVisitedLocations().add(new VisitedLocation(user2.getUserId(),new Location(-110.922008D,93.817595D),null));
        user2.getVisitedLocations().add(new VisitedLocation(user2.getUserId(),new Location(-110.821999D,93.582767D),null));
        return user2;
    }

    public static List<NearbyAttractionsDTO> getFiveNearestLocations(){
        Location location = new Location(7.5381817317681765,117.45241599562848);
        List<NearbyAttractionsDTO> expected = new ArrayList<>();
        expected.add(new NearbyAttractionsDTO(new Attraction(
                "McKinley Tower",
                "Anchorage",
                "AK",
                UUID.fromString("203873ae-3673-4389-a76d-75ddc49f0eab")),
                location,
                737.1848933242989,
                1)
        );

        expected.add(new NearbyAttractionsDTO(new Attraction(
                "Jackson Hole",
                "Jackson Hole",
                "WY",
                UUID.fromString("9ccdd0bf-83d8-4cd6-bb29-022e0463a049")),
                location,
                2736.665927044635,
                1)
        );

        expected.add(new NearbyAttractionsDTO(new Attraction(
                "Mojave National Preserve",
                "Kelso",
                "CA",
                UUID.fromString("0712fb6d-e22c-4936-9e32-8144095af7fc")),
                location,
                3056.832920733511,
                1)
        );

        expected.add(new NearbyAttractionsDTO(new Attraction(
                "Disneyland",
                "Anaheim",
                "CA",
                UUID.fromString("770b82d4-2c78-4214-b320-324a55f3822f")),
                location,
                3058.485830744802,
                1)
        );

        expected.add(new NearbyAttractionsDTO(new Attraction(
                "Joshua Tree National Park",
                "Joshua Tree National Park",
                "CA",
                UUID.fromString("f9a2cb62-dee7-4851-a7ae-131f7b0eca93")),
                location,
                3117.438569890783,
                1)
        );

        return expected;
    }

    public static VisitedLocation getLastVisitedLocationOfUser(){
        return new VisitedLocation(UUID.fromString("648ed5ea-b766-4aee-a0b7-3686e166c977"),new Location(-110.821999D,43.582767D),null);
    }

    public static List<Provider> getProviderList(){
        return new ArrayList<>(Arrays.asList(
                new Provider("provider1",100.0,UUID.fromString("f9a2cb62-dee7-4851-a7ae-131f7b0eca93")),
                new Provider("provider2",200.0,UUID.fromString("f9a2cb89-dee7-4851-a7ae-131f7b0eca93"))
        ));
    }

    public static List<UserDTO> getAllUsersDTO(){
        return new ArrayList<>(Arrays.asList(
                getUserDTO1(),getUserDTO2()
        ));
    }

    public static List<User> getAllUsers(){
        return new ArrayList<>(Arrays.asList(
                getUser1(),getUser2()
        ));
    }

    public static Map<UUID, Location> getAllUsersWithLocation(){
        return getAllUsers().stream().collect(Collectors.toMap(User::getUserId, user -> mapLocation(user.getVisitedLocations().get(0).getLocation())));
    }

    public static Location mapLocation(Location location){
        return new Location(location.getLongitude(), location.getLatitude());
    }

}
