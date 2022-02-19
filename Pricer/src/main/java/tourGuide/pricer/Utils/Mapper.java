package tourGuide.pricer.Utils;

import tourGuide.pricer.Entity.Provider;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    private Mapper() {
        //Empty constructor
    }

    /**
     * Method who convert a List<tripPricer.Provider> to a Entity.List<Provider>
     * @param providerList List<tripPricer.Provider>
     * @return Entity.List<Provider>
     */
    public static List<Provider> mapToEntityProvider(List<tripPricer.Provider> providerList){
        List<Provider> entityProviderList = new ArrayList<>();
        for(tripPricer.Provider provider : providerList){
            entityProviderList.add(new Provider(provider.name,provider.price,provider.tripId));
        }
        return entityProviderList;
    }
}
