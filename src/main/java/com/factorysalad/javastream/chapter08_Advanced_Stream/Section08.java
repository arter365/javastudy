package com.factorysalad.javastream.chapter08_Advanced_Stream;

import com.factorysalad.javastream.chapter08_Advanced_Stream.model.User;
import com.factorysalad.javastream.chapter08_Advanced_Stream.service.EmailService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
collect 알아보기 마지막
 */
public class Section08 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(13, 2, 101, 203, 304, 402, 305, 349, 2312, 203);
        Map<Boolean, List<Integer>> numberPartitions = numbers.stream()
                // 짝수인지 홀수인지 판별하는 predicate를 넘겨준다.
                .collect(Collectors.partitioningBy(number -> number % 2 == 0));
        // true인 짝수 그룹을 보여준다.
        System.out.println("Even number: " + numberPartitions.get(true));
        // 결과 : Even number: [2, 304, 402, 2312]

        // false인 홀수 그룹을 보여준다.
        System.out.println("Odd number: " + numberPartitions.get(false));
        // 결과 : Odd number: [13, 101, 203, 305, 349, 203]

        // ------------------------------------------------------------------------

        User user1 = new User()
                .setId(101)
                .setName("Alice")
                .setEmailAddress("alice@fastcampus.co.kr")
                .setFriendUserIds(Arrays.asList(201, 202, 203, 204, 211, 212, 213, 214));
        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setEmailAddress("bob@fastcampus.co.kr")
                .setFriendUserIds(Arrays.asList(204, 205, 206));
        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setEmailAddress("charlie@fastcampus.co.kr")
                .setFriendUserIds(Arrays.asList(204, 205, 207, 218));
        List<User> users = Arrays.asList(user1, user2, user3);

        // ------------------------------------------------------------------------

        // 친구가 5명 이상이면 친구와 놀아보세요. 이하이면 친구를 만들어 보세요 라는 메일을 보내는 주제
        // 먼저 2 그룹으로 나누는 작업을 진행한다.
        Map<Boolean, List<User>> userPartitions = users.stream()
                .collect(Collectors.partitioningBy(user -> user.getFriendUserIds().size() > 5));

        // 이메일을 보내는 서비스
        EmailService emailService = new EmailService();

        for (User user: userPartitions.get(true)) {
            emailService.sendPlayWithFriendsEmail(user);
        }
        /*
        결과 :
        Sending 'Play With Friends' email to alice@fastcampus.co.kr
         */

        for (User user: userPartitions.get(false)) {
            emailService.sendMakeMoreFriendsEmail(user);
        }
        /*
        결과 :
        Sending 'Make More Friends' email to bob@fastcampus.co.kr
        Sending 'Make More Friends' email to charlie@fastcampus.co.kr
         */
    }
}
