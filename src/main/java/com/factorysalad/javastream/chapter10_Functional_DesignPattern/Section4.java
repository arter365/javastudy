package com.factorysalad.javastream.chapter10_Functional_DesignPattern;

import com.factorysalad.javastream.chapter10_Functional_DesignPattern.model.User;
import com.factorysalad.javastream.chapter10_Functional_DesignPattern.service.InternalUserService;
import com.factorysalad.javastream.chapter10_Functional_DesignPattern.service.UserService;
import com.factorysalad.javastream.chapter10_Functional_DesignPattern.service.UserServiceInFunctionalWay;

import java.util.Arrays;

public class Section4 {
    public static void main(String[] args) {
        User alice = User.builder(1, "Alice")
                .with(builder -> {
                    builder.emailAddress = "alice@fastcampus.co.kr";
                    builder.isVerified = false;
                    builder.friendUserIds = Arrays.asList(201, 202, 203, 204, 211, 212, 213, 214);
                }).build();

        UserService userService = new UserService();
        InternalUserService internalUserService = new InternalUserService();

        userService.createUser(alice);
        internalUserService.createUser(alice);

        UserServiceInFunctionalWay userServiceInFunctionalWay = new UserServiceInFunctionalWay(
                user -> {
                    System.out.println("Validating user " + user.getName());
                    return user.getName() != null && user.getEmailAddress().isPresent();
                },
                user -> {
                    System.out.println("Writing user " + user.getName() + " to DB");
                });
        userServiceInFunctionalWay.createUser(alice);
    }
}
