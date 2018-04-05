package com.bryllyant.kona.util;

public interface Callback<T> {
    void success(T data);
    void error(Throwable t);
}
