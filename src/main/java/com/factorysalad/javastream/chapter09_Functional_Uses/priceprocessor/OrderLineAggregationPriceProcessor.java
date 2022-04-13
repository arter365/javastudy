package com.factorysalad.javastream.chapter09_Functional_Uses.priceprocessor;

import com.factorysalad.javastream.chapter09_Functional_Uses.model.Order;
import com.factorysalad.javastream.chapter09_Functional_Uses.model.OrderLine;

import java.math.BigDecimal;
import java.util.function.Function;

public class OrderLineAggregationPriceProcessor implements Function<Order, Order> {
    @Override
    public Order apply(Order order) {
        return order.setAmount(order.getOrderLines().stream()
                .map(OrderLine::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }
}
