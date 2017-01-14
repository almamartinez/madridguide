package io.keepcoding.madridguide.manager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import java.util.LinkedList;
import java.util.List;

import io.keepcoding.madridguide.model.Activities;
import io.keepcoding.madridguide.model.Activity;

import static io.keepcoding.madridguide.manager.db.DBConstants.Activity.ACTIVITY_ALL_COLUMNS;
import static io.keepcoding.madridguide.manager.db.DBConstants.Activity.KEY_ACTIVITY_ADDRESS;
import static io.keepcoding.madridguide.manager.db.DBConstants.Activity.KEY_ACTIVITY_DESCRIPTION;
import static io.keepcoding.madridguide.manager.db.DBConstants.Activity.KEY_ACTIVITY_ID;
import static io.keepcoding.madridguide.manager.db.DBConstants.Activity.KEY_ACTIVITY_IMAGE_URL;
import static io.keepcoding.madridguide.manager.db.DBConstants.Activity.KEY_ACTIVITY_LATITUDE;
import static io.keepcoding.madridguide.manager.db.DBConstants.Activity.KEY_ACTIVITY_LOGO_IMAGE_URL;
import static io.keepcoding.madridguide.manager.db.DBConstants.Activity.KEY_ACTIVITY_LONGITUDE;
import static io.keepcoding.madridguide.manager.db.DBConstants.Activity.KEY_ACTIVITY_NAME;
import static io.keepcoding.madridguide.manager.db.DBConstants.Activity.KEY_ACTIVITY_URL;
import static io.keepcoding.madridguide.manager.db.DBConstants.Activity.TABLE_ACTIVITY;


public class ActivityDAO extends BaseDAO<Activity> {

    public ActivityDAO(Context context, DBHelper dbHelper) {
        super(context, dbHelper);
    }

    public ActivityDAO(Context context) {
        this(context, DBHelper.getInstance(context));
    }

    @Override
    public @NonNull ContentValues getContentValues(final @NonNull Activity activity) {
        final ContentValues contentValues = new ContentValues();

        if (activity == null) {
            return contentValues;
        }

        contentValues.put(KEY_ACTIVITY_NAME, activity.getName());
        contentValues.put(KEY_ACTIVITY_ADDRESS, activity.getAddress());
        contentValues.put(KEY_ACTIVITY_DESCRIPTION, activity.getDescription());
        contentValues.put(KEY_ACTIVITY_IMAGE_URL, activity.getImageUrl());
        contentValues.put(KEY_ACTIVITY_LOGO_IMAGE_URL, activity.getLogoImgUrl());
        contentValues.put(KEY_ACTIVITY_LATITUDE, activity.getLatitude());
        contentValues.put(KEY_ACTIVITY_LONGITUDE, activity.getLongitude());
        contentValues.put(KEY_ACTIVITY_URL, activity.getUrl());

        return contentValues;
    }

    public static @NonNull Activity getActivityFromContentValues(final @NonNull ContentValues contentValues) {
        final Activity activity = new Activity(1, "");

        //activity.setId(contentValues.getAsInteger(KEY_SHOP_ID));
        activity.setName(contentValues.getAsString(KEY_ACTIVITY_NAME));
        activity.setAddress(contentValues.getAsString(KEY_ACTIVITY_ADDRESS));
        activity.setDescription(contentValues.getAsString(KEY_ACTIVITY_DESCRIPTION));
        activity.setImageUrl(contentValues.getAsString(KEY_ACTIVITY_IMAGE_URL));
        activity.setLogoImgUrl(contentValues.getAsString(KEY_ACTIVITY_LOGO_IMAGE_URL));
        activity.setUrl(contentValues.getAsString(KEY_ACTIVITY_URL));
        activity.setLatitude(contentValues.getAsFloat(KEY_ACTIVITY_LATITUDE));
        activity.setLongitude(contentValues.getAsFloat(KEY_ACTIVITY_LONGITUDE));

        return activity;
    }

    @Override
    @NonNull
    public Activity getElementFromCursor(Cursor c) {
        long identifier = c.getLong(c.getColumnIndex(KEY_ACTIVITY_ID));
        String name = c.getString(c.getColumnIndex(KEY_ACTIVITY_NAME));
        Activity activity = new Activity(identifier, name);

        activity.setAddress(c.getString(c.getColumnIndex(KEY_ACTIVITY_ADDRESS)));
        activity.setDescription(c.getString(c.getColumnIndex(KEY_ACTIVITY_DESCRIPTION)));
        activity.setImageUrl(c.getString(c.getColumnIndex(KEY_ACTIVITY_IMAGE_URL)));
        activity.setLogoImgUrl(c.getString(c.getColumnIndex(KEY_ACTIVITY_LOGO_IMAGE_URL)));
        activity.setLatitude(c.getFloat(c.getColumnIndex(KEY_ACTIVITY_LATITUDE)));
        activity.setLongitude(c.getFloat(c.getColumnIndex(KEY_ACTIVITY_LONGITUDE)));
        activity.setUrl(c.getString(c.getColumnIndex(KEY_ACTIVITY_URL)));
        return activity;
    }

    @NonNull
    @Override
    public String getTableName() {
        return TABLE_ACTIVITY;
    }

    @NonNull
    @Override
    public String getIdFieldName() {
        return KEY_ACTIVITY_ID;
    }

    @NonNull
    @Override
    public String[] getAllColumnNames() {
        return ACTIVITY_ALL_COLUMNS;
    }


    @NonNull
    public Activities getActivities(Cursor data) {
        List<Activity> list = new LinkedList<>();

        while (data.moveToNext()) {
            Activity activity = getElementFromCursor(data);
            list.add(activity);
        }

        return (Activities) Activities.build(list);
    }
}
