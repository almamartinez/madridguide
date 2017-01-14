package io.keepcoding.madridguide.model.mappers;

import java.util.LinkedList;
import java.util.List;

import io.keepcoding.madridguide.manager.net.ActivityEntity;
import io.keepcoding.madridguide.model.Activity;

public class ActivityEntityActivityMapper {
    public List<Activity> map(List<ActivityEntity> activityEntities) {
        if (activityEntities == null) {
            return null;
        }

        List<Activity> result = new LinkedList<>();

        for (ActivityEntity entity: activityEntities) {
            Activity activity = new Activity(entity.getId(), entity.getName());
            // detect current lang
            activity.setDescription(entity.getDescriptionEs());
            activity.setLogoImgUrl(entity.getLogoImg());
            activity.setImageUrl(entity.getImg());
            activity.setAddress(entity.getAddress());
            activity.setUrl(entity.getUrl());
            activity.setLatitude(entity.getLatitide());
            activity.setLongitude(entity.getLongitude());

            result.add(activity);
        }

        return result;
    }
}
