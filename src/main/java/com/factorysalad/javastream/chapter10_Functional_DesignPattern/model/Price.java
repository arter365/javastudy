package com.factorysalad.javastream.chapter10_Functional_DesignPattern.model;

public class Price {
    // 테스트를 위해 string으로 price를 만들어서 어떻게 변화해 가는지 확인한다.
    private final String price;

    public Price(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }
}
