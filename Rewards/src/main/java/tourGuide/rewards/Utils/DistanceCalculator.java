package tourGuide.rewards.Utils;

public class DistanceCalculator {

    /**********************************************************************************
     *
     * Distance calculation below
     *
     **********************************************************************************/

    /**
     * Method who calculates the distance in miles between 2 positions
     * @param lat1 latitude 1
     * @param lon1 longitude 1
     * @param lat2 latitude 2
     * @param lon2 longitude 2
     * @return the distance between the 2 positions in miles
     */
    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
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
