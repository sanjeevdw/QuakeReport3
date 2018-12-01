package com.example.android.quakereport;

import android.support.v7.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Earthquake {

    private double mMagnitude;
    private String mLocation;
    private long mTimeInMilliseconds;
    private String mUrl;

    //  private int mMagnitudeColor;
    /**
     * Constructs a new {@link Earthquake} object.
     *
     * @param magnitude is the magnitude (size) of the earthquake
     * @param location is the city location of the earthquake
     * @param timeInMilliseconds is the time in milliseconds (from the Epoch) when the
     *  earthquake happened
     */

    public Earthquake(double magnitude, String location, long timeInMilliseconds, String Url) {
       // super();
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl= Url;
    }

    public double getMagnitude() {

        return mMagnitude;
    }

    public String getLocation() {

        return mLocation;
    }

    public long getTimeInMilliseconds() {

        return mTimeInMilliseconds;
    }

    public String getUrl() {

        return mUrl;
    }
}
