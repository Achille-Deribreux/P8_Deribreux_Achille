package tourGuide.user.Utils;

import org.javamoney.moneta.Money;
import tourGuide.user.Entity.Location;
import tourGuide.user.Entity.User;
import tourGuide.user.Entity.UserPreferences;
import tourGuide.user.Entity.VisitedLocation;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Data {

    public static User getAUser(){
        User user = new User(UUID.fromString("648ed5ea-b766-4aee-a0b7-3686e166c977"),"username","012345","user@user.user");
        user.getVisitedLocations().add(new VisitedLocation(user.getUserId(),new Location(-117.922008D,33.817595D),null));
        user.getVisitedLocations().add(new VisitedLocation(user.getUserId(),new Location(-110.821999D,43.582767D),null));
        return user;
    }

    public static VisitedLocation getLastVisitedLocationOfUser(){
        return new VisitedLocation(UUID.fromString("648ed5ea-b766-4aee-a0b7-3686e166c977"),new Location(-110.821999D,43.582767D),null);
    }

    public static User getUser1(){
        User user1 = new User(UUID.fromString("648ed5ea-b766-4aee-a0b7-3686e166c977"),"username","012345","user@user.user");
        user1.getVisitedLocations().add(new VisitedLocation(user1.getUserId(),new Location(-117.922008D,33.817595D),null));
        user1.getVisitedLocations().add(new VisitedLocation(user1.getUserId(),new Location(-110.821999D,43.582767D),null));
        user1.setUserPreferences(generateUserPreferences());
        return user1;
    }

    public static User getUser2(){
        User user2 = new User(UUID.fromString("648ed5ea-b766-4aee-a0b7-3677e166c977"),"username2","0123453","user2@user2.user");
        user2.getVisitedLocations().add(new VisitedLocation(user2.getUserId(),new Location(-110.922008D,93.817595D),null));
        user2.getVisitedLocations().add(new VisitedLocation(user2.getUserId(),new Location(-110.821999D,93.582767D),null));
        user2.setUserPreferences(generateUserPreferences());
        return user2;
    }


    public static List<User> userList(){
        return new ArrayList<>(Arrays.asList(getUser1(),getUser2()));
    }

    public static Map<UUID, Location> getAllUsersWithLocation(){
        return userList().stream().collect(Collectors.toMap(User::getUserId, user -> user.getLastVisitedLocation().getLocation()));
    }

    public static  Map<String, User> getInternalUserMap(){
        Map<String, User> internalUserMap = new HashMap<>();
        User user1 = getUser1();
        User user2 =getUser2();
        internalUserMap.put(user1.getUserName(),user1);
        internalUserMap.put(user2.getUserName(),user2);

        return internalUserMap;
    }

    private static UserPreferences generateUserPreferences(){
        CurrencyUnit currency = Monetary.getCurrency("USD");
        return new UserPreferences(Integer.MAX_VALUE,currency, Money.of(1000,currency), Money.of(5000,currency),10,4,2,3);
    }
}
