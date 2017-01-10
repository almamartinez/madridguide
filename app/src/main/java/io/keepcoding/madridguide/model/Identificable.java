package io.keepcoding.madridguide.model;

public interface Identificable<E> {
    public long getId();

    public E setId(long id);
}
