package com.factorysalad.javastudy.K_Annotation;

import java.util.ArrayList;

/*
애너테이션
- 주석처럼 프로그래밍 언어에 영향을 미치지 않으며, "유용한 정보를 특정 프로그램에 제공" (예 : @Test = Junit에 정보제공)
- 자바 주석 : 프로그램과 주석의 내용을 함께 작성하는 방식
- 애너테이션 : 자바 주석과 같이 프로그램과 설정파일(XML)을 함께 작성하는 방식 (기존의 문법을 바꾸지 않아도 된다. 애너테이션으로 정보제공)

// 애너테이션은 인터페이스이다.
public interface Annotation {
    ...
}

 */
public class TestMain {
    public static void main(String[] args) {
        @SuppressWarnings({"unchecked", "deprecation"})      // 컴파일 시 "경고"를 안나타나게 한다. (경고는 문제없다. 내가 이미 인지하고 있다는 표시)
        ArrayList list = new ArrayList();
        list.add("a");
    }
}

@FunctionalInterface    // 표준 애너테이션 : 함수형 인터페이스인지 체크 (추상메서드가 1개). 컴파일러가 안전하게 체크하도록
interface Testable {
    void test();
    // void check();    // 추상메서드가 2개이면 에러가 발생한다.
}