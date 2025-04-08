package org.example.config;

public class Range<T extends Number> {
    public final T min;
    public final T max;

    public Range(T min, T max) {
        this.min = min;
        this.max = max;
    }

    public String toString() {
        return "[" + min + " - " + max + "]";
    }
}
