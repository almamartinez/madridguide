package io.keepcoding.madridguide.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.LinkedList;
import java.util.List;

import io.keepcoding.madridguide.R;
import io.keepcoding.madridguide.fragments.ActivitiesFragment;
import io.keepcoding.madridguide.interactors.GetAllActivitiesInteractor;
import io.keepcoding.madridguide.model.Activities;
import io.keepcoding.madridguide.model.Activity;
import io.keepcoding.madridguide.util.OnElementClick;
import io.keepcoding.madridguide.util.map.MapHelper;
import io.keepcoding.madridguide.util.map.MapPinsAdder;

public class ActivitiesActivity extends AppCompatActivity {

    GoogleMap googleMap;

    SupportMapFragment mapFragment;
    private ActivitiesFragment activitiesFragment;
    private Activities activities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        activitiesFragment = (ActivitiesFragment) getSupportFragmentManager().findFragmentById(R.id.activity_activities_fragment_activities);

        new GetAllActivitiesInteractor().execute(this, new GetAllActivitiesInteractor.GetAllActivitiesInteractorResponse() {
            @Override
            public void response(Activities activities) {
                activitiesFragment.setListener(new OnElementClick<Activity>() {
                    @Override
                    public void clickedOn(@NonNull Activity element, int position) {
                        //Navigator.navigateFromShopsActivityToShopDetailActivity(ActivitiesActivity.this, shop);

                    }
                });

                activitiesFragment.setActivities(activities);
                ActivitiesActivity.this.activities = activities;
                setupMap();
            }
        });
    }


    private void initializeMap() {
        if (googleMap == null) {
            mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            googleMap = mapFragment.getMap();

            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            } else {
                googleMap.setMyLocationEnabled(true);
                googleMap.getUiSettings().setRotateGesturesEnabled(false);
            }
        }
    }


    private void setupMap() {
        final float lat = 40.415363f;
        final float lon = -3.707398f;

        initializeMap();
        MapHelper.centerMapInPosition(googleMap, lat, lon);

        if (activities == null) {
            Snackbar.make(mapFragment.getView(), R.string.no_shops, Snackbar.LENGTH_INDEFINITE)
                    .setAction("Action", null)
                    .show();
            return;
        }
        List<MapPinsAdder.MapPinnable> pins = new LinkedList<>();
        for (Activity activity: activities.getAll()) {
            MapPinsAdder.MapPinnable pin = activity;
            pins.add(pin);
        }

        MapPinsAdder.addPins(pins, googleMap, this);
    }
}
