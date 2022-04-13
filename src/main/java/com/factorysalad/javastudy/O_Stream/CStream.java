package com.factorysalad.javastudy.O_Stream;

import com.factorysalad.javastudy.O_Stream.dto.Gender;
import com.factorysalad.javastudy.O_Stream.dto.Person;

import java.util.*;
import java.util.stream.Collectors;

public class CStream {
    private static List<Person> getPeople() {
        return List.of(
                new Person("Antonio", 20, Gender.MALE),
                new Person("Alina Smith", 33, Gender.FEMALE),
                new Person("Helen White", 57, Gender.FEMALE),
                new Person("Alex Boz", 14, Gender.MALE),
                new Person("Jamie Goa", 99, Gender.MALE),
                new Person("Anna Cook", 7, Gender.FEMALE),
                new Person("Zelda Brown", 120, Gender.FEMALE)
        );
    }

    public static void main(String[] args) {
        List<Person> people = getPeople();

        // 중간연산자 : distinct, filter, limit, skip, peek, sorted
        // 중간연산자 : map, mapToDouble, mapToInt, maoToLong
        // 중간연산자 : flatMap, flatMapToDouble, flatMapToInt, flatMapToLong
        // 최종연산자 : forEach, forEachOrdered, count, max, min, findAny, findFirst, allMatch, anyMatch, noneMatch
        // 최종연산자 : toArray, reduce, collect

        // Filter
        System.out.println("---------- Filter ----------");
        List<Person> females = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList());
        females.forEach(System.out::println);

        // Sort
        System.out.println("---------- Sort ----------");
        List<Person> sorted = people.stream()
                .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender).reversed())
                .collect(Collectors.toList());
        sorted.forEach(System.out::println);

        // All match : 모든 요소들이 매개값(Predicate)으로 주어진 조건에 만족하는지
        System.out.println("---------- All match ----------");
        boolean allMatch = people.stream()
                .allMatch(person -> person.getAge() > 5);
        System.out.println(allMatch);

        // Any match : 최소한 한 개의 요소가 주어진 조건에 만족하는지
        System.out.println("---------- Any match ----------");
        boolean anyMatch = people.stream()
                .anyMatch(person -> person.getAge() > 121);
        System.out.println(anyMatch);

        // None match : 모든 요소들이 주어진 조건을 만족하지 않는지
        System.out.println("---------- None match ----------");
        boolean noneMatch = people.stream()
                .noneMatch(person -> person.getName().equals("Antonio"));
        System.out.println(noneMatch);

        // Max
        System.out.println("---------- Max ----------");
        people.stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        // Min
        System.out.println("---------- Min ----------");
        people.stream()
                .min(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        // 검색
        Optional<String> oldestFemaleAge = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);
        oldestFemaleAge.ifPresent(System.out::println);

        // Reduce
        System.out.println("---------- Reduce identity ----------");
        Integer reduce1 = people.stream()
                .map(Person -> Person.getAge())
                .reduce(0, (a, b) -> a + b);
        System.out.println(reduce1);
        /*
        new Person("Antonio", 20, Gender.MALE),
        new Person("Alina Smith", 33, Gender.FEMALE),
        new Person("Helen White", 57, Gender.FEMALE),
        new Person("Alex Boz", 14, Gender.MALE),
        new Person("Jamie Goa", 99, Gender.MALE),
        new Person("Anna Cook", 7, Gender.FEMALE),
        new Person("Zelda Brown", 120, Gender.FEMALE)

          0(a) +  20(b) =  20
         20(a) +  33(b) =  53
         53(a) +  57(b) = 110
        110(a) +  14(b) = 124
        124(a) +  99(b) = 223
        223(a) +   7(b) = 230
        230(a) + 120(b) = 350
         */

        // Reduce : identity를 넣지 않으면 Optional을 반환한다.
        System.out.println("---------- Reduce Non identity ----------");
        Optional<Integer> reduce2 = people.stream()
                .map(Person -> Person.getAge())
                .reduce((a, b) -> a + b);
        System.out.println(reduce2.orElse(0));

        // collect : toList()
        System.out.println("---------- collect : toList() ----------");
        List<Person> personList = people.stream()
                .filter(p -> p.getGender() == Gender.FEMALE)
                .collect(Collectors.toList());
        personList.stream().forEach(p -> System.out.println(p.getName()));

        // collect : HashSet
        System.out.println("---------- collect : HashSet ----------");
        HashSet<Person> personHashSet = people.stream()
                .filter(p -> p.getGender() == Gender.MALE)
                .collect(Collectors.toCollection(HashSet::new));
        personHashSet.stream().forEach(p -> System.out.println(p.getName()));

        // Group
        System.out.println("---------- Group ----------");
        Map<Gender, List<Person>> groupByGender = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));
        groupByGender.forEach((gender, p) -> {
            System.out.println(gender);
            p.forEach(System.out::println);
            System.out.println();
        });
    }
}
