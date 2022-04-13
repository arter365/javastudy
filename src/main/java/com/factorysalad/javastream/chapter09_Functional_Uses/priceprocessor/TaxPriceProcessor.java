package com.factorysalad.javastream.chapter09_Functional_Uses.priceprocessor;

import com.factorysalad.javastream.chapter09_Functional_Uses.model.Order;

import java.math.BigDecimal;
import java.util.function.Function;

/*
세율을 감안해서 현재의 오더에 세율을 적용해 주는 함수.
 */
public class TaxPriceProcessor implements Function<Order, Order> {

    private final BigDecimal taxRate;

    public TaxPriceProcessor(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public Order apply(Order order) {
        return order.setAmount(order.getAmount()
                // 오더의 가격을 가져와서(order.getAmount()) 세율을 곱해준다
                // (.multiply) 하지만 퍼센트이기 때문에 100으로 나눈다. 곱해주려면 1을 더해준다. 예) 1.098
                .multiply(taxRate.divide(new BigDecimal(100)).add(BigDecimal.ONE)));
    }

}