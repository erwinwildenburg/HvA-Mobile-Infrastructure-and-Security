package net.erwinwildenburg.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.preference.PreferenceManager;

public class MotionHandler implements SensorEventListener {
    private Context context = null;
    public SensorManager sensorManager = null;
    public Sensor stepListener = null;
    private SharedPreferences sharedPreferences = null;
    private MainActivity mainActivity = null;

    public static int steps = 0;

    public MotionHandler(Context context)
    {
        this.context = context;
        this.sensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.stepListener = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        this.mainActivity = (MainActivity) context;

        // Register the sensor event listener so we get updates when a step is made
        sensorManager.registerListener(this, stepListener, sensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // Check if the user is walking
        switch(sensorEvent.sensor.getType()) {
            case Sensor.TYPE_STEP_COUNTER:
                // Increase the step counter
                mainActivity.steps++;
                System.out.println("!!!!! STEP DETECTED !!!!!!");

                // Run on the UI thread so the user can see the results
                mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    // Update the values on the MainActivity with what we got from the sensor
                    mainActivity.currentSteps.setText(String.format(Integer.toString(mainActivity.steps)));
                    }
                });
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
