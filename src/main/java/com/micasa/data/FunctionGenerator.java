package com.micasa.data;

import java.lang.reflect.Array;
import java.util.function.Supplier;

//  TODO: extend with random generators for primitive types and UUIDs
public class FunctionGenerator<T> implements Generator<T> {

    private final Supplier<T> supplier;
    private final boolean ensureUniqueness;

    public FunctionGenerator(Supplier<T> supplier) {
        this(supplier, false);
    }

    public FunctionGenerator(Supplier<T> supplier, boolean ensureUniqueness) {
        this.supplier = supplier;
        this.ensureUniqueness = ensureUniqueness;
    }

    //  TODO: implement uniqueness enforcement
    @Override
    public T[] generate(int count) {
        T firstValue = supplier.get();
        T[] result = (T[]) Array.newInstance(firstValue.getClass(), count);
        result[0] = firstValue;
        for (int i = 1; i < count; i++) {
            result[i] = supplier.get();
        }
        return result;
    }

}
