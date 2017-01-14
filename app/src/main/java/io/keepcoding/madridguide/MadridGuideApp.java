package io.keepcoding.madridguide;

import android.app.Application;
import android.content.Context;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import io.keepcoding.madridguide.interactors.CacheAllActivitiesInteractor;
import io.keepcoding.madridguide.interactors.CacheAllShopsInteractor;
import io.keepcoding.madridguide.interactors.CheckCacheExpiredInteractor;
import io.keepcoding.madridguide.interactors.CleanLocalCacheInteractor;
import io.keepcoding.madridguide.interactors.GetAllActivitiesInteractor;
import io.keepcoding.madridguide.interactors.GetAllShopsInteractor;
import io.keepcoding.madridguide.interactors.GetAllShopsInteractorResponse;
import io.keepcoding.madridguide.interactors.SaveLastUpdatedDateInteractor;
import io.keepcoding.madridguide.model.Activities;
import io.keepcoding.madridguide.model.Shops;
import io.keepcoding.madridguide.navigator.Navigator;

public class MadridGuideApp extends Application {
    private static WeakReference<Context> appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        MadridGuideApp.appContext = new WeakReference<Context>(getApplicationContext());

        new CheckCacheExpiredInteractor().execute(getBaseContext(),
                new Runnable() {
                    @Override
                    public void run() {
                        cacheNotExpired();
                    }
                },
                new Runnable() {
                    @Override
                    public void run() {
                        cacheExpired();
                    }
                }
        );

        Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);
        Picasso.with(getApplicationContext()).setLoggingEnabled(true);

    }

    private void cacheExpired() {
        new CleanLocalCacheInteractor().execute(getBaseContext(), new Runnable() {
            @Override
            public void run() {
                new GetAllShopsInteractor().execute(getApplicationContext(),
                        new GetAllShopsInteractorResponse() {
                            @Override
                            public void response(Shops shops) {
                                new CacheAllShopsInteractor().execute(getApplicationContext(),
                                        shops, new CacheAllShopsInteractor.CacheAllShopsInteractorResponse() {
                                            @Override
                                            public void response(boolean success) {

                                                new GetAllActivitiesInteractor().execute(getApplicationContext(), new GetAllActivitiesInteractor.GetAllActivitiesInteractorResponse() {
                                                    @Override
                                                    public void response(Activities activities) {
                                                        new CacheAllActivitiesInteractor().execute(getApplicationContext(), activities, new CacheAllActivitiesInteractor.CacheAllActivitiesInteractorResponse() {
                                                            @Override
                                                            public void response(boolean success) {
                                                                new SaveLastUpdatedDateInteractor().execute(getAppContext(), new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        Navigator.navigateToMainActivity();
                                                                    }
                                                                });
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        });
                            }
                        }
                );
            }
        });
    }

    private void cacheNotExpired() {
        Navigator.navigateToMainActivity();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public static Context getAppContext() {
        if (appContext != null) {
            return MadridGuideApp.appContext.get();
        }
        return null;
    }
}
