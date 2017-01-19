package io.keepcoding.madridguide.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.keepcoding.madridguide.R;
import io.keepcoding.madridguide.model.Activity;
import io.keepcoding.madridguide.util.Constants;

public class ActivityDetailActivity extends AppCompatActivity {
    @BindView(R.id.activity_shop_detail_shop_name_text)
    TextView activityNameText;

    @BindView(R.id.activity_shop_detail_shop_logo_image)
    ImageView activityLogoImage;

    @BindView(R.id.activity_shop_detail_shop_description_text) TextView activityDescriptionText;

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);

        ButterKnife.bind(this);

        getDetailShopFromCallingIntent();

        updateUI();
    }

    private void getDetailShopFromCallingIntent() {
        Intent i = getIntent();
        if (i != null) {
            activity = (Activity) i.getSerializableExtra(Constants.INTENT_KEY_ACTIVITY_DETAIL);
        }
    }

    private void updateUI() {
        activityNameText.setText(activity.getName());
        activityDescriptionText.setText(activity.getDescription());
        Picasso.with(this)
                .load(activity.getLogoImgUrl())
                .into(activityLogoImage);
    }
}