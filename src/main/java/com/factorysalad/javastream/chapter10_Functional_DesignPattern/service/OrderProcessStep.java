package com.factorysalad.javastream.chapter10_Functional_DesignPattern.service;

import com.factorysalad.javastream.chapter10_Functional_DesignPattern.model.Order;

import java.util.Optional;
import java.util.function.Consumer;

/*
오더를 프로세스 하는 각 단계를 나타내는 OrderProcessStep 클래스
링크드 리스트와 비슷한 방식으로 동작한다.
 */
public class OrderProcessStep {
    // 오더를 프로세스 하는 consumer를 가진다.
    private final Consumer<Order> processOrder;
    // 자식과 같은 OrderProcessStep 필드 next
    private OrderProcessStep next;

    // 생성자를 통해 consumer를 받는다.
    public OrderProcessStep(Consumer<Order> processOrder) {
        this.processOrder = processOrder;
    }

    // 다음 단계를 지정해 주는 함수. 체인의 맨 마지막에 새로들어온 프로세스를 추가한다.
    public OrderProcessStep setNext(OrderProcessStep next) {
        // 현재 next가 없다면 여기에 넣는다.
        if (this.next == null) {
            this.next = next;
        } else {
            // 아니면 다음 프로세스가 비어있는지 확인하고 넣는 과정을 반복한다. (링크드 리스트의 끝에 넣는 과정과 비슷하다)
            this.next.setNext(next);
        }
        return this;
    }

    // 오더를 받아서 실행해 주고 만약 다음 스탭이 있으면 그 프로세스를 실행 해준다.
    public void process(Order order) {
        processOrder.accept(order);
        Optional.ofNullable(next)
                .ifPresent(nextStep -> nextStep.process(order));
    }
}
