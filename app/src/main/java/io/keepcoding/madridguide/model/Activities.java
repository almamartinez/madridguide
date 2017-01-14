package io.keepcoding.madridguide.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Activities extends BaseModelAggregate<Activity> {

    private Activities() {
    }

    public static @NonNull
    Activities build(@NonNull List<Activity> baseModelList) {
        Activities shops = new Activities();

        if (baseModelList == null) {
            shops.setList(new ArrayList<Activity>());
        } else {
            shops.setList(baseModelList);
        }

        return shops;
    }

}
