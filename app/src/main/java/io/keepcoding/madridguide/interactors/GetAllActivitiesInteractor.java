package io.keepcoding.madridguide.interactors;

import android.content.Context;

import java.util.List;

import io.keepcoding.madridguide.manager.net.ActivityEntity;
import io.keepcoding.madridguide.manager.net.NetworkManager;
import io.keepcoding.madridguide.model.Activities;
import io.keepcoding.madridguide.model.Activity;
import io.keepcoding.madridguide.model.mappers.ActivityEntityActivityMapper;

public class GetAllActivitiesInteractor {
    public interface GetAllActivitiesInteractorResponse {
        void response(Activities activities);
    }

    public void execute(final Context context, final GetAllActivitiesInteractorResponse response) {
        NetworkManager networkManager = new NetworkManager(context);
        networkManager.getActivitiesFromServer(new NetworkManager.GetActivitiesListener() {
            @Override
            public void getActivityEntitiesSuccess(List<ActivityEntity> result) {
                List<Activity> shops = new ActivityEntityActivityMapper().map(result);
                if (response != null) {
                    response.response(Activities.build(shops));
                }
            }

            @Override
            public void getActivityEntitiesDidFail() {
                if (response != null) {
                    response.response(null);
                }
            }
        });
    }
}
