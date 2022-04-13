package com.factorysalad.javastream.chapter10_Functional_DesignPattern.service;

import com.factorysalad.javastream.chapter10_Functional_DesignPattern.model.User;

/*
유저를 받아서 이메일을 리턴해주는 전략을 담는 하나의 메서드를 가진 인터페이스.
테스트이기 때문에 이메일 대신 String을 리턴한다.
 */
public interface EmailProvider {
    String getEmail(User user);
}
