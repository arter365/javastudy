package com.factorysalad.javastudy.O_Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
스트림은 다양한 데이터 소스를 표준화된 방법으로 다루기 위한 것이다.
데이터소스 -> Stream -> 중간작업 N번 -> 최종작업 1번
 */
public class AStream {
    public static void main(String[] args) {
        // 스트림은 데이터 소스로부터 데이터를 읽기만 할 뿐 변경하지 않는다.
        // 스트림은 일회용이다.
        List<String> names = new ArrayList<>();
        names.add("keesun");
        names.add("whiteship");
        names.add("toby");
        names.add("foo");

        // 병렬처리가능. 실시간 스트림은 무제한 일 수 있다.
        System.out.println("#1 ============================================================================");
        // #1 : stream()을 얻다 : stream은 데이터를 담는 컬렉션이 아니라 하나씩 흐르는 컨베이어벨트이다. 스트림은 원본데이터를 변경하지 않는다.
        Stream<String> stream1 = names.stream();
        names.forEach(System.out::println);
        stream1.forEach(System.out::println);

        System.out.println("#2 ============================================================================");
        // #2 : stream의 메서드는 한개의 중개 오퍼레이션과 한개의 종료 오퍼레이션의 결합으로 구성된다. (stream을 반환하면 중개 오퍼레이션)
        // Stream은 1회용이라서 이미 닫혔다. 그래서 아래와 같이 새로 만들어야 한다.
        names.stream()
                // 중개 오퍼레이션 map은 스트림 내 요소들을 인자를 통해 받은 람다로 변환하여 새로운 스트림에 담게된다. (mapping이라 한다)
                .map(s -> { // 하나의 인자로 여러 작업을 할 때 / 하나의 일만 처리하면 System.out::print
                    // 이 구문은 실행되지 않는다. 현재는 파이프라인을 구축하는 단계일 뿐이다.
                    // 실행은 종료 오퍼레이션(collect. allMatch, count, forEach, min, max...)을 만나야 실행된다.
                    System.out.println(s);
                    return s.toUpperCase(); // 두줄 이상의 구문은 {}를 하여야 하고 return을 생략 할 수 없다.
                });

        System.out.println("#3 ============================================================================");
        // #3 : 종료오퍼레이션을 만나면 파이프라인이 동작(시작)한다. (여기서는 forEach)
        names.stream().map(String::toUpperCase).forEach(System.out::println);

        System.out.println("#4 ============================================================================");
        // #4 : parallelStream : 데이터가 방대한 경우 jvm에서 병렬로 처리하게 만들 수 있다. (병렬처리가 반드시 빠르다고 할 수 없다. 스레드 비용이 발생한다. 대안 : reactor)
        List<String> collect1 = names.parallelStream()
                .map(s -> {
                    System.out.println(s + " " + Thread.currentThread().getName());
                    return s.toUpperCase();
                }).collect(Collectors.toList()); // collect 예제
        collect1.forEach(System.out::println);
    }
}
