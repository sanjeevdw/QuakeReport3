package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* {@link EarthquakeAdapter) is an (@link ArrayAdapter) that can provide the layout for each list
 * based on a data source, which is a list of {@link Earthquake} objects.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOG_TAG = EarthquakeAdapter.class.getSimpleName();

    private static final String LOCATION_SEPARATOR = "of";

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);

        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    // return the color for the magnitude circle based on the intensity of the earthquake.
    // @param magnitude of the earthquake


/*private String getMagnitudeColor(int magnitudeColor) {
      GradientDrawable gradientDrawable = new GradientDrawable();
      return gradientDrawable.getColor(magnitudeColor); */

    /* This is our own custom constructor (it does'nt mirror the super class constructor).
     * The context is used to inflate layout file, and the list is the data we want
     * populate into the lists
     * @param context. The current context. Used to inflate the layout file.
     * @param earthquakes A list of Earthquake objects to display in a list.

     */
    public EarthquakeAdapter(@NonNull Activity context, ArrayList<Earthquake> earthquakes) {
        /* Here we initialize the ArrayAdapter's initial storage for the context and the list.
         * The second argument is used when ArrayAdapter populates a single TextView.
         * Because this is a custom adapter for three TextViews, the adapter is not
         * going to use the second argument, so it can be any value. Here, we used 0.
         */
        super(context, 0, earthquakes);
    }

    /**
     * Provides a view for an AdapterView(ListView, GridView etc)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return the View for the position in the AdapterView.
     */

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {

            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the {@link Earthquake} object located at this position in the list.

        Earthquake currentEarthquake = getItem(position);

        // Find the TextView in the activity_earthquake layout XML with the ID magnitude_view

        // Create a new magnitude object from the time in milliseconds of the earthquake

        //  Double magnitudeObject = new Double(currentEarthquake.getMagnitude());

        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);

        // Format the magnitude as string (i.e. "0.00")
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());

        // Display the magnitude of the current earthquake in that TextView
        magnitudeView.setText(formattedMagnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color on the current earthquake magnitude.
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // Get the magnitude from the current Earthquake object and
        // set the text on the magnitude TextView
        // magnitudeView.setText(currentEarthquake.getMagnitude());

        //Store the original location in a variable.

        String originalLocation = currentEarthquake.getLocation();
        String primaryLocation;
        String locationOffset;

        // Check if the original location has "of" in the string

        if (originalLocation.contains(LOCATION_SEPARATOR))
        {

            String[] parts = originalLocation.split(LOCATION_SEPARATOR);

            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        }
        else {

            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        TextView locationPrimaryView = (TextView) listItemView.findViewById(R.id.primary_location);

        // Get the location from the current Earthquake object and
        // set the text on the location TextView
        locationPrimaryView.setText(primaryLocation);

        // Find the TextView in the activity_earthquake layout XML with the ID location_view

        TextView offsetLocationView = (TextView) listItemView.findViewById(R.id.location_offset);

        // Get the location from the current Earthquake object and
        // set the text on the location TextView
        offsetLocationView.setText(locationOffset);

        // Find the TextView in the activity_earthquake layout XML with the ID location_view

        // Find the TextView in the activity_earthquake layout XML with the ID location_view

        // TextView locationView = (TextView) listItemView.findViewById(R.id.location_view);

        // Get the location from the current Earthquake object and
        // set the text on the location TextView
        // locationView.setText(currentEarthquake.getLocation());

        // Create a new Date object from the time in milliseconds of the earthquake

        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        // Find the TextView in the activity_earthquake layout XML with the ID location_view
        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date_view);

        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);

        // Display the date of the current earthquake in that TextView

        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time_view);

        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);

        // Display the date of the current earthquake in that TextView
        timeView.setText(formattedTime);

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }
}
