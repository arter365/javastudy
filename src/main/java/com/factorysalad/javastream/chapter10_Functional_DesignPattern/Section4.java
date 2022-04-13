package com.factorysalad.javastream.chapter10_Functional_DesignPattern;

import com.factorysalad.javastream.chapter10_Functional_DesignPattern.model.User;
import com.factorysalad.javastream.chapter10_Functional_DesignPattern.service.InternalUserService;
import com.factorysalad.javastream.chapter10_Functional_DesignPattern.service.UserService;
import com.factorysalad.javastream.chapter10_Functional_DesignPattern.service.UserServiceInFunctionalWay;

import java.util.Arrays;

/*
[행동패턴]
Template Method Pattern : 템플릿 메소드 패턴  (정말 많이 사용된다)

- 또 하나의 대표적인 행동 패턴
- 상위 클래스는 알고리즘의 뼈대만을 정의하고 알고리즘의 각 단계는 하위 클래스에게 정의를 위임하는 패턴
- 알고리즘의 구조를 변경하지 않고 세부 단계들을 유연하게 변경할 수 있게 해 준다.
 */
public class Section4 {
    public static void main(String[] args) {
        // 유저 생성
        User alice = User.builder(1, "Alice")
                .with(builder -> {
                    builder.emailAddress = "alice@fastcampus.co.kr";
                    builder.isVerified = false;
                    builder.friendUserIds = Arrays.asList(201, 202, 203, 204, 211, 212, 213, 214);
                }).build();

        // 유저 서비스 2개 생성
        UserService userService = new UserService();
        InternalUserService internalUserService = new InternalUserService();

        // 실행
        userService.createUser(alice);
        /*
        결과 :
        Validating user Alice
        Writing user Alice to DB
         */

        // 실행
        internalUserService.createUser(alice);
        /*
        결과 :
        validating internal user Alice
        Writing user Alice to internal DB
         */

        /*
        클래스로 만들지 않고 함수형으로 구현해보자.
         */
        UserServiceInFunctionalWay userServiceInFunctionalWay = new UserServiceInFunctionalWay(
                // 인자를 2개 만들어 넘긴다.
                user -> {
                    // 함수로 기능을 넘긴다.
                    System.out.println("Validating user " + user.getName());
                    return user.getName() != null && user.getEmailAddress().isPresent();
                },
                user -> {
                    // 함수로 기능을 넘긴다.
                    System.out.println("Writing user " + user.getName() + " to DB");
                });
        userServiceInFunctionalWay.createUser(alice);
        /*
        결과 :
        Validating user Alice
        Writing user Alice to DB
         */
    }
}
