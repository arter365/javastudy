package com.factorysalad.javastream.chapter08_Advanced_Stream;

import com.factorysalad.javastream.chapter08_Advanced_Stream.model.User;
import com.factorysalad.javastream.chapter08_Advanced_Stream.service.EmailService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
Parallel Stream : Stream을 병렬로 처리
 */
public class Section10 {
    public static void main(String[] args) {
        // 인증되지 않은 유저에게 이메일을 보내는 작업이 병렬처리하기에 좋은 예제이다.
        // 1) 인증되지 않은 유저 고르기 (순서 중요하지 않음)
        // 2) 이메일 보내기 (순서 중요하지 않음)

        // -------------------------------------------------------

        User user1 = new User()
                .setId(101)
                .setName("Alice")
                .setVerified(true)
                .setEmailAddress("alice@fastcampus.co.kr");
        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setVerified(false)
                .setEmailAddress("bob@fastcampus.co.kr");
        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setVerified(false)
                .setEmailAddress("charlie@fastcampus.co.kr");
        User user4 = new User()
                .setId(104)
                .setName("David")
                .setEmailAddress("david@fastcampus.co.kr")
                .setVerified(true);
        User user5 = new User()
                .setId(105)
                .setName("Eve")
                .setEmailAddress("eve@fastcampus.co.kr")
                .setVerified(false);
        User user6 = new User()
                .setId(106)
                .setName("Frank")
                .setEmailAddress("frank@fastcampus.co.kr")
                .setVerified(false);
        List<User> users = Arrays.asList(user1, user2, user3, user4, user5, user6);

        // -------------------------------------------------------

        // 순차처리
        long startTime = System.currentTimeMillis();
        EmailService emailService = new EmailService();
        users.stream()
                .filter(user -> !user.isVerified())
                .forEach(emailService::sendVerifyYourEmailEmail);
        long endTime = System.currentTimeMillis();
        System.out.println("Sequential: " + (endTime - startTime) + "ms");
        /*
        결과 : 순서가 유지된다.
        Sending 'Verify Your Email' email to bob@fastcampus.co.kr
        Sending 'Verify Your Email' email to charlie@fastcampus.co.kr
        Sending 'Verify Your Email' email to eve@fastcampus.co.kr
        Sending 'Verify Your Email' email to frank@fastcampus.co.kr
        Sequential: 25ms
         */

        // 병렬처리
        startTime = System.currentTimeMillis();
        users.stream().parallel()
                .filter(user -> !user.isVerified())
                .forEach(emailService::sendVerifyYourEmailEmail);
        endTime = System.currentTimeMillis();
        System.out.println("Parallel: " + (endTime - startTime) + "ms");
        /*
        결과 : 순서가 유지 되지 않는다.
        Sending 'Verify Your Email' email to eve@fastcampus.co.kr
        Sending 'Verify Your Email' email to frank@fastcampus.co.kr
        Sending 'Verify Your Email' email to bob@fastcampus.co.kr
        Sending 'Verify Your Email' email to charlie@fastcampus.co.kr
        Parallel: 10ms
         */

        /*
        병렬처리에서 순서를 지켜줘도 결과만 순서를 지킬 뿐 처리는 랜덤으로 처리된다.
         */
        List<User> processedUsers = users.parallelStream()
                .map(user -> {
                    System.out.println("Capitalize user name for user " + user.getId());
                    user.setName(user.getName().toUpperCase());
                    return user;
                })
                .map(user -> {
                    System.out.println("Set 'isVerified' to true for user " + user.getId());
                    user.setVerified(true);
                    return user;
                })
                .collect(Collectors.toList());
        /*
        결과 : 순서가 섞여 있다.
        Capitalize user name for user 101
        Set 'isVerified' to true for user 101
        Capitalize user name for user 106
        Set 'isVerified' to true for user 106
        Capitalize user name for user 104
        Set 'isVerified' to true for user 104
        Capitalize user name for user 102
        Capitalize user name for user 103
        Set 'isVerified' to true for user 103
        Set 'isVerified' to true for user 102
        Capitalize user name for user 105
        Set 'isVerified' to true for user 105
         */
        System.out.println(processedUsers);
        /*
        결과 : 최종 결과물에서 결과대로 순서를 지켜 줄 뿐이다. (중간처리 과정이 순서가 중요하면 어렵다. 뮤텍스등을 사용하면 오히려 순차처리보다 느릴 수 있다)
        [User{id=101, name='ALICE', emailAddress='alice@fastcampus.co.kr', isVerified=true, createdAt=null, friendUserIds=null},
         User{id=102, name='BOB', emailAddress='bob@fastcampus.co.kr', isVerified=true, createdAt=null, friendUserIds=null},
         User{id=103, name='CHARLIE', emailAddress='charlie@fastcampus.co.kr', isVerified=true, createdAt=null, friendUserIds=null},
         User{id=104, name='DAVID', emailAddress='david@fastcampus.co.kr', isVerified=true, createdAt=null, friendUserIds=null},
         User{id=105, name='EVE', emailAddress='eve@fastcampus.co.kr', isVerified=true, createdAt=null, friendUserIds=null},
         User{id=106, name='FRANK', emailAddress='frank@fastcampus.co.kr', isVerified=true, createdAt=null, friendUserIds=null}]
         */
    }
}
