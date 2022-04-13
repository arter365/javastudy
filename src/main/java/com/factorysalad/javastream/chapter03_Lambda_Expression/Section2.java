package com.factorysalad.javastream.chapter03_Lambda_Expression;

import java.util.function.Function;

/*
함수의 구성요소
. 함수의 이름
. 반환값의 타입 (return type)
. 매개변수 (parameters)
. 함수의 내용 (body)

Lambda Expression (함수형 인터페이스를 구현하는 가장 간단한 방법)
. 매개변수의 타입이 유추 가능할 경우 타입 생략 가능.
. 매개변수가 하나일 경우 괄호 생략 가능.
. 바로 리턴하는 경우 중괄호 생략 가능.

----------------------------------------
@FunctionalInterface
. 단 하나의 abstract method 만을 가지는 인터페이스
. Default method와 static method는 이미 구현이 되어 있다. (가능)
----------------------------------------

@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
 */
public class Section2 {
    public static void main(String[] args) {
//        Function<Integer, Integer> myAdder = (Integer x) -> {
//            return x + 10;
//        };

        // 아래와 같이 생략이 가능하다.
        Function<Integer, Integer> myAdder = x -> x + 10;

        int result = myAdder.apply(5);
        System.out.println(result);
        // 결과 : 15

    }
}
