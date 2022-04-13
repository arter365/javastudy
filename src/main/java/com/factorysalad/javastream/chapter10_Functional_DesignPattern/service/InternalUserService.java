package com.factorysalad.javastream.chapter10_Functional_DesignPattern.service;

import com.factorysalad.javastream.chapter10_Functional_DesignPattern.model.User;

/*
유저서비스 구현
디테일한 내용만 구현하면 전체 유저를 만드는 틀은 상위 클래스가 제공한다.
 */
public class InternalUserService extends AbstractUserService {
    @Override
    protected boolean validateUser(User user) {
        System.out.println("validating internal user " + user.getName());
        return true;
    }

    @Override
    protected void writeToDB(User user) {
        System.out.println("Writing user " + user.getName() + " to internal DB");
    }
}
