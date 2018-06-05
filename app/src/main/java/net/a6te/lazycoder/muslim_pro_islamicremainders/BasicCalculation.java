package net.a6te.lazycoder.muslim_pro_islamicremainders;

import android.location.Location;

public class BasicCalculation {

    public int getDistanceBetween(double latA, double lngA, double latB, double lngB) {

//        double latA = Qlati;
//        double lngA = Qlongi;
//        double latB = latitude;
//        double lngB = longitude;

        Location locationA = new Location("point A");
        locationA.setLatitude(latA);

        locationA.setLongitude(lngA);
        Location locationB = new Location("point B");
        locationB.setLatitude(latB);
        locationB.setLongitude(lngB);
        float distance = locationA.distanceTo(locationB);
        int dis = (int) distance;

        return dis;
    }

    public double bearing(double startLat, double startLng, double endLat, double endLng){


        double longitude1 = startLng;
        double longitude2 = endLng;
        double latitude1 = Math.toRadians(startLat);
        double latitude2 = Math.toRadians(endLat);
        double longDiff= Math.toRadians(longitude2 - longitude1);
        double y= Math.sin(longDiff)* Math.cos(latitude2);
        double x= Math.cos(latitude1)* Math.sin(latitude2)- Math.sin(latitude1)* Math.cos(latitude2)* Math.cos(longDiff);

        return (Math.toDegrees(Math.atan2(y, x))+360)%360;
    }


}
