package com.factorysalad.javastream.chapter09_Functional_Uses.priceprocessor;

import com.factorysalad.javastream.chapter09_Functional_Uses.model.Order;
import com.factorysalad.javastream.chapter09_Functional_Uses.model.OrderLine;

import java.math.BigDecimal;
import java.util.function.Function;

/*
오더라인의 값을 합쳐준다.
오더를 받아 오더를 리턴하는 함수를 구현했다.
 */
public class OrderLineAggregationPriceProcessor implements Function<Order, Order> {
    @Override
    public Order apply(Order order) {
        return order.setAmount(order.getOrderLines().stream()
                .map(OrderLine::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }
}
