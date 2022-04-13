package com.factorysalad.javastudy.L_Lambda;

/*
1. 함수형 인터페이스 : 추상메서드가 하나만 있으면 함수형 인터페이스이다. (스테틱, 디펄트 메서드는 상관없다.)
2. 순수함수이어야 한다. (입력받은 값이 동일한 경우 값이 같아야 한다.)
 */

// 아래의 어노테이션을 붙이면 추상메서드가 하나 이상일 때 에러를 만들어준다.
@FunctionalInterface
public interface RunSomething {
    int doIt(int number);

    // 스테틱 메서드
    static void printStatic() {
        System.out.println("인터페이스에 static 메서드 선언가능");
    }

    // 디펄트 메서드 (반드시 동작한다는 보장이 없기 때문에 문서화를 잘해야 한다.)
    default void pintDefault() {
            System.out.println("인터페이스에 default 메서드 선언가능");
    }
}
