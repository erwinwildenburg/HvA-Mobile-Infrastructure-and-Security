package net.erwinwildenburg.app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

// Imports for Google Maps widget
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.concurrent.ExecutionException;

import io.swagger.client.model.Status;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    public LocationHandler locationHandler = null;
    public MotionHandler motionHandler = null;
    private SettingsHandler settingsHandler = null;

    public int speed = 0;
    public TextView currentSpeed = null;
    public int steps = 0;
    public TextView currentSteps = null;

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    private static final String STEPS_INT_KEY = "MapViewBundleKey";
    private static final String SPEED_INT_KEY = "MapViewBundleKey";

    public MapView mapView = null;
    public GoogleMap googleMap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the mapView
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            // Restore the mapView
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);

            // Restore the steps
            steps = savedInstanceState.getInt(STEPS_INT_KEY);
            speed = savedInstanceState.getInt(SPEED_INT_KEY);
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the map view
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

        // Register the textViews so we can update their values
        currentSpeed = findViewById(R.id.textView_speed_value);
        currentSpeed.setText(String.format(Integer.toString(speed)));
        currentSteps = findViewById(R.id.textView_step_count_value);
        currentSteps.setText(String.format(Integer.toString(steps)));

        // Initialize sensor handlers
        // These handlers will take care of reading the sensor data
        this.motionHandler = new MotionHandler(this);
        this.locationHandler = new LocationHandler(this);
        this.settingsHandler = new SettingsHandler(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        }
        if (id == R.id.action_refresh) {
            refreshMapMarkers();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 100: // LocationHandler
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    this.locationHandler = new LocationHandler(this);
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);

        // Save our current steps and speed in the savedInstanceState
        outState.putInt(STEPS_INT_KEY, steps);
        outState.putInt(SPEED_INT_KEY, speed);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
        if (motionHandler != null) {
            motionHandler.sensorManager.registerListener(motionHandler, motionHandler.stepListener,
                    motionHandler.sensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();

        if (motionHandler != null) {
            motionHandler.sensorManager.unregisterListener(motionHandler);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setMinZoomPreference(12);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        }

        if (locationHandler != null) {
            // Get the current location from the location sensor
            Location location = locationHandler.getCurrentLocation();

            // Move the camera of the mapView to our location
            LatLng curLoc = new LatLng(location.getLatitude(), location.getLongitude());
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(curLoc));

            refreshMapMarkers();
        }
    }

    public void refreshMapMarkers() {
        if (googleMap == null) { return; }

        // Get the unique identifier of this device
        String id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);

        // Get a list of all pointers we want to show on the map
        List<Status> statuses = null;
        try {
            statuses = new ApiGetHandler().execute(id).get();
            if (statuses == null) { return; }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (Status status : statuses) {
            // Create the object location from the latitude and longitude location
            LatLng pointerLoc = new LatLng(Double.parseDouble(status.getLatitude()),
                    Double.parseDouble(status.getLongitude()));

            // Create the map marker
            MarkerOptions marker = new MarkerOptions();
            marker.position(pointerLoc);
            marker.title(status.getRowKey());
            marker.snippet("Steps: " + status.getSteps());
            googleMap.addMarker(marker);
        }
    }
}
