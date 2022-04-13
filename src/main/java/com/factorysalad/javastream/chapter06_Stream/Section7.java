package com.factorysalad.javastream.chapter06_Stream;

import com.factorysalad.javastream.chapter06_Stream.model.Order;
import com.factorysalad.javastream.chapter06_Stream.model.OrderLine;
import com.factorysalad.javastream.chapter06_Stream.model.OrderLine.OrderLineType;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
FlatMap : 스트림의 스트림을 납작하게

- Map + Flatten
- 데이터에 함수를 적용한 후 중첩된 stream을 연결하여 하나의 stream으로 리턴

<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
 */
public class Section7 {
    public static void main(String[] args) {
        String[][] cities = new String[][] {
                { "Seoul", "Busan" },
                { "San Francisco", "New York" },
                { "Madrid", "Barcelona" }
        };

        // map을 사용한 경우
        Stream<String[]> cityStream = Arrays.stream(cities);
        Stream<Stream<String>> cityStreamStream = cityStream.map(x -> Arrays.stream(x));
        // stream이 감싸고 있어서 사용 할 수 없다.
        List<Stream<String>> cityStreamList = cityStreamStream.collect(Collectors.toList());

        // flatMap을 사용한 경우
        Stream<String[]> cityStream2 = Arrays.stream(cities);   // 위에서 만든 스트림은 이미 다 흘려보내서 다시 사용할 수 없다.
        Stream<String> flattenedCityStream = cityStream2.flatMap(x -> Arrays.stream(x));
        List<String> flattenedCityList = flattenedCityStream.collect(Collectors.toList());
        System.out.println("flatMap을 사용한 경우 : " + flattenedCityList);
        /*
        결과 : 도시들이 하나의 리스트로 합해져 있는 것을 확인 할 수 있다.
        [Seoul, Busan, San Francisco, New York, Madrid, Barcelona]
         */

        Order order1 = new Order()
                .setId(1001)
                .setOrderLines(Arrays.asList(
                        new OrderLine()
                                .setId(10001)
                                .setType(OrderLineType.PURCHASE)
                                .setAmount(BigDecimal.valueOf(5000)),
                        new OrderLine()
                                .setId(10002)
                                .setType(OrderLineType.PURCHASE)
                                .setAmount(BigDecimal.valueOf(4000))
                ));
        Order order2 = new Order()
                .setId(1002)
                .setOrderLines(Arrays.asList(
                        new OrderLine()
                                .setId(10003)
                                .setType(OrderLineType.PURCHASE)
                                .setAmount(BigDecimal.valueOf(2000)),
                        new OrderLine()
                                .setId(10004)
                                .setType(OrderLineType.DISCOUNT)
                                .setAmount(BigDecimal.valueOf(-1000))
                ));
        Order order3 = new Order()
                .setId(1003)
                .setOrderLines(Arrays.asList(
                        new OrderLine()
                                .setId(10005)
                                .setType(OrderLineType.PURCHASE)
                                .setAmount(BigDecimal.valueOf(2000))
                ));
        List<Order> orders = Arrays.asList(order1, order2, order3);

        // ------------------------------------------------------------------

        List<OrderLine> mergedOrderLines = orders.stream() 	// Stream<Order>
                .map(Order::getOrderLines)					// Stream<List<OrderLine>>
                // .map(List::stream)                       // 이렇게 사용하면 Stream<Stream<OrderLine>>
                .flatMap(List::stream) 						// Stream<OrderLine>
                .collect(Collectors.toList());              // List<OrderLine>
        System.out.println(mergedOrderLines);
        /*
        결과 :
        [
        OrderLine{id=10001, type=PURCHASE, productId=0, quantity=0, amount=5000},
        OrderLine{id=10002, type=PURCHASE, productId=0, quantity=0, amount=4000},
        OrderLine{id=10003, type=PURCHASE, productId=0, quantity=0, amount=2000},
        OrderLine{id=10004, type=DISCOUNT, productId=0, quantity=0, amount=-1000},
        OrderLine{id=10005, type=PURCHASE, productId=0, quantity=0, amount=2000}
        ]
         */

    }
}
