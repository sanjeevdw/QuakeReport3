package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.net.URL;
import java.util.List;

/**
 * Loads a list of earthquakes by using an AsyncTask to perform
 * network request to the given URL
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>>{

    // Tag for log messages
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    // Query URL
    private String mURL;

    /**
     * Constructs a new {@link EarthquakeLoader}
     *
     * @param context of the activity
     * @param url to load data from
     */
    public EarthquakeLoader(@NonNull Context context, String url) {
        super(context);
        // TODO; Finish implementing this constructor.
        mURL = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    // This is on a background thread.

    @Nullable
    @Override
    public List<Earthquake> loadInBackground() {
        // TODO; Implement this method.
        // Don't perform the request if there are no URLs, or the first URL is null.
        if (mURL == null) {
            return null;
        }

        // Performs the network request, parse the JSON response, and extract a list of earthquakes.
        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mURL);
        return earthquakes;
    }
}