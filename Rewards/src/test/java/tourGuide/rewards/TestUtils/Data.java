package tourGuide.rewards.TestUtils;

import com.con.Entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Data {
    public static List<Attraction> getAttractionList(){
        List<Attraction> attractions = new ArrayList();
        attractions.add(new Attraction("Disneyland", "Anaheim", "CA",UUID.fromString("648ed5ea-b766-4aee-a0b7-3686e166c977"), 33.817595D, -117.922008D));
        attractions.add(new Attraction("Jackson Hole", "Jackson Hole", "WY",UUID.fromString("166e2162-f208-4fa5-9811-5e15da14839b"), 43.582767D, -110.821999D));
        attractions.add(new Attraction("Mojave National Preserve", "Kelso", "CA",UUID.randomUUID(), 35.141689D, -115.510399D));
        attractions.add(new Attraction("Joshua Tree National Park", "Joshua Tree National Park", "CA",UUID.randomUUID(), 33.881866D, -115.90065D));
        attractions.add(new Attraction("Buffalo National River", "St Joe", "AR", UUID.randomUUID(),35.985512D, -92.757652D));
        attractions.add(new Attraction("Hot Springs National Park", "Hot Springs", "AR",UUID.randomUUID(), 34.52153D, -93.042267D));
        return attractions;
    }

    public static Attraction getOneAttraction(){
        return new Attraction("Disneyland", "Anaheim", "CA",UUID.randomUUID(), 33.817595D, -117.922008D);
    }

    public static User getAUser(){
        User user = new User(UUID.randomUUID(),"username","012345","user@user.user");
        user.getVisitedLocations().add(new VisitedLocation(user.getUserId(),new Location(-117.922008D,33.817595D),null));
        user.getVisitedLocations().add(new VisitedLocation(user.getUserId(),new Location(-110.821999D,43.582767D),null));
        return user;
    }

    public static Attraction getDisneyAttraction(){
        return getAttractionList().get(0);
    }

    public static Attraction getJacksonAttraction(){
        return getAttractionList().get(1);
    }
}
