package io.keepcoding.madridguide.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;

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

public class ShopsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    GoogleMap googleMap;

    private MapFragment mapFragment;
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
            }
        });

        setupMap();
    }


    private void initializeMap() {
        if (googleMap == null) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
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
