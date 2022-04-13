package com.factorysalad.javastudy.L_Lambda;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class RunSomethingRun {
    public static void main(String[] args) {

        int res1;
        String res2;
        boolean res3;
        // ---------------------------------------------------------------------------------------------------

        /*
        // 익명내부크래스 형태의 구현
        ARunSomething fun1 = new ARunSomething() {
            @Override
            public void doIt(number) {
                return number + 10;
            }
        };
        */

        // 람다식 구현 (한줄일 경우 {} 생략가능) * 사용할 수 있는 레퍼런스를 만드는 작업이다.
        RunSomething fun1 = (number) -> number + 10;

        // 실행
        res1 = fun1.doIt(5);
        System.out.println(res1);

        // ---------------------------------------------------------------------------------------------------

        // 이미 구현된 함수형 인터페이스를 사용 할 수 있다. (https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html)
        // <인풋값, 리턴값>
        Function<Integer, Integer> fun2 = i -> i + 10;

        // 실행
        res1 = fun2.apply(5);
        System.out.println(res1);

        // ---------------------------------------------------------------------------------------------------

        // 위의 예를 더 간단하게 입력과 리턴의 자료형이 같을 때 사용한다.
        UnaryOperator<Integer> fun3 = i -> i + 10;
        System.out.println(fun3.apply(5));

        // ---------------------------------------------------------------------------------------------------

        // 입력과 리턴의 자료형이 다르면 아래와 같이 사용한다.
        Function<Integer, String> fun4 = i -> String.valueOf(i + 2);
        res2 = fun4.apply(5);
        System.out.println(res2);

        // ---------------------------------------------------------------------------------------------------

        Function<Integer, Integer> fun5 = i -> i * 2;

        // 성격에 맞는 디펄트 함수를 가지고 있다. (2 + 10) * 2 = 24 (andThen과 compose는 연산순서가 다르다)
        res1 = fun2.andThen(fun5).apply(2);
        System.out.println(res1);

        // ---------------------------------------------------------------------------------------------------
        // 기타 내장함수 사용예 (true, false를 판단하는 함수를 만든다)
        Predicate<Integer> isEven = i -> i % 2 == 0;
        res3 = isEven.test(3);
        System.out.println(res3);

        // 같은 타입의 인자를 받아서 같은 타입을 리턴한다. a,b와 같이 타입을 안적어도 타입추론이 가능하다.
        BinaryOperator<Integer> sum = (a, b) -> a + b;
    }
}
