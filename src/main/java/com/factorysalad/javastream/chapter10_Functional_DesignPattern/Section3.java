package com.factorysalad.javastream.chapter10_Functional_DesignPattern;

import com.factorysalad.javastream.chapter10_Functional_DesignPattern.model.User;
import com.factorysalad.javastream.chapter10_Functional_DesignPattern.service.EmailProvider;
import com.factorysalad.javastream.chapter10_Functional_DesignPattern.service.EmailSender;
import com.factorysalad.javastream.chapter10_Functional_DesignPattern.service.MakeMoreFriendsEmailProvider;
import com.factorysalad.javastream.chapter10_Functional_DesignPattern.service.VerifyYourEmailAddressEmailProvider;

import java.util.Arrays;
import java.util.List;

/*
[행동패턴]
Strategy Pattern : 전략 패턴

- 대표적인 행동 패턴
- 런타임에 어떤 전략(알고리즘)을 사용할 지 선택할 수 있게 해준다.
- 전략들을 캡슐화하여 간단하게 교체 할 수 있게 해준다.
 */
public class Section3 {
    public static void main(String[] args) {

        //----------------------------------------------------------------

        User user1 = User.builder(1, "Alice")
                .with(builder -> {
                    builder.emailAddress = "alice@fastcampus.co.kr";
                    builder.isVerified = false;
                    builder.friendUserIds = Arrays.asList(201, 202, 203, 204, 211, 212, 213, 214);
                }).build();
        User user2 = User.builder(2, "Bob")
                .with(builder -> {
                    builder.emailAddress = "bob@fastcampus.co.kr";
                    builder.isVerified = true;
                    builder.friendUserIds = Arrays.asList(212, 213, 214);
                }).build();
        User user3 = User.builder(3, "Charlie")
                .with(builder -> {
                    builder.emailAddress = "charlie@fastcampus.co.kr";
                    builder.isVerified = true;
                    builder.friendUserIds = Arrays.asList(201, 202, 203, 204, 211, 212);
                }).build();
        List<User> users = Arrays.asList(user1, user2, user3);

        //----------------------------------------------------------------

        // 이메일 sender를 만든다.
        EmailSender emailSender = new EmailSender();

        // 이메일 전략2가지를 만든다.
        EmailProvider verifyYourEmailAddressEmailProvider = new VerifyYourEmailAddressEmailProvider();
        EmailProvider makeMoreFriendsEmailProvider = new MakeMoreFriendsEmailProvider();

        // 전략사용
        emailSender.setEmailProvider(verifyYourEmailAddressEmailProvider);
        users.stream()
                .filter(user -> !user.isVerified())
                .forEach(emailSender::sendEmail);
        /*
        결과 : Sending 'Verify Your Email Address' email for Alice
         */

        // 전략사용
        emailSender.setEmailProvider(makeMoreFriendsEmailProvider);
        users.stream()
                .filter(User::isVerified)
                .filter(user -> user.getFriendUserIds().size() <= 5)
                .forEach(emailSender::sendEmail);
        /*
        결과 : Sending 'Make More Friends' email for Bob
         */

        // 전략을 클래스로 만들지 않고 람다식으로 구현하는 방식
        emailSender.setEmailProvider(user -> "'Play With Friends' email for " + user.getName());
        users.stream()
                .filter(User::isVerified)
                .filter(user -> user.getFriendUserIds().size() > 5)
                .forEach(emailSender::sendEmail);
        /*
        결과 : Sending 'Play With Friends' email for Charlie
         */
    }
}
