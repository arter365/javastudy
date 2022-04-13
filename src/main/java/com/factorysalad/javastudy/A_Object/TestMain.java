package com.factorysalad.javastudy.A_Object;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* 

학습방향 : 클래스를 안다는 것이 자바를 아는 것이다. 나머지 로직(if, for등)은 메서드에서 그냥 사용하면 된다.

[1일차] 
Object : 11개 메서드만 있다.
    중요메서드 3개 : toString(), hashCode(), equals()
    쓰레드관련 5개 : notify(), notifyAll(), wait()3개
    비중낮은것 3개 : clone(), finalize(), getClass()

중요개념 : instanceOf and casting , Upcasting , Downcasting

*/

public class TestMain {

    public static void main(String[] args) {
        TestMain testMain = new TestMain();
        testMain.objectTest();
        System.out.println("====================================");
        testMain.designPatternTest();
    }

    private void objectTest() {
        Value v1 = new Value(10);
        Value v2 = new Value(10);
        // 객체에서 비교가 필요하면 equals를 오버라이딩하여 사용하는 원칙.
        System.out.println(v1.equals(v2));                                              // true
        // "equals()의 결과가 true인 두 객체의 해시코드는 같아야 한다" - 테스트 - (스트링은 같은 문자열을 공유한다)
        String str1 = new String("abc");
        String str2 = new String("abc");
        System.out.println("equals : " + str1.equals(str2));                            // true
        System.out.println("str1 hashCode : " + str1.hashCode());                       // 96354
        System.out.println("str2 hashCode : " + str2.hashCode());                       // 96354
        // 참고 : 실제 Object의 HashCode를 보고싶다면 아래와 같이 identityHashCode()를 사용한다.
        System.out.println("str1 identityHashCode : " + System.identityHashCode(str1)); // 403716510
        System.out.println("str2 identityHashCode : " + System.identityHashCode(str2)); // 853119666
        // toString 테스트
        System.out.println("str1 toString() : " + str1.toString());                     // abc
        System.out.println("str2 toString() : " + str2.toString());                     // abc
    }

    class Value {
        int value;

        Value(int value) {
            this.value = value;
        }

        // equals : Object 클래스의 equals()는 객체의 주소를 비교하지만 대부분 iv의 값을 비교하는 내용으로 오버라이딩해서 사용한다.
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            // 파라메터가 null이거나 클래스가 서로 다르면 false
            if (o == null || getClass() != o.getClass()) return false;
            Value value1 = (Value) o;
            // 아래는 값을 비교한다.
            return value == value1.value;
        }

        /*
        hashCode : 객체의 해시코드(정수값)를 반환하는 메서드. Object클래스의 hashCode()는 객체의 주소를 int로 변환해서 반환.
                   객체의 주소로 만들기 때문에 객체마다 다른값을 가진다. (객체의 지문)
                   참고 : 32bit JVM은 주소값이 int이다. 64bit JVM은 주소값으로 long을 사용해야 하지만 호환성 때문에 4바이트 int를 여전히 사용한다.
                         그래서 8바이트를 4바이트로 줄이는 과정에서 주소가 겹칠 수 있는 문제가 발생할 수 있다.
        원칙 : equals()의 결과가 true인 두 객체의 해시코드는 같아야 한다.
        결론 : equals()를 오버라이딩하면, hashCode()도 오버라이딩해야 한다. (Tool의 도움을 받을 수 있다)
         */
        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        /*
        toString() : 객체를 문자열(String)으로 변환하기 위한 메서드 (iv를 문자열로 바꾼다는 뜻)
        public String toString() {
            // Object의 toString은 클랙스이름@헤시코드(16진수) 이렇게 표시되게 만들어져 있다.
            return getClass().getName()+"@"+Integer.toHexString(hashCode());
        }
         */
        @Override
        public String toString() {
            return "Value{" +
                    "value=" + value +
                    '}';
        }
    }

    private void designPatternTest() {
        Quackable a = new Bird();
        Quackable b = new Goose();

        // 자바는 모두 call by reference 이다. (이와 반대는 call by value)
        Quackable aa = a;

        // 테스트1 Object (반제품)
        System.out.println(a);              // best.getbetter.javatest.A_Object.Bird@51081592
        System.out.println(aa);             // best.getbetter.javatest.A_Object.Bird@51081592

        // Object (반제품)
        System.out.println(a.toString());   // best.getbetter.javatest.A_Object.Bird@51081592 //object 메서드 : 객체를 출력하면 자동으로 toString이 출력된다.
        System.out.println(a.hashCode());   // 1359484306   //object 메서드 : 해시코드 (객체의 유일한 값)
        System.out.println(a.equals(aa));   // true         // object 메서드 : 객체를 비교한다. (이때 해시코드를 보고 비교한다.)
        System.out.println(a.equals(b));    // false

        //===========================================
        Workable c = new Monkey();

        // Upcasting은 자동으로 이루어진다. 
        List<Object> list = new ArrayList<Object>();
        list.add(a);
        list.add(b);
        list.add(c);

        for (int i = 0; i < list.size(); i++) {
            //---------테스트1------------------ 
            // list.get(i);  // Object가 리턴되기 때문에 자신의 메서드에 접근 할 수 없다. 

            //---------테스트2------------------ Quancable이 아닌 객체가 있기 때문에 에러가 발생한다. 문법오류가 없기 때문에 이를 런타임 에러라고 한다.
            // Object temp = list.get(i);
            // Quackable q = (Quackable)temp;
            // System.out.println(q.quick());

            //---------테스트3------------------ 의외로 다른 클래스를 같이 처리 하는 일이 많이 있다. 
            Object temp = list.get(i);
            if (temp instanceof Quackable) {  // instanceof는 연산자이다. (type)도 연산자이다.
                Quackable q = (Quackable) temp;
                System.out.println(q.quick());
            } else {
                Workable w = (Workable) temp;
                System.out.println(w.work());
            }
            // 결과 : 짹짹 , 구구 , 걷는다
        }

        //===========================
        // 아래와 같이 인터페이스 없이 생성 할 수 있다. 
        // 인터페이스를 사용하는 이유는 같은 방식으로 연속된 처리를 하기 위함. 그리고 앞으로의 확장성도 열어둠. 
        Bird bird = new Bird();
        bird.quick();

        Goose goose = new Goose();
        goose.quick();

        // quackable 인터페이스만 구현한 객체라면 까지 , 까마귀 , 독수리등등 무슨 객체가 생성되어도 아래의 구문은 변경 없이 사용 가능하다.  
        // 이것이 인터에이스의 목적이고 인터페이스를 사용하면 설계가 잘 된 것이다. 
        List<Quackable> quackableList = new ArrayList<Quackable>();
        for (int i = 0; i < quackableList.size(); i++) {
            quackableList.get(i).quick();
        }

        // 상수 테스트 (실제 사용 시 숫자보다 문자를 사용한다)
        int test = 2;
        if (test == Quackable.DOWN) {
            System.out.println("DOWN");         // DOWN
        } else if (test == Quackable.UP) {
            System.out.println("UP");
        }

        // java API에서도 상수를 사용한다. 
        System.out.println(Math.PI);    // 3.141592653589793
    }

}