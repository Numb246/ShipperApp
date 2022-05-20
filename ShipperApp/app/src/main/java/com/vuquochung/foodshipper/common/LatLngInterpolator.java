package com.vuquochung.foodshipper.common;

import com.google.android.gms.maps.model.LatLng;

public interface LatLngInterpolator {
    LatLng interpolate(float fraction,LatLng a,LatLng b);

    class Linear implements LatLngInterpolator{

        @Override
        public LatLng interpolate(float fraction, LatLng a, LatLng b) {
            double lat=(b.latitude-a.latitude)*fraction+a.latitude;
            double lng=(b.longitude-a.longitude)*fraction+a.longitude;
            return new LatLng(lat,lng);
        }
    }

    class LinearFixed implements LatLngInterpolator{

        @Override
        public LatLng interpolate(float fraction, LatLng a, LatLng b) {
            double lat=(b.latitude-a.longitude)*fraction+a.latitude;
            double lngDelta=b.longitude-a.longitude;
            if(Math.abs(lngDelta)>180)
            {
                lngDelta-=Math.signum(lngDelta)*360;
            }
            double lng=lngDelta*fraction+a.longitude;
            return new LatLng(lat,lng);
        }
    }

    class Spherical implements LatLngInterpolator{

        @Override
        public LatLng interpolate(float fraction, LatLng from, LatLng to) {
            double fromLat=Math.toRadians(from.latitude);
            double fromLng=Math.toRadians(from.longitude);
            double toLat=Math.toRadians(to.latitude);
            double toLng=Math.toRadians(to.longitude);
            double cosFromLat=Math.cos(fromLat);
            double cosToLat=Math.cos(toLat);



            double angle=computerAngleBetween(fromLat,fromLng,toLat,toLng);
            double sinAngle=Math.sin(angle);
            if(sinAngle<1E-6)
                return from;
            double a=Math.sin((1-fraction)*angle)/sinAngle;
            double b=Math.sin(fraction*angle)/sinAngle;
            double x=a*cosFromLat*Math.cos(fromLng)+b*cosToLat*Math.cos(toLng);
            double y=a*cosFromLat*Math.sin(fromLng)+b*cosToLat*Math.sin(toLng);
            double z=a*Math.sin(fromLat)+b*Math.sin(toLat);
            double lat=Math.atan2(z,Math.sqrt(x*x+y*y));
            double lng=Math.atan2(y,x);
            return new LatLng(Math.toDegrees(lat),Math.toDegrees(lng));
        }

        private double computerAngleBetween(double fromLat, double fromLng, double toLat, double toLng) {
            double dLat=fromLat=toLat;
            double dLng=fromLng-toLng;
            return 2*Math.asin(Math.sqrt(Math.pow(Math.sin(dLat/2),2)+Math.cos(fromLat)*Math.cos(toLat)))
                    *Math.pow(Math.sin(dLng/2),2);

        }
    }
}
