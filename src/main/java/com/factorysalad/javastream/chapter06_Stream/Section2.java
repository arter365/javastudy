package com.factorysalad.javastream.chapter06_Stream;

import com.factorysalad.javastream.chapter06_Stream.model.Order.OrderStatus;
import com.factorysalad.javastream.chapter06_Stream.model.Order;
import com.factorysalad.javastream.chapter06_Stream.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Filter : 거름종이 같은 필터
- 만족하는 데이터만 걸러내는데 사용
- Predicate에 true를 반환하는 데이터만 존재하는 stream을 리턴

Stream<T> filter(Predicate<? super T> predicate);
 */
public class Section2 {
    public static void main(String[] args) {
        // 양수만 골라내는 예제
        Stream<Integer> numberStream = Stream.of(3, -5, 7, 10, -3);
        Stream<Integer> filteredNumberStream = numberStream.filter(x -> x > 0);
        List<Integer> filteredNumbers = filteredNumberStream.collect(Collectors.toList());
        System.out.println(filteredNumbers);
        // 결과 : [3, 7, 10]

        // 위와 동일하지만 체이닝하여 사용한 예제
        List<Integer> newFilteredNumbers = Stream.of(3, -5, 7, 10, -3)
                .filter(x -> x > 0)
                .collect(Collectors.toList());
        System.out.println(newFilteredNumbers);
        // 결과 : [3, 7, 10]

        //------------------------------------------------------------------

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
                .setVerified(true)
                .setEmailAddress("charlie@fastcampus.co.kr");

        List<User> users = Arrays.asList(user1, user2, user3);

        // 검증된 유저 필터링
        List<User> verifiedUsers = users.stream()
                .filter(User::isVerified)
                .collect(Collectors.toList());
        System.out.println(verifiedUsers);
        /*
        결과 :
        [User{id=101, name='Alice', emailAddress='alice@fastcampus.co.kr', isVerified=true, friendUserIds=null},
         User{id=103, name='Charlie', emailAddress='charlie@fastcampus.co.kr', isVerified=true, friendUserIds=null}]
         */

        // 검증되지 않은 유저 필터링
        List<User> unverifiedUsers = users.stream()
                .filter(user -> !user.isVerified())
                .collect(Collectors.toList());
        System.out.println(unverifiedUsers);
        /*
        결과 :
        [User{id=102, name='Bob', emailAddress='bob@fastcampus.co.kr', isVerified=false, friendUserIds=null}]
         */

        //------------------------------------------------------------------

        Order order1 = new Order()
                .setId(1001)
                .setStatus(OrderStatus.CREATED);
        Order order2 = new Order()
                .setId(1002)
                .setStatus(OrderStatus.ERROR);
        Order order3 = new Order()
                .setId(1003)
                .setStatus(OrderStatus.PROCESSED);
        Order order4 = new Order()
                .setId(1004)
                .setStatus(OrderStatus.ERROR);
        Order order5 = new Order()
                .setId(1005)
                .setStatus(OrderStatus.IN_PROGRESS);

        List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);
        // 오더 상태가 에러인 것만 필터링.
        List<Order> filteredOrders = orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.ERROR)
                .collect(Collectors.toList());
        System.out.println(filteredOrders);
        /*
        결과 :
        [Order{id=1002, createdAt=null, createdByUserId=0, status=ERROR, amount=null, orderLines=null},
         Order{id=1004, createdAt=null, createdByUserId=0, status=ERROR, amount=null, orderLines=null}]
         */
    }
}
