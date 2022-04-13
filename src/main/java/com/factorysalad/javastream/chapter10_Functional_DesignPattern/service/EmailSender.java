package com.factorysalad.javastream.chapter10_Functional_DesignPattern.service;

import com.factorysalad.javastream.chapter10_Functional_DesignPattern.model.User;

/*
이메일 프로바이더에 따라서 다양한 이메일을 보낼 수 있게 해준다.
 */
public class EmailSender {
    private EmailProvider emailProvider;

    // 실시간으로 전략을 바꿀 수 있는 setter가 있다.
    public EmailSender setEmailProvider(EmailProvider emailProvider) {
        this.emailProvider = emailProvider;
        return this;
    }

    // 현재 받아온 전략으로 이메일을 만들고 이메일을 보내는 메서드
    public void sendEmail(User user) {
        String email = emailProvider.getEmail(user);
        System.out.println("Sending " + email);
    }
}

