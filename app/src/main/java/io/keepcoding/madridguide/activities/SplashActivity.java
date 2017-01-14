package io.keepcoding.madridguide.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import io.keepcoding.madridguide.R;
import io.keepcoding.madridguide.navigator.Navigator;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_splash);

        Navigator.setStartActivity(this);
    }
}
