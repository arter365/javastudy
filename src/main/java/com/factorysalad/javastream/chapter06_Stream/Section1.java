package com.factorysalad.javastream.chapter06_Stream;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Stream을 만드는 다양한 방법
 */
public class Section1 {
    public static void main(String[] args) {
        Stream<String> nameStream = Stream.of("Alice", "Bob", "Charlie");
        // 스트림을 바로 출력하는 방법이 없기 때문에 아래와 같이 처리했다.
        List<String> names = nameStream.collect(Collectors.toList());
        System.out.println(names);
        // 결과 : [Alice, Bob, Charlie]

        String[] cityArray = new String[] {"San Jose", "Seoul", "Tokyo" };
        Stream<String> cityStream = Arrays.stream(cityArray);
        List<String> cityList = cityStream.collect(Collectors.toList());
        System.out.println(cityList);
        // 결과 : [San Jose, Seoul, Tokyo]

        // 가장 많이 사용하는 방법
        Set<Integer> numberSet = new HashSet<>(Arrays.asList(3, 5, 7));
        Stream<Integer> numberStream = numberSet.stream();
        List<Integer> numberList = numberStream.collect(Collectors.toList());
        System.out.println(numberList);
        // 결과 : [3, 5, 7]
    }
}
