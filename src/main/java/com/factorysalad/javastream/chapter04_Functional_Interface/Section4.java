package com.factorysalad.javastream.chapter04_Functional_Interface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/*
Predicate : 진실 혹은 거짖

@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
 */
public class Section4 {
    public static void main(String[] args) {
        Predicate<Integer> isPositive = x -> x > 0;
        System.out.println(isPositive.test(10));
        // 결과 : true

        List<Integer> inputs = Arrays.asList(10, -5, 4, -2, 0, 3);
        System.out.println("Positive number : " + filter(inputs, isPositive));
        // 결과 : Positive number : [10, 4, 3]

        // negate
        System.out.println("Non-positive number : " + filter(inputs, isPositive.negate()));
        // 결과 : Non-positive number : [-5, -2, 0]

        // or
        System.out.println("Non-negative number : " + filter(inputs, isPositive.or(x -> x == 0)));
        // 결과 : Non-negative number : [10, 4, 0, 3]

        // and
        System.out.println("Positive even numbers : " + filter(inputs, isPositive.and(x -> x % 2 == 0)));
        // 결과 : Positive even numbers : [10, 4]
    }

    public static <T> List<T> filter(List<T> inputs, Predicate<T> condition) {
        List<T> output = new ArrayList<>();
        for (T input : inputs) {
            if (condition.test(input)){
                output.add(input);
            }
        }
        return output;
    }
}
