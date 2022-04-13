package com.factorysalad.javastudy.B_Class;

/*

학습방향 : 추상클래스, 내부클래스, 인터페이스(디폴트메소드, 스테틱메소드), 익명클래스

추상클래스(abstract class) 장점 :
모듈처럼 중복되는 부분이나 공통적인 부분은 미리 다 만들어진 것을 사용하고, 이를 받아 사용하는 쪽에서는 자신에게 필요한 부분만을
재정의하여 사용함으로써 생산성이 향상되고 배포 등이 쉬워지기 때문.

내부클래스(inner class) 장점 :
1. 내부 클래스에서 외부 클래스의 멤버에 손쉽게 접근할 수 있게 된다.
2. 서로 관련 있는 클래스를 논리적으로 묶어서 표현함으로써, 코드의 캡슐화를 증가시킨다.
3. 외부에서는 내부 클래스에 접근할 수 없으므로, 코드의 복잡성을 줄일 수 있다.

인터페이스에 추가된 내용 (java8부터 추가됨. 인터페이스 원칙에는 위배) :
- 디폴트 메소드
인터페이스가 변경이 되면, 인터페이스를 구현하는 모든 클래스들이 해당 메소드를 구현해야 하는 문제가 있다.
이런 문제를 해결하기 위하여 인터페이스에 메소드를 구현해 놓을 수 있도록 하였다.
- 스테틱 메소드
인터페이스에 static 메소드를 선언함으로써, 인터페이스를 이용하여 간단한 기능을 가지는 유틸리티성 인터페이스를 만들 수 있게 되었다.

익명클래스(anonymous class) 장점 :
이름이 없는 일회용 클래스. 정의와 생성을 동시에 처리한다. (부모클래스 이름 또는 인터페이스로 new 한다.)
익명클래스를 사용하면 간단히 클래스를 만들고 바로 처리 할 수 있다는 장점이 있다. 또한 함수형 프로그램에서 익명클래스 형태가 사용된다.

*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestMain {
    public static void main(String[] args) {
        // 추상클래스
        RemoteControl remoteControl = new Lg();
        remoteControl.setProduct(RemoteControl.SAMSUNG);
        // 내부클래스
        Outter outter = new Outter();
        Outter.Printable printable = outter.myMethod();
        printable.test();
        printable.defaultMethod();
        int i = Outter.Printable.exec(3, 2);
        System.out.println(i);
    }
}

// 추상클래스 ==================================================================================

// 추상클래스
abstract class RemoteControl {
    // 상수가능
    public static final int LG = 0;
    public static final int SAMSUNG = 1;

    private boolean state;

    // 추상메서드
    abstract void setProduct(int brand);

    // 일반메서드 가능
    public boolean getState() {
        return this.state;
    }
}

// 추상메서드 구현
class Lg extends RemoteControl {
    @Override
    void setProduct(int brand) {
        // 코드 생략
        System.out.println("ok");   // ok
    }
}

// 내부클래스 ==================================================================================
// 외부에서 사용할 일이 없는 클래스를 내부에 선언하여 외부클래스의 자원을 사용하는 방법.
class Outter {
    private int iv1 = 100;
    static int cv1 = 100;
    final static int CONST1 = 100;

    private InstanceInner instanceInner = new InstanceInner();
    private StaticInner staticInner = new StaticInner();

    // 내부클래스의 접근제한자는 public, private, protected, default 모두 사용 가능하고 변수에 적용되는 규칙과 동일함.
    private class InstanceInner {
        int iv2 = 200;
        // static int cv2 = 200;         // 에러! static 변수를 선언 할 수 없다. (부모 없이 먼저 생성될 수 없기 때문)
        final static int CONST2 = 200;   // 상수는 가능

        public void test() {
            System.out.println("InstanceInner : " + iv1);   // InstanceInner : 100
            System.out.println("InstanceInner : " + iv2);   // InstanceInner : 200
        }
    }

    protected static class StaticInner {
        int iv3 = 300;
        static int cv3 = 300;
        final static int CONST3 = 300;   // 상수

        public void test() {
            // System.out.println("StaticInner : " + iv1);
            System.out.println("StaticInner : " + iv3);     // StaticInner : 300
        }
    }

    interface Printable {
        void test();

        // 디폴트 메서드
        default void defaultMethod() {
            System.out.println("Default Method Call~~");
        }

        // 스테틱 메서드
        public static int exec(int i, int j) {
            return i * j;
        }
    }

    // Outter 클래스의 메서드
    public Printable myMethod() {
        instanceInner.test();
        staticInner.test();

        class LocalInner implements Printable {
            int iv4 = 400;
            // static int cv4 = 400;         // 에러! static 변수를 선언 할 수 없다.
            final static int CONST4 = 400;   // 상수

            @Override
            public void test() {
                System.out.println("LocalInner : " + iv1);      // LocalInner : 100
                System.out.println("LocalInner : " + iv4);      // LocalInner : 400
            }
        }
        return new LocalInner();
    }
}

// 익명클래스 ==================================================================================
// 아래는 익명클래스로 만들지 않았다.--------------------------------
class NotAnonymousTest {
    public static void main(String[] args) {
        Button b = new Button("Start");
        b.addActionListener(new EventHandler());
    }
}

// 1회성 클래스라서 별도로 안만들어도 된다.
class EventHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("ActionEvent occurred!!!");
    }
}

// 아래는 익명클래스로 만들었다. (위와 동일한 내용)--------------------
class AnonymousTest {
    public static void main(String[] args) {
        Button b = new Button("Start");
        // 익명이기 때문에 부모의 이름 또는 인터페이스의 이름으로 생성한다.
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ActionEvent occurred!!!");
            }
        });
    }
}