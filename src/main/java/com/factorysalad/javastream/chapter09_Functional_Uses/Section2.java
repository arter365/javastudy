package com.factorysalad.javastream.chapter09_Functional_Uses;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Lazy Evaluation

- Lambda의 계산은 그 결과값이 필요할 때가 되어서야 계산된다.
- 이를 이용하여 불필요한 계산을 줄이거나 해당 코드의 샐행 순서를 의도적으로 미룰 수 있다.
- 계산을 미뤄서 계산이 필요없다면 하지 않아도 되는 최적화를 할 수 있다.
 */
public class Section2 {

    public static void main(String[] args) {
        // 하나만 실행 되는 경우
        if (returnTrue() || returnFalse()) {
            System.out.println("true");
        }
        /*
        결과 : returnTrue()가 true를 리턴하기 때문에 or 연산을 하면 무조건 실행되고 returnFalse()는 실행되지 않는다.
        Returning true
        true
         */

        // 두개 다 실행 되는 경우 (최적화 되지 않았다)
        if (or(returnTrue(), returnFalse())) {
            System.out.println("true");
        }
        /*
        결과 : 두 파라메터의 값을 모두 알고 실행하기 때문
        Returning true
        Returning false
        true
         */

        // 최적화 코드
        if (lazyOr(() -> returnTrue(), () -> returnFalse())) {
            System.out.println("true");
        }
        /*
        결과 : supplier를 통해서 하나의 값이 true인 것을 먼저 알았기 때문에 뒤의 returnFalse()는 실행시키지 않는다.
        Returning true
        true
         */

        // 스트림의 최적화 코드 예제
        Stream<Integer> integerStream = Stream.of(3, -2, 5, 8, -3, 10)
                .filter(x -> x > 0)
                // stream에는 peek라는 함수가 있는데 잠시 힐끗 보고 넘어가는 함수이다.
                .peek(x -> System.out.println("peeking " + x))
                .filter(x -> x % 2 ==0);
        System.out.println("Before collect");

        List<Integer> integers = integerStream.collect(Collectors.toList());
        System.out.println("After collect: " + integers);

        /*
        결과 : peeking 구문이 먼저 실행될 것 같지만 실제 스트림은 종결처리(여기서는 collect)를 하기 전까지는 모든 계산을 미룬다.
        Before collect
        peeking 3
        peeking 5
        peeking 8
        peeking 10
        After collect: [8, 10]
         */
    }

    public static boolean or(boolean x, boolean y) {
        return x || y;
    }

    // supplier를 거친 최적화 코드
    public static boolean lazyOr(Supplier<Boolean> x, Supplier<Boolean> y) {
        return x.get() || y.get();
    }

    // 헬퍼 true 리턴
    public static boolean returnTrue() {
        System.out.println("Returning true");
        return true;
    }

    // 헬퍼 false 리턴
    public static boolean returnFalse() {
        System.out.println("Returning false");
        return false;
    }
}