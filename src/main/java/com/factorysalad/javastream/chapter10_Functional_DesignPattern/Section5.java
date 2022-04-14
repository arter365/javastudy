package com.factorysalad.javastream.chapter10_Functional_DesignPattern;

import com.factorysalad.javastream.chapter10_Functional_DesignPattern.model.Order.OrderStatus;
import com.factorysalad.javastream.chapter10_Functional_DesignPattern.model.Order;
import com.factorysalad.javastream.chapter10_Functional_DesignPattern.model.OrderLine;
import com.factorysalad.javastream.chapter10_Functional_DesignPattern.service.OrderProcessStep;

import java.math.BigDecimal;
import java.util.Arrays;

/*
[행동패턴]
Chain of Responsibility Pattern : 책임 연쇄 패턴

- 행동 패턴의 하나
- 명령과 명령을 각각의 방법으로 처리할 수 있는 처리 객체들이 있을 때,
    - 처리 객체들을 체인으로 엮는다.
    - 명령을 처리 객체들이 체인의 앞에서부터 하나씩 처리해 보도록 한다.
    - 각 처리 객체는 자신이 처리할 수 없을 때 체인의 다음 처리 객체로 명령을 넘긴다.
    - 체인의 끝에 다다르면 처리가 끝난다.
- 새로운 처리 객체를 추가하는 것으로 매우 간단히 처리 방법을 더할 수 있다.
 */
public class Section5 {
    public static void main(String[] args) {
        // 각 스텝을 만들어 본다-------------------------------------------------------------------

        // 스탭1) 오더 스탭을 만들면서 컨슈머를 만들어 넘겨준다.
        OrderProcessStep initializeStep = new OrderProcessStep(order -> {
            // 오더가 CREATED 상태일 때 실행한다.
            if (order.getStatus() == OrderStatus.CREATED) {
                System.out.println("Start processing order " + order.getId());
                // 오더의 상태를 IN_PROGRESS로 바꿔준다.
                order.setStatus(OrderStatus.IN_PROGRESS);
            }
        });
        // 스탭2)
        OrderProcessStep setOrderAmountStep = new OrderProcessStep(order -> {
            // 오더가 IN_PROGRESS 상태일 때 Order의 amount를 셋팅해 준다.
            if (order.getStatus() == OrderStatus.IN_PROGRESS) {
                System.out.println("Setting amount of order " + order.getId());
                // 오더라인의 총합을 구한다.
                order.setAmount(order.getOrderLines().stream()
                        .map(OrderLine::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add));
            }
        });
        // 스탭3)
        OrderProcessStep verifyOrderStep = new OrderProcessStep(order -> {
            // 오더가 IN_PROGRESS 상태일 때 Order가 정상인지 확인하는 단계
            if (order.getStatus() == OrderStatus.IN_PROGRESS) {
                System.out.println("Verifying order " + order.getId());
                // Order의 amount가 0 이하이면 ERROR 상태로 만든다.
                if (order.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
                    order.setStatus(OrderStatus.ERROR);
                }
            }
        });
        // 스탭4)
        OrderProcessStep processPaymentStep = new OrderProcessStep(order -> {
            // 오더가 정상이면 패이먼트 처리를 한다.
            if (order.getStatus() == OrderStatus.IN_PROGRESS) {
                System.out.println("Processing payment of order " + order.getId());
                // 은행이나 카드사를 통해 페이먼트 처리하는 과정을 거친다.
                // 완료 후 상태를 PROCESSED로 바꾼다.
                order.setStatus(OrderStatus.PROCESSED);
            }
        });

        // 스탭5)
        OrderProcessStep handleErrorStep = new OrderProcessStep(order -> {
            // 에러 상태일 때 핸들링 한다.
            if (order.getStatus() == OrderStatus.ERROR) {
                System.out.println("Sending out 'Failed to process order' alert for order " + order.getId());
            }
        });

        // 스탭6)
        OrderProcessStep completeProcessingOrderStep = new OrderProcessStep(order -> {
            // 오더가 PROCESSED 상태일 때 이메일을 보내거나 하는 완료 작업을 할 수 있다.
            if(order.getStatus() == OrderStatus.PROCESSED) {
                System.out.println("Finished processing order " + order.getId());
            }
        });

        // 위의 스탭들을 엮어서(chain) 워크플로우를 만든다.
        // ***** 자기가 처리 할 일이 아니면 다음으로 넘긴다. *****
        OrderProcessStep chainedOrderProcessSteps = initializeStep
                .setNext(setOrderAmountStep)
                .setNext(verifyOrderStep)
                .setNext(processPaymentStep)
                .setNext(handleErrorStep)
                .setNext(completeProcessingOrderStep);

        // ------------------------------------------------------------------

        Order order = new Order()
                .setId(1001L)
                .setStatus(OrderStatus.CREATED)
                .setOrderLines(Arrays.asList(
                        new OrderLine().setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine().setAmount(BigDecimal.valueOf(2000))));
        chainedOrderProcessSteps.process(order);
        /*
        결과 :
        Start processing order 1001
        Setting amount of order 1001
        Verifying order 1001
        Processing payment of order 1001
        Finished processing order 1001
         */

        Order failingOrder = new Order()
                .setId(1002L)
                .setStatus(OrderStatus.CREATED)
                .setOrderLines(Arrays.asList(
                        new OrderLine().setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine().setAmount(BigDecimal.valueOf(-2000))));
        chainedOrderProcessSteps.process(failingOrder);
        /*
        결과 :
        Start processing order 1002
        Setting amount of order 1002
        Verifying order 1002
        Sending out 'Failed to process order' alert for order 1002
         */
    }
}
