package io.keepcoding.madridguide.interactors;

import android.content.Context;

import io.keepcoding.madridguide.manager.db.ActivityDAO;
import io.keepcoding.madridguide.manager.db.ShopDAO;
import io.keepcoding.madridguide.util.MainThread;

public class CleanLocalCacheInteractor {
    public void execute(final Context context, final Runnable onCompletion) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ShopDAO shopDAO = new ShopDAO(context);
                shopDAO.deleteAll();

                ActivityDAO activityDAO = new ActivityDAO(context);
                activityDAO.deleteAll();

                if (onCompletion != null) {
                    MainThread.run(onCompletion);
                }
            }
        }).start();
    }
}