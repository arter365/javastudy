package com.factorysalad.javastream.chapter03_Lambda_Expression;

import java.util.function.BiFunction;

/*
두개의 인자를 받는 Bi function

@FunctionalInterface
public interface BiFunction<T, U, R> {
    R apply(T t, U u);
}
 */
public class Section3 {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;

        int result = add.apply(3, 5);
        System.out.println(result);
        // 결과 : 8
    }
}
