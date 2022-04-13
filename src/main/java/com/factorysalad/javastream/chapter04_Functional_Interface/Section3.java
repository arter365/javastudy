package com.factorysalad.javastream.chapter04_Functional_Interface;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

/*
BiConsumer : 더 먹보

@FunctionalInterface
public interface BiConsumer<T, U> {
    void accept(T t, U u);
}
 */
public class Section3 {
    public static void main(String[] args) {
        BiConsumer<Integer, Double> myDoubleProcessor = (index, input) ->
                System.out.println("Processing " + input + " at index " + index);

        List<Double> inputs = Arrays.asList(1.1, 2.2, 3.3);
        process(inputs, myDoubleProcessor);
        /*
        결과 :
        Processing 1.1 at index 0
        Processing 2.2 at index 1
        Processing 3.3 at index 2
         */
    }

    public static <T> void process(List<T> inputs, BiConsumer<Integer, T> processor) {
        for (int i = 0; i < inputs.size(); i++) {
            processor.accept(i, inputs.get(i));
        }
    }
}
