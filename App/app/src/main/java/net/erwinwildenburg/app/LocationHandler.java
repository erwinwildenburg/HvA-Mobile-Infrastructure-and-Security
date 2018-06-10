package net.erwinwildenburg.app;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import org.joda.time.DateTime;

import io.swagger.client.ApiException;
import io.swagger.client.api.ValuesApi;
import io.swagger.client.model.Status;

public class LocationHandler implements LocationListener {

    private Context context = null;
    private LocationManager locationManager = null;
    private SharedPreferences sharedPreferences = null;
    private MainActivity mainActivity = null;

    public LocationHandler(Context context) {
        this.context = context;
        this.locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.mainActivity = (MainActivity) context;

        // Check if we have the required permissions
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Request permissions to access accurate location
            ActivityCompat.requestPermissions((MainActivity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        } else {
            // Register the listener so we get triggered when a location update is available
            onLocationChanged(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);
        }
    }

    @Override
    public void onLocationChanged(final Location location) {
        // Check if we actually got a value
        if (location == null) return;

        final String speed = String.format(Double.toString(location.getSpeed()) + " km/h");

        // Run on the UI thread so the user can see the results
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Update the values on the MainActivity with what we got from the LocationListener
                mainActivity.currentSpeed.setText(speed);
                //mainActivity.currentSteps.setText(String.format(Integer.toString(mainActivity.steps)));

                // Update the mapView to show our current location
                if (mainActivity.googleMap != null) {
                    // Move the camera to the current location
                    LatLng curLoc = new LatLng(location.getLatitude(), location.getLongitude());
                    mainActivity.googleMap.moveCamera(CameraUpdateFactory.newLatLng(curLoc));
                }
            }
        });

        // Add the current location to the status object
        Status status = new Status();
        status.setLongitude(Double.toString(location.getLongitude()));
        status.setLatitude(Double.toString(location.getLatitude()));
        status.setAltitude(Double.toString(location.getAltitude()));
        status.setSpeed(Float.toString(location.getSpeed()));
        status.setSteps(Integer.toString(mainActivity.steps));

        // We use the android unique identifier to identify this device
        status.setPartitionKey(Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID));

        new ApiPostHandler().execute(status);
        mainActivity.refreshMapMarkers();
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

    public Location getCurrentLocation() {
        // Check if we have the required permissions
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Request permissions to access accurate location
            ActivityCompat.requestPermissions((MainActivity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        }

        // Request the current location from the GPS chip
        return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

}
