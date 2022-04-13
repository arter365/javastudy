package com.factorysalad.javastream.chapter10_Functional_DesignPattern.service;

import com.factorysalad.javastream.chapter10_Functional_DesignPattern.model.User;

import java.util.function.Consumer;
import java.util.function.Predicate;

/*
함수형 템플릿 메서드 뼈대
 */
public class UserServiceInFunctionalWay {
    // 검증을 위한 함수형 인터페이스
    private final Predicate<User> validateUser;
    // 저장을 위한 함수형 인터페이스
    private final Consumer<User> writeToDB;

    // 생성자를 통해 외부에서 받는다.
    public UserServiceInFunctionalWay(Predicate<User> validateUser, Consumer<User> writeToDB) {
        this.validateUser = validateUser;
        this.writeToDB = writeToDB;
    }

    // 로직 뼈대
    public void createUser(User user) {
        if (validateUser.test(user)) {
            writeToDB.accept(user);
        } else {
            System.out.println("Cannot create user");
        }
    }
}
