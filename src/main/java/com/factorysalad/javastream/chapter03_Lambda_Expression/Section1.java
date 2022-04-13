package com.factorysalad.javastream.chapter03_Lambda_Expression;

import com.factorysalad.javastream.chapter03_Lambda_Expression.util.Adder;

import java.util.function.Function;

/*
아래와 같이 클래스(Adder)를 만들어서 사용하면 매우 불편하다.
람다를 사용하면 편하게 함수를 사용 할 수 있다.
 */
public class Section1 {
    public static void main(String[] args) {
        Function<Integer, Integer> myAdder = new Adder();
        int result = myAdder.apply(5);
        System.out.println(result);
        // 결과 : 15
    }
}

