package io.leres.controllers;

public interface Presenter<T, S> {
    T convert(S entity);
}
