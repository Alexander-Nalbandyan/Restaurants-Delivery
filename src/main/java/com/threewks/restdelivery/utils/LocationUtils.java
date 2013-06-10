package com.threewks.restdelivery.utils;

import com.google.code.geocoder.model.LatLng;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 6/9/13
 * Time: 8:35 PM
 *
 * Provides Geo Location utility methods.
 */
public class LocationUtils {

    public enum DISTANCE_UNIT {
        KILOMETERS, MILES
    }

    private LocationUtils() {}

    /**
     * Calculates distance between provided geo locations in provided distance unit.
     * @param loc1      First location
     * @param loc2      Second location
     * @return distance in provided unit
     */
    public static double calculateDistance(LatLng loc1, LatLng loc2,
                                           DISTANCE_UNIT distance_unit) {
        return calculateDistance(loc1.getLat().doubleValue(),
                                loc1.getLng().doubleValue(),
                                loc2.getLat().doubleValue(),
                                loc2.getLng().doubleValue(),
                                distance_unit);
    }

    public static double calculateDistance(double lat1, double lng1, double lat2, double lng2, DISTANCE_UNIT distance_unit) {
        double earthRadius = -1;
        switch (distance_unit) {
            case KILOMETERS:  {
                earthRadius = 6371d;
                break;
            }

            case MILES: {
                earthRadius = 3959d;
                break;
            }

        }

        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = earthRadius * c;

        return dist;
    }
}
