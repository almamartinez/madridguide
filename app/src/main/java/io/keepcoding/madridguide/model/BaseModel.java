package io.keepcoding.madridguide.model;

import java.io.Serializable;

public abstract class BaseModel implements Identificable<BaseModel>, Serializable {
    private long id;

    public long getId() {
        return id;
    }

    public BaseModel setId(long id) {
        this.id = id;
        return this;
    }
}
