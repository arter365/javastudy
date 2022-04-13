package com.factorysalad.javastream.chapter06_Stream.model;

import java.util.List;

public class User {
    private int id;
    private String name;
    private String emailAddress;
    // 유저 이메일에 대한 확인이 되었는지
    private boolean isVerified;
    private List<Integer> friendUserIds;

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        // 체이닝을 할 수 있도록 return this를 넣었다.
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public User setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public User setVerified(boolean isVerified) {
        this.isVerified = isVerified;
        return this;
    }

    public List<Integer> getFriendUserIds() {
        return friendUserIds;
    }

    public User setFriendUserIds(List<Integer> friendUserIds) {
        this.friendUserIds = friendUserIds;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", isVerified=" + isVerified +
                ", friendUserIds=" + friendUserIds +
                '}';
    }
}
