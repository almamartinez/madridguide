package io.keepcoding.madridguide.interactors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import io.keepcoding.madridguide.manager.db.ActivityDAO;
import io.keepcoding.madridguide.model.Activities;
import io.keepcoding.madridguide.model.Activity;
import io.keepcoding.madridguide.util.MainThread;

public class CacheAllActivitiesInteractor {
    public interface CacheAllActivitiesInteractorResponse {
        public void response(boolean success);
    }

    public void execute(final Context context, final @NonNull Activities activities, final CacheAllActivitiesInteractorResponse response) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (context == null || activities == null) {
                    Log.w("CacheAllShopsInteractor", "Context or Shops null");
                    return;
                }

                ActivityDAO dao = new ActivityDAO(context);

                boolean success = true;
                for (Activity activity: activities.getAll()) {
                    success = dao.insert(activity) > 0;
                    if (!success) {
                        break;
                    }
                }

                if (response != null) {
                    final boolean finalSuccess = success;
                    MainThread.run(new Runnable() {
                        @Override
                        public void run() {
                            response.response(finalSuccess);
                        }
                    });
                }
            }
        }).start();
    }
}
