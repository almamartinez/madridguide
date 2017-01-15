package io.keepcoding.madridguide.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;

import java.util.LinkedList;
import java.util.List;

import io.keepcoding.madridguide.R;
import io.keepcoding.madridguide.fragments.ShopsFragment;
import io.keepcoding.madridguide.interactors.GetAllShopsFromLocalCacheInteractor;
import io.keepcoding.madridguide.manager.db.DBConstants;
import io.keepcoding.madridguide.manager.db.ShopDAO;
import io.keepcoding.madridguide.manager.db.provider.MadridGuideProvider;
import io.keepcoding.madridguide.model.Shop;
import io.keepcoding.madridguide.model.Shops;
import io.keepcoding.madridguide.navigator.Navigator;
import io.keepcoding.madridguide.util.OnElementClick;
import io.keepcoding.madridguide.util.map.MapHelper;
import io.keepcoding.madridguide.util.map.MapPinsAdder;

public class ShopsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, GoogleMap.OnInfoWindowClickListener {

    SupportMapFragment mapFragment;
    private ShopsFragment shopsFragment;
    private Shops shops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);

        shopsFragment = (ShopsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shops_fragment_shops);

        LoaderManager loaderManager = getSupportLoaderManager();
        // loaderManager.initLoader(0, null, this);

        // using interactors instead of Loaders
        GetAllShopsFromLocalCacheInteractor interactor = new GetAllShopsFromLocalCacheInteractor();
        interactor.execute(this, new GetAllShopsFromLocalCacheInteractor.OnGetAllShopsFromLocalCacheInteractorCompletion() {
            @Override
            public void completion(Shops shops) {
                shopsFragment.setListener(new OnElementClick<Shop>() {
                    @Override
                    public void clickedOn(@NonNull Shop shop, int position) {
                        Navigator.navigateFromShopsActivityToShopDetailActivity(ShopsActivity.this, shop);
                    }
                });

                shopsFragment.setShops(shops);
                ShopsActivity.this.shops = shops;
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

        if (shops == null) {
            Snackbar.make(mapFragment.getView(), R.string.no_shops, Snackbar.LENGTH_INDEFINITE)
                    .setAction("Action", null)
                    .show();
            return;
        }
        List<MapPinsAdder.MapPinnable> pins = new LinkedList<>();
        for (Shop shop: shops.getAll()) {
            MapPinsAdder.MapPinnable pin = shop;

            pins.add(pin);
        }

        MapPinsAdder.addPins(pins, googleMap, this);

        googleMap.setOnInfoWindowClickListener(this);

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if (marker.getTag() != null) {
            Shop shop = (Shop) marker.getTag();

            Navigator.navigateFromShopsActivityToShopDetailActivity(this, shop);
        }
    }

    // Cursor Loaders using Content Provider

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader loader = new CursorLoader(this,
                MadridGuideProvider.SHOPS_URI,
                DBConstants.Shop.SHOP_ALL_COLUMNS,            // projection
                null,                               // where
                null,                               // campos del where
                null                                // order
        );

        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        final Shops shops = new ShopDAO(this).getShops(data);

        shopsFragment.setListener(new OnElementClick<Shop>() {
            @Override
            public void clickedOn(@NonNull Shop shop, int position) {
                Navigator.navigateFromShopsActivityToShopDetailActivity(ShopsActivity.this, shop);
            }
        });

        shopsFragment.setShops(shops);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
