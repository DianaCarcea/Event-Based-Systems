package org.example.config;
import java.util.*;

public class Config {

    public static final boolean SHOW_TIMINGS = true;

    public static final int NUM_THREADS = 10;
    public static final int NUM_PUBLICATIONS = 10000;
    public static final int NUM_SUBSCRIPTIONS = 10000;

    public static final int CHUNK_SIZE_PUBLICATIONS = NUM_PUBLICATIONS / NUM_THREADS;
    public static final int REMAINDER_PUBLICATIONS = NUM_PUBLICATIONS % NUM_THREADS;

    public static final int CHUNK_SIZE_SUBSCRIPTIONS = NUM_SUBSCRIPTIONS / NUM_THREADS;
    public static final int REMAINDER_SUBSCRIPTIONS = NUM_SUBSCRIPTIONS % NUM_THREADS;



    public static final List<String> ORDERED_FIELDS= List.of(
            "stationid", "city", "temp", "rain", "wind", "direction", "date"
    );

    public static final Map<String, Double> FIELD_FREQUENCIES = Map.of(
            "stationid", 0.3,
            "city", 0.9,
            "temp", 0.5,
            "rain", 0.4,
            "wind", 0.7,
            "direction", 0.5,
            "date", 0.6
    );

    public static final Map<String, List<String>> AVAILABLE_OPERATORS = Map.of(
            "stationid", List.of("=", "!=", "<", ">", "<=", ">="),
            "city", List.of("=", "!="),
            "temp", List.of("=", "<", ">", "<=", ">="),
            "rain", List.of("=", "<", ">", "<=", ">="),
            "wind", List.of("=", "<", ">", "<=", ">="),
            "direction", List.of("=", "!="),
            "date", List.of("=", "<", ">", "<=", ">=")
    );

    public static final Map<String, Double> EQUALITY_OPERATOR_PERCENTAGES = Map.of(
            "city", 0.7
//            "direction", 0.5
    );

    public static final Map<String, List<String>> FIXED_VALUES = Map.of(
            "stationid", List.of("1", "2", "3", "4", "5"),
            "city", List.of("Bucharest", "Cluj", "Iasi", "Timisoara"),
            "direction", List.of("N", "NE", "E", "SE", "S", "SW", "W", "NW"),
            "date", List.of("2.02.2023", "3.02.2023", "4.02.2023")
    );

    public static final Map<String, Range<Double>> RANGE_VALUES = Map.of(
            "temp", new Range<>(0.0, 40.0),
            "rain", new Range<>(0.0, 10.0),
            "wind", new Range<>(0.0, 30.0)
    );


}
