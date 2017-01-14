package io.keepcoding.madridguide.interactors;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Date;

import io.keepcoding.madridguide.util.Constants;
import io.keepcoding.madridguide.util.MainThread;

public class CheckCacheExpiredInteractor {
    private long MILLISECONDS_IN_A_WEEK = 1000*60*60*24*7;

    public void execute(final Context context, final Runnable onCacheNotExpired, final Runnable onCacheExpired) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        long time = preferences.getLong(Constants.LAST_UPDATED_CACHE, 0);

        long millisecondsPassed = new Date().getTime() - time;
        if (millisecondsPassed > MILLISECONDS_IN_A_WEEK) {
            MainThread.run(onCacheExpired);
        } else {
            MainThread.run(onCacheNotExpired);
        }
    }
}
