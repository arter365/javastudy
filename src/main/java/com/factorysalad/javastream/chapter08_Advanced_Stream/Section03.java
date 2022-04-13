package com.factorysalad.javastream.chapter08_Advanced_Stream;

import java.util.Optional;
import java.util.stream.Stream;

/*
Optional<T> findFirst();
findFirst : Stream 안의 첫번째 데이터를 반환. Stream이 비어있다면 비어있는 Optional을 반환.

Optional<T> findAny();
findAny : Stream 안의 아무 데이터나 리턴, 순서가 중요하지 않고 Parallel Stream을 사용할 때 최적화를 할 수 있다.
          마찬가지로 Stream이 비어있다면 빈 Optional을 반환.
 */
public class Section03 {
    public static void main(String[] args) {
        Optional<Integer> anyNegativeInteger = Stream.of(3, 2, -5, 6)
                // find~ 는 보통 필터와 연개해서 많이 사용한다.
                .filter(x -> x < 0)
                .findAny();
        System.out.println(anyNegativeInteger.get());
        // 결과 : -5 (값이 랜덤이다)

        Optional<Integer> firstPositiveInteger = Stream.of(-3, -2, -5, 6)
                .filter(x -> x > 0)
                .findFirst();
        System.out.println(firstPositiveInteger.get());
        // 결과 : 6
    }
}
