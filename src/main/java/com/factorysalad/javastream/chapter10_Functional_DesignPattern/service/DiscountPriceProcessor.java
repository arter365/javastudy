package com.factorysalad.javastream.chapter10_Functional_DesignPattern.service;

import com.factorysalad.javastream.chapter10_Functional_DesignPattern.model.Price;

public class DiscountPriceProcessor implements PriceProcessor {

    @Override
    public Price process(Price price) {
        return new Price(price.getPrice() + ", then applied discount");
    }

}
