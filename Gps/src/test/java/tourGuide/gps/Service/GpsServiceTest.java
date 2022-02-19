package tourGuide.gps.Service;

import com.google.common.base.Verify;
import gpsUtil.GpsUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tourGuide.gps.DTO.AttractionWithDistanceFromUserDTO;
import tourGuide.gps.DTO.NearbyAttractionsDTO;
import tourGuide.gps.Entity.Attraction;
import tourGuide.gps.Entity.Location;
import tourGuide.gps.Utils.DistanceCalculator;
import tourGuide.gps.Utils.TestUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class GpsServiceTest {

    @MockBean
    GpsUtil gpsUtil;

    @Autowired
    GpsService gpsService;

    @BeforeEach
    void setUp() {
        gpsService.setGpsUtil(gpsUtil);
    }


    @Test
    void trackUserLocationTest(){
        //Given
        UUID userId = UUID.randomUUID();
        //when
        gpsService.trackUserLocation(userId);
        //Then
        verify(gpsUtil, Mockito.times(1)).getUserLocation(userId);
    }

    @Test
    void getNearByAttractionsTest() {
        //Given
        Location location = new Location(117.45241599562848,7.5381817317681765);
        List<NearbyAttractionsDTO> expected = TestUtils.getFiveNearestLocations();
        List<NearbyAttractionsDTO> result;
        //When
        Mockito.when(gpsUtil.getAttractions()).thenReturn(TestUtils.getAttractions());
        result=gpsService.getNearByAttractions(location.getLatitude(),location.getLongitude(),5);
        //Then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void getAttractionListWithDistanceFromUserTest() {
        //Given
        Location location = new Location(7.5381817317681765,117.45241599562848);
        List<AttractionWithDistanceFromUserDTO> expected = new ArrayList<>();
        List<AttractionWithDistanceFromUserDTO> result;

        for(Attraction attraction : TestUtils.mapedAttractionList()) {
            expected.add(new AttractionWithDistanceFromUserDTO(attraction, DistanceCalculator.distance(location.getLatitude(), location.getLongitude(), attraction.getLatitude(), attraction.getLongitude())));
        }
        expected = expected.stream().sorted(Comparator.comparing(AttractionWithDistanceFromUserDTO::getDistanceFromUser)).collect(Collectors.toList());

        //When
        Mockito.when(gpsUtil.getAttractions()).thenReturn(TestUtils.getAttractions());
        result = gpsService.getAttractionListWithDistanceFromUser(location);
        //Then
        Assertions.assertEquals(expected, result);
    }
}
