package com.factorysalad.javastream.chapter04_Functional_Interface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/*
Consumer : 먹보

@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}
 */
public class Section2 {
    public static void main(String[] args) {
        Consumer<String> myStringConsumer = (String str) -> {
            System.out.println(str);
        };
        myStringConsumer.accept("hello");
        // 결과 : hello

        // 같은 값이라도 여러개의 Consumer를 만들어서 호출 할 수 있다. -------------------------------------
        List<Integer> integerInputs = Arrays.asList(4, 2, 3);  // Arrays.asList로 만들어진 리스트는 변경불가

        Consumer<Integer> myIntegerProcessor = x -> System.out.println("Processing integer " + x);
        process(integerInputs, myIntegerProcessor);
        /*
        결과 :
        Processing integer 4
        Processing integer 2
        Processing integer 3
         */

        Consumer<Integer> myDifferentIntegerProcessor = x -> System.out.println("Processing integer in different way " + x);
        process(integerInputs, myDifferentIntegerProcessor);
         /*
        결과 :
        Processing integer in different way 4
        Processing integer in different way 2
        Processing integer in different way 3
         */

        // 제네릭 타입으로 처리하면 유연하게 프로그램을 만들 수 있다.
        Consumer<Integer> process2consumer = x -> System.out.println("Processing Generic in different way " + x);
        process2(integerInputs, process2consumer);
         /*
        결과 :
        Processing Generic in different way 4
        Processing Generic in different way 2
        Processing Generic in different way 3
         */
    }

    // Integer만 받을 수 있는 process
    public static void process(List<Integer> inputs, Consumer<Integer> processor) {
        for (Integer input : inputs) {
            processor.accept(input);
        }
    }

    // 제네릭 타입 T를 넣어서 처리 할 수 있다.
    public static <T> void process2(List<T> inputs, Consumer<T> processor) {
        for (T input : inputs) {
            processor.accept(input);
        }
    }
}
