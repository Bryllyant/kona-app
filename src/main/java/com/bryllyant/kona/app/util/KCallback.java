package com.bryllyant.kona.app.util;

public interface KCallback<T> {
    void success(T data);
    void error(Throwable t);
}
