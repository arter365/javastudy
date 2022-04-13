package com.factorysalad.javastream.chapter06_Stream;

import com.factorysalad.javastream.chapter06_Stream.model.Order.OrderStatus;
import com.factorysalad.javastream.chapter06_Stream.model.Order;
import com.factorysalad.javastream.chapter06_Stream.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Map : 데이터의 변형
- 데이터를 변형하는데 사용
- 데이터에 해당 함수가 적용된 결과물을 제공하는 stream을 리턴

<R> Stream<R> map(Function<? super T, ? extends R> mapper);
 */
public class Section3 {
    public static void main(String[] args) {
        List<Integer> numberList = Arrays.asList(3, 6, -4);

        // 숫자의 값을 두배 해주는 예제
        List<Integer> numberListX2 = numberList.stream()
                .map(x -> x * 2)
                .collect(Collectors.toList());
        System.out.println(numberListX2);
        // 결과 : [6, 12, -8]

        // 숫자와 문자를 합해주는 예제
        Stream<Integer> numberListStream = numberList.stream();
        Stream<String> strStream = numberListStream.map(x -> "Number is " + x);
        List<String> strList = strStream.collect(Collectors.toList());
        System.out.println(strList);
        // 결과 : [Number is 3, Number is 6, Number is -4]

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

        List<String> emailAddresses = users.stream()
                // 이메일 주소만 흐르는 새로운 스트림이 만들어진다.
                .map(User::getEmailAddress)
                .collect(Collectors.toList());
        System.out.println(emailAddresses);
        // 결과 : [alice@fastcampus.co.kr, bob@fastcampus.co.kr, charlie@fastcampus.co.kr]

        //-------------------------------------------------------

        Order order1 = new Order()
                .setId(1001)
                .setStatus(OrderStatus.CREATED)
                .setCreatedByUserId(101);
        Order order2 = new Order()
                .setId(1002)
                .setStatus(OrderStatus.ERROR)
                .setCreatedByUserId(103);
        Order order3 = new Order()
                .setId(1003)
                .setStatus(OrderStatus.PROCESSED)
                .setCreatedByUserId(102);
        Order order4 = new Order()
                .setId(1004)
                .setStatus(OrderStatus.ERROR)
                .setCreatedByUserId(104);
        Order order5 = new Order()
                .setId(1005)
                .setStatus(OrderStatus.IN_PROGRESS)
                .setCreatedByUserId(101);
        List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);

        // TODO: Create list of createdByUserId
        List<Long> createdByUserIds = orders.stream()
                // Id가 있는 새로운 스트림이 흐른다.
                .map(Order::getCreatedByUserId)
                .collect(Collectors.toList());
        System.out.println(createdByUserIds);
        // 결과 : [101, 103, 102, 104, 101]
    }
}
