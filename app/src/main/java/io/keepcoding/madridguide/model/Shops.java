package io.keepcoding.madridguide.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Shops extends BaseModelAggregate<Shop> {

    private Shops() {
    }

    public static @NonNull
    Shops build(@NonNull List<Shop> baseModelList) {
        Shops shops = new Shops();

        if (baseModelList == null) {
            shops.setList(new ArrayList<Shop>());
        } else {
            shops.setList(baseModelList);
        }

        return shops;
    }


}
