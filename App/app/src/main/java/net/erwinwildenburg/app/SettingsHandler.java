package net.erwinwildenburg.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;


public class SettingsHandler {

    Context context = null;

    public SettingsHandler(Context context) {
        this.context = context;
        try {
            // Check if the location is enabled and set to high accuracy mode
            if (Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE) != Settings.Secure.LOCATION_MODE_HIGH_ACCURACY) {
                // Show alert with a question to fix the
                ShowAlert("Set location services to high accuracy?", Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void ShowAlert(String message, final String positiveAction) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert)
            .setTitle(message)
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // We send the user to the location settings page so he/she can change the settings accordingly
                    context.startActivity(new Intent(positiveAction));
                }
            })
            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // We don't have to do anything when the user doesn't want to change this
                }
            })
            .setIcon(android.R.drawable.ic_dialog_alert);

        builder.show();
    }

}
