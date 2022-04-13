package com.factorysalad.javastream.chapter09_Functional_Uses;

import com.factorysalad.javastream.chapter09_Functional_Uses.model.Order;
import com.factorysalad.javastream.chapter09_Functional_Uses.model.OrderLine;
import com.factorysalad.javastream.chapter09_Functional_Uses.priceprocessor.OrderLineAggregationPriceProcessor;
import com.factorysalad.javastream.chapter09_Functional_Uses.priceprocessor.TaxPriceProcessor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/*
Function Composition : 함수 합성
- 여러 개의 함수를 합쳐 하나의 새로운 함수로 만드는 것.
 */
public class Section3 {
    public static void main(String[] args) {
        Function<Integer, Integer> multiplyByTwo = x -> 2 * x;
        Function<Integer, Integer> addTen = x -> x + 10;

        // 읽는 순서와 같기 때문에 andThen을 많이 사용한다.
        Function<Integer, Integer> composedFunction = multiplyByTwo.andThen(addTen);
        System.out.println(composedFunction.apply(3));
        /*
        결과 : 3 * 2 = 6 , 6 + 10 = 16
        16
         */

        // ---------------------------------------------------------------------------------

        /*
        작은 함수를 합성해서 여러가지 함수를 만들 수 있다.
        오더를 받아서 오더의 가격을 프로세스 해주는 함수를 만들어 본다.
        가격을 프로세스 해주는 함수는 여러개가 있을 수 있다.
        - 오더를 받아서 오더라인의 가격을 합하여 최종 가격을 오더에 셋팅해주는 프로세스.
        - 오더를 받아서 텍스에 맞게 오더의 가격을 조정해주는 프로세스.
        원하는 프로세스만 오더에 적용 할 수 있다.
         */
        Order unprocessedOrder = new Order()
                .setId(1001L)
                .setOrderLines(Arrays.asList(
                        new OrderLine().setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine().setAmount(BigDecimal.valueOf(2000))));

        // ---------------------------------------------------------------------------------

        // 함수가 두개 있는 리스트
        List<Function<Order, Order>> priceProcessors = getPriceProcessors(unprocessedOrder);

        // 두가지 함수가 있는 Composition 함수 mergedPriceProcessors를 만든다.
        Function<Order, Order> mergedPriceProcessors = priceProcessors.stream()
                // .reduce(Function.identity(), (priceProcessor1, proceProcessor2) -> priceProcessor1.andThen(priceProcessor2))를 아래와 같이 적을 수 있다.
                .reduce(Function.identity(), Function::andThen);

        // 위의 Composition 함수를 오더에 적용 시킬 수 있다.
        Order processedOrder = mergedPriceProcessors.apply(unprocessedOrder);
        System.out.println(processedOrder);
        /*
        결과 :
        Order{id=1001, createdAt=null, createdByUserId=0, status=null,
              amount=3281.25000, orderLines=[OrderLine{id=0, type=null,
              productId=0, quantity=0, amount=1000},
        OrderLine{id=0, type=null, productId=0, quantity=0, amount=2000}]}
         */
    }

    // 헬퍼함수
    public static List<Function<Order, Order>> getPriceProcessors(Order order) {
        return Arrays.asList(new OrderLineAggregationPriceProcessor(),
                new TaxPriceProcessor(new BigDecimal("9.375")));
    }
}
