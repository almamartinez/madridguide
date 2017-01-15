package io.keepcoding.madridguide.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;

import java.util.LinkedList;
import java.util.List;

import io.keepcoding.madridguide.R;
import io.keepcoding.madridguide.fragments.ActivitiesFragment;
import io.keepcoding.madridguide.interactors.GetAllActivitiesInteractor;
import io.keepcoding.madridguide.model.Activities;
import io.keepcoding.madridguide.model.Activity;
import io.keepcoding.madridguide.navigator.Navigator;
import io.keepcoding.madridguide.util.OnElementClick;
import io.keepcoding.madridguide.util.map.MapHelper;
import io.keepcoding.madridguide.util.map.MapPinsAdder;

public class ActivitiesActivity extends AppCompatActivity implements GoogleMap.OnInfoWindowClickListener {

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
                        Navigator.navigateFromActivitiesActivityToActivityDetailActivity(ActivitiesActivity.this, element);
                    }
                });

                activitiesFragment.setActivities(activities);
                ActivitiesActivity.this.activities = activities;
                initializeMap();
            }
        });
    }


    private void initializeMap() {
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                // check if map is created successfully or not
                if (googleMap == null) {
                    Toast.makeText(getApplicationContext(),
                            "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                            .show();
                } else {

                    setupMap(googleMap);

                    if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    googleMap.setMyLocationEnabled(true);
                    googleMap.getUiSettings().setRotateGesturesEnabled(false);

                }
            }
        });

    }


    private void setupMap(GoogleMap googleMap) {
        final float lat = 40.415363f;
        final float lon = -3.707398f;

        MapHelper.centerMapInPosition(googleMap, lat, lon);

        if (activities == null) {
            Snackbar.make(mapFragment.getView(), R.string.no_shops, Snackbar.LENGTH_INDEFINITE)
                    .setAction("Action", null)
                    .show();
            return;
        }
        List<MapPinsAdder.MapPinnable> pins = new LinkedList<>();
        for (Activity activity : activities.getAll()) {
            MapPinsAdder.MapPinnable pin = activity;

            pins.add(pin);
        }

        MapPinsAdder.addPins(pins, googleMap, this);

        googleMap.setOnInfoWindowClickListener(this);

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if (marker.getTag() != null) {
            Activity activity = (Activity) marker.getTag();

            Navigator.navigateFromActivitiesActivityToActivityDetailActivity(this, activity);
        }
    }
}