package io.keepcoding.madridguide.interactors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.jetbrains.annotations.Nullable;

import io.keepcoding.madridguide.model.Activities;
import io.keepcoding.madridguide.model.Activity;
import io.keepcoding.madridguide.model.Shop;
import io.keepcoding.madridguide.model.Shops;
import io.keepcoding.madridguide.util.MainThread;

public class CacheAllActivitiesImagesInteractor {
    public interface CacheAllShopImagesInteractorResponse {
        public void response();
    }

    public void execute(final Context context, final @Nullable Activities activities, final CacheAllShopImagesInteractorResponse response) {
        if (context == null || activities == null) {
            Log.w("CacheAllActivitiesImagesInteractor", "Context or Shops null");
            return;
        }

        for (final Activity activity: activities.getAll()) {
            Picasso.with(context).load(activity.getLogoImgUrl()).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    Log.d("CacheAllActivitiesImag", "loaded -->" + activity.getLogoImgUrl());
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
