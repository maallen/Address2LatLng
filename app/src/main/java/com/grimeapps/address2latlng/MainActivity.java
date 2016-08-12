package com.grimeapps.address2latlng;

import android.location.Location;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity {

    private static String LATITUDE = "Latitude is ";
    private static String LONGITUDE = "Longitude is ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected plac
                LatLng latLng = place.getLatLng();
                String latitude = Location.convert(latLng.latitude, Location.FORMAT_SECONDS);
                String longitude = Location.convert(latLng.longitude, Location.FORMAT_SECONDS);
                TextView latText = (TextView) findViewById(R.id.latText);
                latText.setText(LATITUDE + latitude);
                TextView lngText = (TextView) findViewById(R.id.lngText);
                lngText.setText(LONGITUDE + longitude);
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("autocompleteFragment", "An error occurred: " + status);
            }
        });
    }
}
