package com.factorysalad.javastream.chapter08_Advanced_Stream;

import com.factorysalad.javastream.chapter08_Advanced_Stream.model.Order;
import com.factorysalad.javastream.chapter08_Advanced_Stream.model.OrderLine;
import com.factorysalad.javastream.chapter08_Advanced_Stream.model.User;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/*
Reduce

- 주어진 함수를 반복 적용해 Stream 안의 데이터를 하나의 값으로 합치는 작업
- Max / Min / Count도 사실 reduce의 일종이다.
- 병렬 컴퓨팅의 맵리듀스 중 리듀스가 이 방식이다. 여러 컴퓨터에서 맵핑 작업을 하고 그 결과를 리듀스를 통해 하나의 결과물로 합쳐주는 것이다.
 */
public class Section04 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 4, -2, -5, 3);

        // 합계
        int sum = numbers.stream()
                // 리듀스에 바이너리 오퍼레이터를 넘긴다.
                .reduce((x, y) -> x + y)
                .get();
        System.out.println(sum);
        // 결과 : 1

        // 최소
        int min = numbers.stream()
                // x가 크면 x 아니면 y를 리턴하는 삼항연산자
                .reduce((x, y) -> x > y ? x : y)
                .get();
        System.out.println(min);
        // 결과 : 4

        // 곱하기
        int product = numbers.stream()
                .reduce(1, (x, y) -> x * y);
        System.out.println(product);
        // 결과 : 120

        // 문자형 숫자의 합을 구하기
        List<String> numberStrList = Arrays.asList("3", "2", "5", "-4");
        int sumOfNumberStrList = numberStrList.stream()
                .map(Integer::parseInt)
                .reduce(0, (x, y) -> x + y);
        System.out.println(sumOfNumberStrList);
        // 결과 : 6

        // 문자형 숫자의 합을 구하기 (맵과 리듀스를 합해서 사용)
        // 일반적으로는 위왁 같이 맵과 리듀스를 따로 사용한다. 이 예제는 참고만 한다.
        int sumOfNumberStrList2 = numberStrList.stream()
                .reduce(0, (number, str) -> number + Integer.parseInt(str), (num1, num2) -> num1 + num2);
        System.out.println(sumOfNumberStrList2);
        // 결과 : 6

        // -------------------------------------------------------------

        User user1 = new User()
                .setId(101)
                .setName("Alice")
                .setFriendUserIds(Arrays.asList(201, 202, 203, 204));
        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setFriendUserIds(Arrays.asList(204, 205, 206));
        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setFriendUserIds(Arrays.asList(204, 205, 207));
        List<User> users = Arrays.asList(user1, user2, user3);

        // -------------------------------------------------------------

        // 친구 수의 총합
        int sumOfNumberOfFriends = users.stream()
                // 유저의 친구 리스트를 얻고
                .map(User::getFriendUserIds)
                // 리스트의 사이즈를 얻고
                .map(List::size)
                // 그 총 합을 구함
                .reduce(0,  (x, y) -> x + y);
        System.out.println(sumOfNumberOfFriends);
        // 결과 : 10

        // -------------------------------------------------------------

        Order order1 = new Order()
                .setId(1001L)
                .setOrderLines(Arrays.asList(
                        new OrderLine().setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine().setAmount(BigDecimal.valueOf(2000))));
        Order order2 = new Order()
                .setId(1002L)
                .setOrderLines(Arrays.asList(
                        new OrderLine().setAmount(BigDecimal.valueOf(2000)),
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(3000))));
        Order order3 = new Order()
                .setId(1002L)
                .setOrderLines(Arrays.asList(
                        new OrderLine().setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine().setAmount(BigDecimal.valueOf(2000))));
        List<Order> orders = Arrays.asList(order1, order2, order3);

        // TODO: find the sum of amounts

        BigDecimal sumOfAmouts = orders.stream()
                .map(Order::getOrderLines)  // stream<List<OrderLine>>
                .flatMap(List::stream)      // stream<OrderLine>
                .map(OrderLine::getAmount)  // stream<BigDecimal>
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(sumOfAmouts);
        // 결과 : 11000
    }
}
