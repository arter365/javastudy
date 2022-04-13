package com.factorysalad.javastream.chapter08_Advanced_Stream;

import com.factorysalad.javastream.chapter08_Advanced_Stream.model.Order.OrderStatus;
import com.factorysalad.javastream.chapter08_Advanced_Stream.model.Order;
import com.factorysalad.javastream.chapter08_Advanced_Stream.model.User;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/*
boolean allMatch(Predicate<? super T> predicate);
allMatch : Stream 안의 모든 데이터가 predicate를 만족하면 true

boolean anyMatch(Predicate<? super T> predicate);
anyMatch : Stream 안의 데이터 중 하나라도 predicate를 만족하면 true
 */
public class Section02 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, -4, 2, 7, 9);

        // allMathch
        boolean allPostive = numbers.stream()
                .allMatch(number -> number > 0);
        System.out.println("Are all numbers positive: " + allPostive);
        // 결과 : false

        // anyMatch
        boolean anyNegative = numbers.stream()
                .anyMatch(number -> number < 0);
        System.out.println("Is any number negative: " + anyNegative);
        // 결과 : true

        //---------------------------------------------------------

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

        // 모든 유저가 확인 되었는지
        boolean areAllUserVerified = users.stream()
                .allMatch(User::isVerified);
        System.out.println(areAllUserVerified);
        // 결과 : false

        //--------------------------------------------------

        Order order1 = new Order()
                .setId(1001L)
                .setAmount(BigDecimal.valueOf(2000))
                .setStatus(OrderStatus.CREATED);
        Order order2 = new Order()
                .setId(1002L)
                .setAmount(BigDecimal.valueOf(4000))
                .setStatus(OrderStatus.ERROR);
        Order order3 = new Order()
                .setId(1003L)
                .setAmount(BigDecimal.valueOf(3000))
                .setStatus(OrderStatus.ERROR);
        Order order4 = new Order()
                .setId(1004L)
                .setAmount(BigDecimal.valueOf(7000))
                .setStatus(OrderStatus.PROCESSED);
        List<Order> orders = Arrays.asList(order1, order2, order3, order4);

        // TODO: check if any of orders is in ERROR status
        boolean isAnyOrderInErrorsStatus = orders.stream()
                .anyMatch(order -> order.getStatus() == OrderStatus.ERROR);
        System.out.println(isAnyOrderInErrorsStatus);
        // 결과 : true

    }
}
