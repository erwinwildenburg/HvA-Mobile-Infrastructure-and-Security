package net.erwinwildenburg.app;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LocationHandler locationHandler = null;
    private SettingsHandler settingsHandler = null;

    public TextView currentLongitude = null;
    public TextView currentLatitude = null;
    public TextView currentAltitude = null;
    public TextView currentSpeed = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        currentLongitude = findViewById(R.id.textView_longitude_value);
        currentLatitude = findViewById(R.id.textView_latitude_value);
        currentAltitude = findViewById(R.id.textView_altitude_value);
        currentSpeed = findViewById(R.id.textView_speed_value);

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
}
