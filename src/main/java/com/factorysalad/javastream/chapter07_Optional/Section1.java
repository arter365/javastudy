package com.factorysalad.javastream.chapter07_Optional;

import com.factorysalad.javastream.chapter07_Optional.model.User;

import java.util.Optional;

/*
Optional 생성과 메서드 사용법
 */
public class Section1 {
    public static void main(String[] args) {
        User user1 = new User()
                .setId(1001)
                .setName("Alice")
                .setVerified(false);

        User user2 = new User()
                .setId(1001)
                .setName("Alice")
                .setEmailAddress("alice@fastcampus.co.kr")
                .setVerified(false);

        System.out.println("Same? :" + userEquals(user1, user2));
        // 결과 : Same? :false

        String someEmail = "some@email.com";
        String nullEmail = null;

        // Optional을 만드는 방법
        Optional<String> maybeEmail = Optional.of(someEmail);           // null을 넘기면 에러가 발생한다.
        Optional<String> maybeEmail2 = Optional.empty();                // 빈 Optional을 만든다.
        Optional<String> maybeEmail3 = Optional.ofNullable(someEmail);  // null인지 아닌지 모를 때 사용한다.
        Optional<String> maybeEmail4 = Optional.ofNullable(nullEmail);  // null인지 아닌지 모를 때 사용한다. (empty로 만들어준다)

        String email = maybeEmail.get();
        System.out.println(email);
        // 결과 : some@email.com

        if (maybeEmail2.isPresent()) {  // isPresent() : Optional의 값이 비어있는지 확인한다.
            System.out.println(maybeEmail2.get());  // get() : Optional의 값을 가져온다. 만약 값이 없으면 에러가 발생한다.
        }
        // 결과 : 없음.

        // -----------------------------------------------------

        String defaultEmail = "default@email.com";
        String email3 = maybeEmail2.orElse(defaultEmail);   // orElse() : Optional이 null이면 공급된 값을, 아니면 값을 가져온다.
        System.out.println(email3);
        // 결과 : default@email.com

        String email4 = maybeEmail2.orElseGet(() -> defaultEmail);  // orElseGet() : null이면 supplier로 공급되는 값을 리턴.
        System.out.println(email4);
        // 결과 : default@email.com

        // orElseThrow() : null이면 exceptionSupplier로 공급되는 exception을 던진다.
        String email5 = maybeEmail2.orElseThrow(() -> new RuntimeException("email not present"));
        /*
        결과 :
        Exception in thread "main" java.lang.RuntimeException: email not present
            at com.factorysalad.javastream.chapter07_Optional.Section1.lambda$main$1(Section1.java:49)
            at java.base/java.util.Optional.orElseThrow(Optional.java:408)
            at com.factorysalad.javastream.chapter07_Optional.Section1.main(Section1.java:49)
         */
    }

    public static boolean userEquals(User u1, User u2) {
        return u1.getId() == u2.getId()
                && u1.getName().equals(u2.getName())
                && u1.getEmailAddress().equals(u2.getEmailAddress())
                && u1.isVerified() == u2.isVerified();
    }
}
