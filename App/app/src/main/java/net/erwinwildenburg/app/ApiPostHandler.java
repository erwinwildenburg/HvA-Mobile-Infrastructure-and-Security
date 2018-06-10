package net.erwinwildenburg.app;

import android.os.AsyncTask;

import org.joda.time.DateTime;

import io.swagger.client.ApiException;
import io.swagger.client.api.ValuesApi;

public class ApiPostHandler extends AsyncTask<io.swagger.client.model.Status, Void, Void> {

    @Override
    protected Void doInBackground(io.swagger.client.model.Status... statuses) {
        // Set values to the status that is required by Azure Storage
        io.swagger.client.model.Status status = statuses[0];
        status.setRowKey(DateTime.now().toString());
        status.setTimestamp(org.threeten.bp.OffsetDateTime.now());
        status.setETag("");

        // Initialize connection to the API
        ValuesApi apiInstance = new ValuesApi();

        try {
            // Sent the status to the API
            apiInstance.apiStatusPost(status);
        } catch (ApiException e) {
            e.printStackTrace();
        }

        return null;
    }

}
