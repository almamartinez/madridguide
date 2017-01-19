package io.keepcoding.madridguide.interactors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import io.keepcoding.madridguide.model.Shop;
import io.keepcoding.madridguide.model.Shops;
import io.keepcoding.madridguide.util.MainThread;

public class CacheAllShopImagesInteractor {
    public interface CacheAllShopImagesInteractorResponse {
        public void response();
    }

    public void execute(final Context context, final @NonNull Shops shops, final CacheAllShopImagesInteractorResponse response) {
        if (context == null || shops == null) {
            Log.w("CacheAllShopsInteractor", "Context or Shops null");
            return;
        }

        for (final Shop shop: shops.getAll()) {
            Picasso.with(context).load(shop.getLogoImgUrl()).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    Log.d("CacheAllShopImages", "loaded -->" + shop.getLogoImgUrl());
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });
        }

        if (response != null) {
            MainThread.run(new Runnable() {
                @Override
                public void run() {
                    response.response();
                }
            });
        }
    }

}
