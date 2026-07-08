package com.example.demo.util;



public class GeoUtils {

    private GeoUtils() {
    }

    /**
     * Calculates an interpolated latitude.
     *
     * @param startLat Starting latitude
     * @param endLat Ending latitude
     * @param progress Journey progress (0.0 - 1.0)
     * @return Current latitude
     */
    public static double interpolateLatitude(double startLat,
                                             double endLat,
                                             double progress) {

        return startLat + (endLat - startLat) * progress;

    }

    /**
     * Calculates an interpolated longitude.
     *
     * @param startLng Starting longitude
     * @param endLng Ending longitude
     * @param progress Journey progress (0.0 - 1.0)
     * @return Current longitude
     */
    public static double interpolateLongitude(double startLng,
                                              double endLng,
                                              double progress) {

        return startLng + (endLng - startLng) * progress;

    }

    /**
     * Calculates both latitude and longitude.
     *
     * Returns:
     * position[0] = latitude
     * position[1] = longitude
     */
    public static double[] interpolate(double startLat,
                                       double startLng,
                                       double endLat,
                                       double endLng,
                                       double progress) {

        progress = Math.max(0.0, Math.min(progress, 1.0));

        double latitude =
                interpolateLatitude(startLat, endLat, progress);

        double longitude =
                interpolateLongitude(startLng, endLng, progress);

        return new double[] { latitude, longitude };

    }

    /**
     * Distance between two coordinates (Haversine Formula)
     *
     * Returns distance in kilometers.
     */
    public static double calculateDistance(double lat1,
                                           double lon1,
                                           double lat2,
                                           double lon2) {

        final double R = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a =
                Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2)
                * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;

    }

}