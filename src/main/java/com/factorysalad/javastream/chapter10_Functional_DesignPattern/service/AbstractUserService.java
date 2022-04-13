package com.factorysalad.javastream.chapter10_Functional_DesignPattern.service;

import com.factorysalad.javastream.chapter10_Functional_DesignPattern.model.User;

/*
알고리즘의 뼈대를 제공하는 클래스이다.
 */
public abstract class AbstractUserService {
    // 유저의 검증 (하위 구현 클래스에 맡긴다)
    protected abstract boolean validateUser(User user);

    // 유저를 저장 (하위 구현 클래스에 맡긴다)
    protected abstract void writeToDB(User user);

    // ***** 알고리즘의 뼈대 *****
    // 검증이 되면 저장하고 아니면 에러메시지 전달.
    public void createUser(User user) {
        if (validateUser(user)) {
            writeToDB(user);
        } else {
            System.out.println("Cannot create user");
        }
    }
}