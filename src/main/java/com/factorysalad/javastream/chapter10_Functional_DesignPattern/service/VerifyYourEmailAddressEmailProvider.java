package com.factorysalad.javastream.chapter10_Functional_DesignPattern.service;

import com.factorysalad.javastream.chapter10_Functional_DesignPattern.model.User;

public class VerifyYourEmailAddressEmailProvider implements EmailProvider {

    @Override
    public String getEmail(User user) {
        return "'Verify Your Email Address' email for " + user.getName();
    }

}
