package io.keepcoding.madridguide.manager.net;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ActivityResponse {
    @SerializedName("result")
    List<ActivityEntity> result;

    public List<ActivityEntity> getResult() {
        return result;
    }
}
