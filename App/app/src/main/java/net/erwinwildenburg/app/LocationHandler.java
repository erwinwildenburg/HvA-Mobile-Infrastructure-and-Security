package net.erwinwildenburg.app;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class LocationHandler implements LocationListener {

    private Context context = null;
    private LocationManager locationManager = null;

    public LocationHandler(Context context) {
        this.context = context;
        this.locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);

        // Check if we have the required permissions
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((MainActivity)context, new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, 100);
        }
        else {
            onLocationChanged(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        // Check if we actually got a value
        if (location == null) return;


        final String latitude = String.format(Double.toString(location.getLatitude()));
        final String longitude = String.format(Double.toString(location.getLongitude()));
        final String altitude = String.format(Double.toString(location.getAltitude()));
        final String speed = String.format(Double.toString(location.getSpeed()));

        // TODO: Save these with the API

        // Run on the UI thread so the user can see the results
        ((MainActivity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Update the values on the MainActivity with what we got from the LocationListener
                ((MainActivity) context).currentLongitude.setText(longitude);
                ((MainActivity) context).currentLatitude.setText(latitude);
                ((MainActivity) context).currentAltitude.setText(altitude);
                ((MainActivity) context).currentSpeed.setText(speed);
            }
        });
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

}
