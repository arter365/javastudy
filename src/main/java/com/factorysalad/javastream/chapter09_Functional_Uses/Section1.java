package com.factorysalad.javastream.chapter09_Functional_Uses;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Section1 {

    public static void main(String[] args) {
        // Scope와 Closure 테스트
        Supplier<String> supplier = getStringSupplier();
        System.out.println(supplier.get());
        // 결과 : HelloWorld

        // Curry 테스트
        BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;
        Function<Integer, Function<Integer, Integer>> curriedAdd = x -> y -> x + y;

        Function<Integer, Integer> addThree = curriedAdd.apply(3);
        int result = addThree.apply(10);
        System.out.println(result);
        // 결과 : 13
    }

    public static Supplier<String> getStringSupplier() {
        String hello = "Hello";
        // 이 메서드의 호출이 끝나는 시점에 위의 hello 변수는 사라져야 하지만 외부에서 사용되기 때문에 없어지지 않는다.
        // 그래서 아래의 supplier 메서드는 클로져이다.
        Supplier<String> supplier = () -> {
            String world = "World";
            return hello + world;
        };

        return supplier;
    }
}
