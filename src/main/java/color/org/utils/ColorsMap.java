package color.org.utils;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.List;

import static java.util.Arrays.asList;

public class ColorsMap {


    public static List<ImmutablePair<Integer, String>> COLORS = asList(
            new ImmutablePair<>(1, "blue"),
            new ImmutablePair<>(2, "green"),
            new ImmutablePair<>(3, "purple"),
            new ImmutablePair<>(4, "red"),
            new ImmutablePair<>(5, "yellow"),
            new ImmutablePair<>(6, "turquois"),
            new ImmutablePair<>(7, "white")
    );
}
