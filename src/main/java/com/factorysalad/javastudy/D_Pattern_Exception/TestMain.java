package com.factorysalad.javastudy.D_Pattern_Exception;

import java.util.ArrayList;
import java.util.List;

/*

[3일차]
패턴 : 스트레티지 , 어뎁터 , 싱글턴 (Static 특징) Exception : try, catch, finally를 사용 할 수 있다. 
사용자 Exception을 만들 수 있다. (Spring Boot에서 사용한다.) : this and super , extends 
프로그램의 대부분은 문자, 숫자, 날짜를 처리하는 작업이다.(실생활도 이 3가지가 전부이기 때문) 
이 작업을 얼마나 능숙하게 하는가에 따라 프로그램의 숙련도가 굉장히 높아보인다. 
객체를 알고 자료를 능숙하게 처리하고 프레임워크(Spring Boot, JPA)를 사용하는것이 전부이다. 
나머지는 자바의 디자인 원칙에 의해 만들어진 클래스의 API를 보고 사용하면 된다. 

*/

public class TestMain {

    public static void main(String[] agrs) {
        TestMain testMain = new TestMain();
        testMain.example();
    }

    private void example() {
        List<Quackable> list = new ArrayList<Quackable>();

        // Adapter Pattern
        Quackable bird = new Bird();
        Quackable goose = new Goose();

        // lion 코드를 고치지 않는다.
        Crying lion = new Lion();
        // Adapter Pattern (기존의 코드 for문과 lion객체를 고치지 않고 소스에 적응 시켰다. )
        LionAdapter lionAdapter = new LionAdapter(lion);

        list.add(bird);
        list.add(goose);
        list.add(lionAdapter);

        // 기존 코드도 고치지 않는다.
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).quick());
        }

        // 싱글턴 테스트 & Exception
        String temp = null;
        try {
            temp = SingletonTest.getInstance().sayHello();
        } catch (TestException e) {
            // 에러에 대한 처리를 한다.
            e.printStackTrace();
        } finally {
            // 반드시 처리해야 하는 구문을 넣는다.
            // 주로 DB Connection을 닫는 것 같은 객체를 마무리 하는 용도로 사용한다.
        }
        System.out.println(temp);
    }
}