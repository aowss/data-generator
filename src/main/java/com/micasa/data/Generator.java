package com.micasa.data;

public interface Generator<T> {
    T[] generate(int count);
}
