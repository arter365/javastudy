package com.factorysalad.javastream.chapter10_Functional_DesignPattern.service;

import com.factorysalad.javastream.chapter10_Functional_DesignPattern.model.User;

public class MakeMoreFriendsEmailProvider implements EmailProvider {
    @Override
    public String getEmail(User user) {
        return "'Make More Friends' email for " + user.getName();
    }
}
