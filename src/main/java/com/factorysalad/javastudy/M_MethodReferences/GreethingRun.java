package com.factorysalad.javastudy.M_MethodReferences;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class GreethingRun {
    public static void main(String[] args) {
        // -------------------------------------------------------------
        // 메서드 레퍼런스 참조
        UnaryOperator<String> hi1 = s -> "hi " + s;
        // 이렇게 new 하지 않고 바로 사용할 수 있는 이유는 익명클래스이기 때문이다.
        // 익명클래스로 모양을 바꾸어보면 바로 이해 할 수 있다.
        System.out.println(hi1.apply("twotone1"));  // hi twotone1

        // 함수형 인터페이스에 레퍼런스 연결
        UnaryOperator<String> hi2 = Greething::hi;
        System.out.println(hi2.apply("twotone2"));  // hi twotone2

        // 함수형 인터페이스에 레퍼런스 연결
        Greething greething = new Greething();
        UnaryOperator<String> hi3 = greething::hello;
        System.out.println(hi3.apply("twotone3"));  // hello twotone3

        // -------------------------------------------------------------
        // 생성자 레퍼런스 참조
        Supplier<Greething> gree1 = Greething::new;
        // get() 할 때 인스터스가 생성된다.
        System.out.println(gree1.get().getName());  // hi

        // <인풋타입, 리턴타입>
        Function<String, Greething> gree2 = Greething::new;
        System.out.println(gree2.apply("twotone").getName());   // twotone

        // -------------------------------------------------------------
        // 임의 객체의 인스턴스 메소드 참조
        String[] names = {"keesun", "whiteship", "toby"};
        Arrays.sort(names, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(names));
    }
}
