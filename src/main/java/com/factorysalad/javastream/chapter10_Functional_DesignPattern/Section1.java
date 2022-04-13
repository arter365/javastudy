package com.factorysalad.javastream.chapter10_Functional_DesignPattern;

import com.factorysalad.javastream.chapter10_Functional_DesignPattern.model.User;

/*
[생성패턴]
Builder Pattern : 빌더 패턴

- 대표적인 생성 패턴
- 객체의 생성에 대한 로직과 표현에 대한 로직을 분리해 준다.
- 객체의 생성과정을 유연하게 해준다.
- 객체의 생성과정을 정의하고 싶거나 필드가 많아 constructor가 복잡해질 때 유용하다.
 */
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
