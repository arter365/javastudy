package com.factorysalad.javastream.chapter08_Advanced_Stream;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
collect 알아보기
 */
public class Section05 {
    public static void main(String[] args) {
        // 리스트로 만들기
        List<Integer> numberList = Stream.of(3, 5, -3, 3, 4, 5)
                .collect(Collectors.toList());
        System.out.println(numberList);
        // 결과 : [3, 5, -3, 3, 4, 5]

        // Set의 형태로 만들기 (Set : 순서 유지 없고 중복 없다)
        Set<Integer> numberSet = Stream.of(3, 5, -3, 3, 4, 5)
                .collect(Collectors.toSet());
        System.out.println(numberSet);
        // 결과 : [-3, 3, 4, 5]

        // 절대값 찾기 리스트
        List<Integer> numberList2 = Stream.of(3, 5, -3, 3, 4, 5)
                .collect(Collectors.mapping(x -> Math.abs(x), Collectors.toList()));
        System.out.println(numberList2);
        // 결과 : [3, 5, 3, 3, 4, 5]

        // 절대값 찾기 셋
        Set<Integer> numberSet2 = Stream.of(3, 5, -3, 3, 4, 5)
                .collect(Collectors.mapping(x -> Math.abs(x), Collectors.toSet()));
        System.out.println(numberSet2);
        // 결과 : [3, 4, 5]

        // 리듀스
        int sum = Stream.of(3, 5, -3, 3, 4, 5)
                .collect(Collectors.reducing(0, (x, y) -> x + y));
        System.out.println(sum);
        // 결과 : 17
    }
}
