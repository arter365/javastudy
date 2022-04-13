package com.factorysalad.javastream.chapter10_Functional_DesignPattern;

import com.factorysalad.javastream.chapter10_Functional_DesignPattern.model.Price;
import com.factorysalad.javastream.chapter10_Functional_DesignPattern.service.BasicPriceProcessor;
import com.factorysalad.javastream.chapter10_Functional_DesignPattern.service.DiscountPriceProcessor;
import com.factorysalad.javastream.chapter10_Functional_DesignPattern.service.PriceProcessor;
import com.factorysalad.javastream.chapter10_Functional_DesignPattern.service.TaxPriceProcessor;

/*
[구조패턴]
Decorator Pattern : 데코레이터 패턴

- 구조 패턴의 하나
- 용도에 따라 객체에 기능을 계속 추가(decorate) 할 수 있게 해준다.
 */
public class Section2 {
    public static void main(String[] args) {
        // 프라이스 생성
        Price unprocessedPrice = new Price("Original Price");

        // 프로세스 생성
        PriceProcessor basicPriceProcessor = new BasicPriceProcessor();
        PriceProcessor discountPriceProcessor = new DiscountPriceProcessor();
        PriceProcessor taxPriceProcessor = new TaxPriceProcessor();

        // 3개의 프로세스를 andThen으로 조합 (데코레이트 패턴)
        PriceProcessor decoratedPriceProcessor = basicPriceProcessor
                .andThen(discountPriceProcessor)
                .andThen(taxPriceProcessor);

        // 프라이스 처리
        Price processedPrice = decoratedPriceProcessor.process(unprocessedPrice);
        System.out.println(processedPrice.getPrice());
        /*
        결과 :
        Original Price, then applied discount, then applied tax
         */

        // --------------------------------------------------------------------------------

        // 람다를 통해서 클래스를 만들지 않고 프로세스 추가 하기
        PriceProcessor decoratedPriceProcessor2 = basicPriceProcessor
                .andThen(taxPriceProcessor)
                .andThen(price -> new Price(price.getPrice() + ", then apply another procedure"));
        // 프로세스 처리
        Price processedPrice2 = decoratedPriceProcessor2.process(unprocessedPrice);
        System.out.println(processedPrice2.getPrice());
        /*
        결과 : 클래스로 만들어 놓으면 재사용이 가능하지만 람다를 통해서 만들면 그 스코프 안에서만 사용이 가능하다.
        Original Price, then applied tax, then apply another procedure
         */
    }
}
