package com.factorysalad.javastream.chapter10_Functional_DesignPattern.service;

import com.factorysalad.javastream.chapter10_Functional_DesignPattern.model.Price;

@FunctionalInterface
public interface PriceProcessor {
    Price process(Price price);

    // 디펄트로 andThen을 만든다.
    // 인자로 자기 다음에 실행될 PriceProcessor를 받아온다.
    // 리턴될 때 새로운 PriceProcessor를 리턴해 준다. 새로운 PriceProcessor은 process 메서드 하나뿐이다.
    // 1. 먼저 자신의 process부터 처리한다.
    // 2. 인자로 들어온 process를 호출한다.
    default PriceProcessor andThen(PriceProcessor next) {
        return price -> next.process(process(price));
    }
}
