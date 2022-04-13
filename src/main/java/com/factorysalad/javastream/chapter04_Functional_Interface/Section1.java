package com.factorysalad.javastream.chapter04_Functional_Interface;

import java.util.function.Supplier;

/*
Supplier : 아낌없이 주는 나무

@FunctionalInterface
public interface Supplier<T> {
    T get();
}
 */
public class Section1 {
    public static void main(String[] args) {
        // 예제 1
        Supplier<String> myStringSupplier = () -> "hello world!";
        System.out.println(myStringSupplier.get());
        // 결과 : hello world!

        // 예제 2
        Supplier<Double> myRandomDoubleSupplier = () -> Math.random();
        // 함수가 1등 시민이 되었기 때문에 파라메터로 넘길 수 있다.
        printRandomDoubles(myRandomDoubleSupplier, 5);
    }

    public static void printRandomDoubles(Supplier<Double> randomSupplier, int count) {
        for (int i = 0; i< count; i++ ) {
            System.out.println(randomSupplier.get());
        }
    }
    /*
    결과 :
    0.563293080021141
    0.5912076650655896
    0.09702290698332139
    0.021855108402891843
    0.6137683485260339
     */
}