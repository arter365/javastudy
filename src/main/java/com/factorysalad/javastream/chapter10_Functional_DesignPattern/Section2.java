package com.factorysalad.javastream.chapter10_Functional_DesignPattern;

import com.factorysalad.javastream.chapter10_Functional_DesignPattern.model.Price;
import com.factorysalad.javastream.chapter10_Functional_DesignPattern.service.BasicPriceProcessor;
import com.factorysalad.javastream.chapter10_Functional_DesignPattern.service.DiscountPriceProcessor;
import com.factorysalad.javastream.chapter10_Functional_DesignPattern.service.PriceProcessor;
import com.factorysalad.javastream.chapter10_Functional_DesignPattern.service.TaxPriceProcessor;

public class Section2 {
    public static void main(String[] args) {
        Price unprocessedPrice = new Price("Original Price");

        PriceProcessor basicPriceProcessor = new BasicPriceProcessor();
        PriceProcessor discountPriceProcessor = new DiscountPriceProcessor();
        PriceProcessor taxPriceProcessor = new TaxPriceProcessor();

        PriceProcessor decoratedPriceProcessor = basicPriceProcessor
                .andThen(discountPriceProcessor)
                .andThen(taxPriceProcessor);
        Price processedPrice = decoratedPriceProcessor.process(unprocessedPrice);
        System.out.println(processedPrice.getPrice());

        PriceProcessor decoratedPriceProcessor2 = basicPriceProcessor
                .andThen(taxPriceProcessor)
                .andThen(price -> new Price(price.getPrice() + ", then apply another procedure"));
        Price processedPrice2 = decoratedPriceProcessor2.process(unprocessedPrice);
        System.out.println(processedPrice2.getPrice());
    }
}
