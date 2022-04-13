package com.factorysalad.javastream.chapter08_Advanced_Stream;

import com.factorysalad.javastream.chapter08_Advanced_Stream.model.Order.OrderStatus;
import com.factorysalad.javastream.chapter08_Advanced_Stream.model.Order;
import com.factorysalad.javastream.chapter08_Advanced_Stream.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/*
max, min, count는 Terminal Operation 이다.

Optional<T> max(Comparator<? super T> comparator);
max : Stream 안의 데이터 중 최대값을 반환. Stream이 비어있다면 빈 Optional 반환.

Optional<T> min(Comparator<? super T> comparator);
min : Stream 안의 데이터 중 최소값을 반환. Stream이 비어있다면 빈 Optional 반환.

long count();
count : Stream 안의 데이터의 개수를 반환.
 */
public class Section01 {
    public static void main(String[] args) {
        // max
        Optional<Integer> max = Stream.of(5, 3, 6, 2, 1)
                .max(Integer::compareTo);
        System.out.println("max : " + max.get());
        // 결과 : 6

        // ----------------------------------------------------

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

        // ----------------------------------------------------

        // min
        User firstUser = users.stream()
                .min((u1, u2) -> u1.getName().compareTo(u2.getName()))
                .get();
        System.out.println("min : " + firstUser);
        // 결과 : User{id=101, name='Alice', emailAddress='alice@fastcampus.co.kr', isVerified=true, createdAt=null, friendUserIds=null}

        // count
        long positiveIntegerCount = Stream.of(1, -4, 5, -3, 6)
                .filter(x -> x > 0)
                .count();
        System.out.println("Positive integers: " + positiveIntegerCount);
        // 결과 : 3

        // ----------------------------------------------------------------

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        user1.setCreatedAt(now.minusDays(2));
        user2.setCreatedAt(now.minusHours(10));
        user3.setCreatedAt(now.minusHours(1));
        // 유저 한명 더 추가
        User user4 = new User()
                .setId(104)
                .setName("David")
                .setVerified(true)
                .setEmailAddress("david@fastcampus.co.kr")
                .setCreatedAt(now.minusHours(27));
        users = Arrays.asList(user1, user2, user3, user4);

        // ----------------------------------------------------------------

        // count : 최근 24시간 안에 가입한 유저중에서 검증되지 않은 유저는 몇명이 있는지?
        long unverfiedUsersIn24Hrs = users.stream()
                .filter(user -> user.getCreatedAt().isAfter(now.minusDays(1)))
                .filter(user -> !user.isVerified())
                .count();
        System.out.println(unverfiedUsersIn24Hrs);
        // 결과 : 2

        // ----------------------------------------------------------------

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

        // ----------------------------------------------------------------

        // TODO: find order with highest amount an in ERROR status
        Order erroredOrderWithMaxAmount = orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.ERROR)
                .max((o1, o2) -> o1.getAmount().compareTo(o2.getAmount()))
                .get();
        System.out.println(erroredOrderWithMaxAmount);
        // 결과 : Order{id=1002, createdAt=null, createdByUserId=0, status=ERROR, amount=4000, orderLines=null}

        // 가장 비싼 주문의 금액만 표시
        BigDecimal maxErroredAmount = orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.ERROR)
                .map(Order::getAmount)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO); // 값이 없으면 빅데시멀 0을 기본값으로 넣는다.
        System.out.println(maxErroredAmount);
        // 결과 : 4000
    }
}
