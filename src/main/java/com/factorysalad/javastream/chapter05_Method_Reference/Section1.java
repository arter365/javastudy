package com.factorysalad.javastream.chapter05_Method_Reference;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/*
기존에 만들어져 있는 함수를 람다에서 사용하는 방식을 배운다.
레퍼런스를 지정하는 것이기 때문에 함수의 이름만 지정한다.
 */
public class Section1 {
    public static int calculate(int x, int y, BiFunction<Integer, Integer, Integer> operator) {
        return operator.apply(x, y);
    }

    public static int multiply(int x, int y) {
        return x * y;
    }

    public int subtract(int x, int y) {
        return x - y;
    }

    // 클래스 안에서 메서드 레퍼런스 사용하는 방법
    public void myMethod() {
        System.out.println(calculate(10, 3, this::subtract));
    }

    public static void main(String[] args) {
        // String을 받아서 Integer를 리턴하는 함수를 만들어서 메서드 레퍼런스를 받는다.
        Function<String, Integer> str2int = Integer::parseInt;
        System.out.println(str2int.apply("15"));
        // 결과 : 15

        String str = "hello";
        Predicate<String> equalsToHello = str::equals;
        System.out.println(equalsToHello.test("hello"));
        // 결과 : true

        // 방법1
        System.out.println("방법1 : " + calculate(8, 2, (x, y) -> x + y));
        // 결과 : 10

        // 방법2 메서드 레퍼런스 (스테틱 메서드)
        System.out.println("방법2 : " + calculate(8, 2, Section1::multiply));
        // 결과 : 16

        // 방법3 메서드 레퍼런스 (인스턴스 메서드)
        Section1 instance = new Section1();
        System.out.println("방법3 : " + calculate(8, 2, instance::subtract));
        // 결과 : 6

        // 방법4
        instance.myMethod();
        // 결과 : 7


    }
}
