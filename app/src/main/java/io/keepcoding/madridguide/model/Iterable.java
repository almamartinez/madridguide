package io.keepcoding.madridguide.model;

import java.util.List;

public interface Iterable<E extends BaseModel> {
    public long size();
    public E get(long index);
    public List<E> getAll();
}
