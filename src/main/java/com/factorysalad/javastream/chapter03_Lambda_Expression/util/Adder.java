package com.factorysalad.javastream.chapter03_Lambda_Expression.util;

import java.util.function.Function;

public class Adder implements Function<Integer, Integer> {
    @Override
    public Integer apply(Integer x) {
        return x + 10;
    }
}
