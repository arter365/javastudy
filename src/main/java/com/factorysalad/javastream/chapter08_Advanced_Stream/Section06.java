package com.factorysalad.javastream.chapter08_Advanced_Stream;

import com.factorysalad.javastream.chapter08_Advanced_Stream.model.Order.OrderStatus;
import com.factorysalad.javastream.chapter08_Advanced_Stream.model.Order;
import com.factorysalad.javastream.chapter08_Advanced_Stream.model.User;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
collect 알아보기

public static <T, K, U> Collector<T, ?, Map<K, U>> toMap(
    Function<? super T, ? extends K> keyMapper,
    Function<? super T, ? extends U> valueMapper
)
ToMap : 많이 사용하는 collector이다.
        stream 안의 데이터를 맵의 형태로 반환한다.
        map은 키와 벨류로 이루어진 데이터 스트럭쳐이다.
        키를 통해 값에 빠르게 접근 할 수 있어서 List와 함께 가장 많이 사용되는 데이터 스트럭쳐이다.

 */
public class Section06 {
    public static void main(String[] args) {
        // 숫자 값을 키와 값으로 사용한 맵
        Map<Integer, String> numberMap1 = Stream.of(3, 5, -4, 2, 6)
                .collect(Collectors.toMap(x -> x, x -> "Number is " + x));
        System.out.println("numberMap1 : " + numberMap1);
        // 결과 : {2=Number is 2, 3=Number is 3, -4=Number is -4, 5=Number is 5, 6=Number is 6}

        Map<Integer, String> numberMap2 = Stream.of(3, 5, -4, 2, 6)
                // Function.identity()는 위의 x -> x와 같은 내용이다.
                .collect(Collectors.toMap(Function.identity(), x -> "Number is " + x));
        System.out.println("numberMap2 : " + numberMap2.get(3));
        // 결과 : Number is 3

        // --------------------------------------------------------------

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

        // --------------------------------------------------------------

        // ***** Map을 가장 자주 사용하는 방법이 Object List가 왔을 때 id를 사용하여 Map으로 만드는 것이다. *****
        Map<Integer, User> userIdToUserMap = users.stream()
                // 아래와 같이 id와 object 값으로 맵이 만들어 진다.
                .collect(Collectors.toMap(User::getId, Function.identity()));
        System.out.println(userIdToUserMap);
        // 결과 :
        // {101=User{id=101, name='Alice', emailAddress='alice@fastcampus.co.kr', isVerified=true, createdAt=null, friendUserIds=null},
        //  102=User{id=102, name='Bob', emailAddress='bob@fastcampus.co.kr', isVerified=false, createdAt=null, friendUserIds=null},
        //  103=User{id=103, name='Charlie', emailAddress='charlie@fastcampus.co.kr', isVerified=false, createdAt=null, friendUserIds=null}}
        System.out.println(userIdToUserMap.get(103));
        // 결과 :
        // User{id=103, name='Charlie', emailAddress='charlie@fastcampus.co.kr', isVerified=false, createdAt=null, friendUserIds=null}

        // --------------------------------------------------------------

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

        // --------------------------------------------------------------

        // TODO: Create a map from order id to order status
        Map<Long, OrderStatus> orderIdToOrderStatusMap = orders.stream()
                .collect(Collectors.toMap(Order::getId, Order::getStatus));
        System.out.println(orderIdToOrderStatusMap);
        // 결과 : {1001=CREATED, 1002=ERROR, 1003=ERROR, 1004=PROCESSED}
        System.out.println(orderIdToOrderStatusMap.get(1003L));
        // 결과 : ERROR

    }
}
