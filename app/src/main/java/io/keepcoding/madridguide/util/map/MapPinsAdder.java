package io.keepcoding.madridguide.util.map;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapPinsAdder {
    public interface MapPinnable<E> {
        float getLatitude();
        float getLongitude();
        String getPinDescription();
        String getPinImageUrl();
        E getRelatedModelObject();
    }

    public static void addPins(List<MapPinnable> mapPinnables, final GoogleMap googleMap, final Context context) {
        if (mapPinnables == null || googleMap == null) {
            return;
        }

        for (final MapPinnable pinnable: mapPinnables) {
                final LatLng position = new LatLng(pinnable.getLatitude(), pinnable.getLongitude());
                final String profileImageUrl = pinnable.getPinImageUrl();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        BitmapDescriptor bitmapDescriptor = null;
/*
                        try {
                            bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(Picasso.with(context).load(profileImageUrl).get());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        */
                        final MarkerOptions marker = new MarkerOptions().position(position).title(pinnable.getPinDescription());
                        marker.icon(bitmapDescriptor);


                        (new Handler(Looper.getMainLooper())).post(new Runnable() {
                            @Override
                            public void run() {
                                Marker m = googleMap.addMarker(marker);
                                m.setTag(pinnable);
                            }
                        });
                    }
                }).start();
        }
    }


}
