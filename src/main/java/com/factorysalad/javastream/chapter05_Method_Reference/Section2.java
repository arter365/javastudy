package com.factorysalad.javastream.chapter05_Method_Reference;

import com.factorysalad.javastream.chapter05_Method_Reference.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

/*
해당 클래스의 인스턴스를 매개변수로 넘겨 메서드를 생행해 주는 함수.
 */
public class Section2 {
    public static void main(String[] args) {
        Function<String, Integer> strLength = String::length;
        int length = strLength.apply("hello world");
        System.out.println(length);
        // 결과 : 11

        BiPredicate<String, String> strEquals = String::equals;
        boolean helloEqualsWorld = strEquals.test("hello", "world");
        System.out.println(strEquals.test("hello", "hello"));
        // 결과 : true

        List<User> users = new ArrayList<>();
        users.add(new User(3, "Alice"));
        users.add(new User(1, "Charlie"));
        users.add(new User(5, "Bob"));

        // 기존 클래스의 메서드 레퍼런스를 파라메터로 넘기는 방법
        printUserField(users, User::getName);
        /*
        결과 :
        Alice
        Charlie
        Bob
         */
    }

    public static void printUserField(List<User> users, Function<User, Object> getter) {
        for (User user : users) {
            System.out.println(getter.apply(user));
        }
    }
}
