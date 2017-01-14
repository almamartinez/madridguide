package io.keepcoding.madridguide.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BaseModelAggregate<E extends BaseModel> implements Iterable<E>, Updatable<E> {
    private List<E> list;

    public static @NonNull
    BaseModelAggregate build(@NonNull List<? extends BaseModel> baseModelList) {
        BaseModelAggregate shops = new BaseModelAggregate();

        if (baseModelList == null) {
            shops.list = new ArrayList<>();
        } else {
            shops.list = baseModelList;
        }

        return shops;
    }

    @Override
    public long size() {
        return list.size();
    }

    @Override
    public @Nullable
    E get(long index) {
        if (index > size()) {
            return null;
        }
        return list.get((int)index);
    }

    @Override
    public @NonNull  List<E> getAll() {
        return this.list;
    }

    @Override
    public void add(E e) {
        this.list.add(e);
    }

    @Override
    public void delete(E e) {
        this.list.remove(e);
    }

    @Override
    public void edit(E e, long index) {

    }
}