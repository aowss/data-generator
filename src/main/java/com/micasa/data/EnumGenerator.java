package com.micasa.data;

import java.lang.reflect.Array;

public class EnumGenerator<T> implements Generator<T> {

    private final T[] values;

    public EnumGenerator(T[] values) {
        this.values = values;
    }

    @Override
    public T[] generate(int count) {
        T[] result = (T[]) Array.newInstance(values.getClass().getComponentType(), count);
        for (int i = 0; i < count; i++) {
            result[i] = values[i % values.length];
        }
        return result;
    }

}
