package com.factorysalad.javastream.chapter10_Functional_DesignPattern;

import com.factorysalad.javastream.chapter10_Functional_DesignPattern.model.User;

public class Section1 {
    public static void main(String[] args) {
        User user = User.builder(1, "Alice")
                .with(builder -> {
                    builder.emailAddress = "alice@fastcampus.co.kr";
                    builder.isVerified = true;
                }).build();
        System.out.println(user);
    }
}
