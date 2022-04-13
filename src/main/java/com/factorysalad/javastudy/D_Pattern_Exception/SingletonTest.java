package com.factorysalad.javastudy.D_Pattern_Exception;

/**
 * Singleton 디자인 패턴은 new 해서 계속 같은 객체가 사용되는 것을 방지하기 위해서 사용된다. 
 * 주로 서비스를 만들 때 많이 사용된다. 
 */
public class SingletonTest {
    // 생성자가 막혔기 때문에 Static 방식으로 클래스 이름으로 접근하는 방식을 택한다. 
    // 하지만 외부에서 바로 접근하는 것을 막기 위해서 변수를 private로 선언했다.  
    private static SingletonTest instance;

    // 생성자를 private로 막아서 외부에서 생성되는 것을 막는다. 
    private SingletonTest() {}

    // 이 클래스의 인스턴스를 생성하는 메서드는 아래와 같다. 
    // static 방식이라 이름에 점찍고 접근이 가능하고 클래스 내에서는 접근제한자가 무의미 하다. 
    public static SingletonTest getInstance() {
        if (instance == null) 
            instance = new SingletonTest();
        return instance;
    }

    // 객체가 생성되면 일반 메서드와 필드도 다 사용이 가능하다. 
    public String sayHello() throws TestException { // Exception처리를 이 메서드를 호출한 곳으로 미룬다.
        if(instance == null){
            // Exception을 발생시키는 구문 
            throw new TestException();
        }
        // 정상적으로 처리되었으면 아래의 메시지를 리턴한다. 
        return "hello";
    }

}