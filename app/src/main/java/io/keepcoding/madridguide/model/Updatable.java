package io.keepcoding.madridguide.model;

public interface Updatable<E> {
    public void add(E e);
    public void delete(E element);
    public void edit(E newElement, long index);
}
