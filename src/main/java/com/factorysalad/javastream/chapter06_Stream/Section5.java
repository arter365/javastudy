package com.factorysalad.javastream.chapter06_Stream;

import com.factorysalad.javastream.chapter06_Stream.model.Order.OrderStatus;
import com.factorysalad.javastream.chapter06_Stream.model.Order;
import com.factorysalad.javastream.chapter06_Stream.model.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
Sorted : 데이터의 정렬
- 데이터가 순서대로 정렬된 stream을 리턴
- 데이터의 종류에 따라 Comparator가 필요할 수 있음.

Stream<T> sorted();
Stream<T> sorted(Comparator<? super T> comparator);
 */
public class Section5 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, -5, 7, 4);

        // 숫자정렬
        List<Integer> sortedNumbers = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(sortedNumbers);
        // 결과 : [-5, 3, 4, 7]

        // ----------------------------------------------

        User user1 = new User()
                .setId(101)
                .setName("Paul")
                .setVerified(true)
                .setEmailAddress("alice@fastcampus.co.kr");
        User user2 = new User()
                .setId(102)
                .setName("David")
                .setVerified(false)
                .setEmailAddress("bob@fastcampus.co.kr");
        User user3 = new User()
                .setId(103)
                .setName("John")
                .setVerified(false)
                .setEmailAddress("charlie@fastcampus.co.kr");
        List<User> users = Arrays.asList(user1, user2, user3);

        // User객체의 이름순으로 리스트 정렬
        List<User> sortedUsers = users.stream()
                // Comparator 전달
                .sorted((u1, u2) -> u1.getName().compareTo(u2.getName()))
                .collect(Collectors.toList());
        System.out.println(sortedUsers);
        /*
        결과 :
        [User{id=102, name='David', emailAddress='bob@fastcampus.co.kr', isVerified=false, friendUserIds=null},
        User{id=103, name='John', emailAddress='charlie@fastcampus.co.kr', isVerified=false, friendUserIds=null},
        User{id=101, name='Paul', emailAddress='alice@fastcampus.co.kr', isVerified=true, friendUserIds=null}]
         */

        // ----------------------------------------------

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        Order order1 = new Order()
                .setId(1001)
                .setStatus(OrderStatus.CREATED)
                .setCreatedByUserId(101)
                .setCreatedAt(now.minusHours(4));
        Order order2 = new Order()
                .setId(1002)
                .setStatus(OrderStatus.ERROR)
                .setCreatedByUserId(103)
                .setCreatedAt(now.minusHours(1));
        Order order3 = new Order()
                .setId(1003)
                .setStatus(OrderStatus.PROCESSED)
                .setCreatedByUserId(102)
                .setCreatedAt(now.minusHours(36));
        Order order4 = new Order()
                .setId(1004)
                .setStatus(OrderStatus.ERROR)
                .setCreatedByUserId(104)
                .setCreatedAt(now.minusHours(40));
        Order order5 = new Order()
                .setId(1005)
                .setStatus(OrderStatus.IN_PROGRESS)
                .setCreatedByUserId(101)
                .setCreatedAt(now.minusHours(10));
        List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);

        // TODO: sort the orders based on createdAt
        List<Order> sortedOrders = orders.stream()
                .sorted((u1, u2) -> u1.getCreatedAt().compareTo(u2.getCreatedAt()))
                .collect(Collectors.toList());
        System.out.println(sortedOrders);
        /*
        결과 :
        [Order{id=1004, createdAt=2022-04-10T23:52:56.185354100, createdByUserId=104, status=ERROR, amount=null, orderLines=null},
        Order{id=1003, createdAt=2022-04-11T03:52:56.185354100, createdByUserId=102, status=PROCESSED, amount=null, orderLines=null},
        Order{id=1005, createdAt=2022-04-12T05:52:56.185354100, createdByUserId=101, status=IN_PROGRESS, amount=null, orderLines=null},
        Order{id=1001, createdAt=2022-04-12T11:52:56.185354100, createdByUserId=101, status=CREATED, amount=null, orderLines=null},
        Order{id=1002, createdAt=2022-04-12T14:52:56.185354100, createdByUserId=103, status=ERROR, amount=null, orderLines=null}]
         */
    }
}
