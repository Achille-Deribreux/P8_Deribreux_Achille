package tourGuide.rewards.TestUtils;

import tourGuide.rewards.Entity.Location;

public class Calculator {
    public static double getDistance(Location loc1, Location loc2) {
        double lat1 = loc1.latitude;
        double lon1 = loc1.longitude;
        double lat2 = loc2.latitude;
        double lon2 = loc2.longitude;
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
