package io.keepcoding.madridguide.interactors;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Date;

import io.keepcoding.madridguide.util.MainThread;

import static io.keepcoding.madridguide.util.Constants.LAST_UPDATED_CACHE;

public class SaveLastUpdatedDateInteractor {


    public void execute(final Context context, final Runnable onCompletion) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putLong(LAST_UPDATED_CACHE, new Date().getTime());
        editor.apply();

        if (onCompletion != null) {
            MainThread.run(onCompletion);
        }
    }
}