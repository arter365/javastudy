package com.factorysalad.javastream.chapter08_Advanced_Stream;

import com.factorysalad.javastream.chapter08_Advanced_Stream.model.Order.OrderStatus;
import com.factorysalad.javastream.chapter08_Advanced_Stream.model.Order;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/*
collect 알아보기
 */
public class Section07 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(13, 2, 101, 203, 304, 402, 305, 349, 2312, 203);

        // --------------------------------------------------------------------

        Map<Integer, List<Integer>> unitDigitMap = numbers.stream()
                .collect(Collectors.groupingBy(number -> number % 10));
        System.out.println(unitDigitMap);
        // 결과 : {1=[101], 2=[2, 402, 2312], 3=[13, 203, 203], 4=[304], 5=[305], 9=[349]}

        // 리스트 대신 set으로 반환 (중복값이 제거된다)
        Map<Integer, Set<Integer>> unitDigitSet = numbers.stream()
                .collect(Collectors.groupingBy(number -> number % 10, Collectors.toSet()));
        System.out.println(unitDigitSet);
        // 결과 : {1=[101], 2=[2, 402, 2312], 3=[203, 13], 4=[304], 5=[305], 9=[349]}

        Map<Integer, List<String>> unitDigitStrMap = numbers.stream()
                .collect(Collectors.groupingBy(number -> number % 10,
                        Collectors.mapping(number -> "unit digit is " + number, Collectors.toList())));
        System.out.println(unitDigitStrMap.get(3));
        // 결과 : [unit digit is 13, unit digit is 203, unit digit is 203]

        // -----------------------------------------------------------------

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

        // TODO: 상태별로 묶은 Order
        Map<OrderStatus, List<Order>> orderStatusMap = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus));
        System.out.println(orderStatusMap);
        /*
        결과 :
        {ERROR=[Order{id=1002, createdAt=null, createdByUserId=0, status=ERROR, amount=4000, orderLines=null},
                Order{id=1003, createdAt=null, createdByUserId=0, status=ERROR, amount=3000, orderLines=null}],
         CREATED=[Order{id=1001, createdAt=null, createdByUserId=0, status=CREATED, amount=2000, orderLines=null}],
         PROCESSED=[Order{id=1004, createdAt=null, createdByUserId=0, status=PROCESSED, amount=7000, orderLines=null}]}
         */

        // 오더의 각 상태별 합계
        Map<OrderStatus, BigDecimal> orderStatusToSumOfAmountMap = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus,
                        // 그룹핑된 결과물에 맵핑을 한 후 그 빅데시멀 결과물을 리듀싱한다.
                        Collectors.mapping(Order::getAmount,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
        System.out.println(orderStatusToSumOfAmountMap);
        // 결과 : {ERROR=7000, CREATED=2000, PROCESSED=7000}
    }
}
