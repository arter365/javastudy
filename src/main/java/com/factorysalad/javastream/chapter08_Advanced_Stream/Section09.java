package com.factorysalad.javastream.chapter08_Advanced_Stream;

import com.factorysalad.javastream.chapter08_Advanced_Stream.model.User;
import com.factorysalad.javastream.chapter08_Advanced_Stream.service.EmailService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/*
forEach
 */
public class Section09 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 5, 2, 1);
        numbers.stream().forEach(number -> System.out.println("The number is " + number));
        /*
        결과 :
        The number is 3
        The number is 5
        The number is 2
        The number is 1
         */

        // 추가적인 작업이 필요없다면 stream을 안적고 forEach를 사용 할 수 있다.
        numbers.forEach(number -> System.out.println("The number is " + number));
        /*
        결과 :
        The number is 3
        The number is 5
        The number is 2
        The number is 1
         */

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
        List<User> users = Arrays.asList(user1, user2, user3);

        // -------------------------------------------------------

        // 이메일 서비스
        EmailService emailService = new EmailService();

        // 이메일을 확인하지 않은 사람에게 이메일 보내기
        users.stream()
                .filter(user -> !user.isVerified())
                .forEach(emailService::sendVerifyYourEmailEmail);
        /*
        결과 :
        Sending 'Verify Your Email' email to bob@fastcampus.co.kr
        Sending 'Verify Your Email' email to charlie@fastcampus.co.kr
         */

        // 예제1) for문 안에서 index 사용한 경우
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.println("Do an operation on user " + user.getName() + " at index " + i);
        }
        /*
        결과 :
        Do an operation on user Alice at index 0
        Do an operation on user Bob at index 1
        Do an operation on user Charlie at index 2
         */

        // 예제2) 위의 방식을 스트림 방식으로 처리 (range 사용, 시작점은 포함하고 끝점은 포함하지 않는다.)
        IntStream.range(0, users.size()).forEach(i -> {
            User user = users.get(i);
            System.out.println("Do an operation on user " + user.getName() + " at index " + i);
        });
        /*
        결과 :
        Do an operation on user Alice at index 0
        Do an operation on user Bob at index 1
        Do an operation on user Charlie at index 2
         */
    }
}
