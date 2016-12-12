package io.keepcoding.madridguide.navigator;

import android.content.Intent;

import io.keepcoding.madridguide.activities.MainActivity;
import io.keepcoding.madridguide.activities.ShopsActivity;

public class Navigator {
    public static Intent navigateFromMainActivityToShopsActivity(MainActivity mainActivity) {
        Intent i = new Intent(mainActivity, ShopsActivity.class);
        mainActivity.startActivity(i);
        return i;
    }
}
