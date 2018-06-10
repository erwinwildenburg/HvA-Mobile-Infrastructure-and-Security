package net.erwinwildenburg.app;

import android.os.AsyncTask;

import java.util.List;

import io.swagger.client.ApiException;
import io.swagger.client.api.ValuesApi;

public class ApiGetHandler extends AsyncTask<String, Void, List<io.swagger.client.model.Status>> {

    @Override
    protected List<io.swagger.client.model.Status> doInBackground(String... ids) {
        // Initialize connection to the API
        ValuesApi apiInstance = new ValuesApi();

        try {
            // Get the statuses from the API
            return apiInstance.apiStatusByIdGet(ids[0]);
        } catch (ApiException e) {
            e.printStackTrace();
        }

        return null;
    }

}
