package com.factorysalad.javastudy.I_Enum;

/*

[7일차] 
(#4 : enum) <br>

참고 사이트 (배민) : https://techblog.woowahan.com/2527/

1.5 버전부터 새롭게 추가되었다.
enum은 관련 있는 상수들의 집합이다.
이전에 상수를 class 또는 interface를 사용하였지만 이제는 class 대신 enum 키워드를 사용하여 정의 할 수 있다.
그리고 아래의 Day와 같이 상수의 이름을 차례대로 나열하면 상수가 된다. (데이터 타입을 생략하고 상수만 입력 할 수 있다.)
enum은 완전한 기능을 갖춘 클래스이다.

장점 :
- 코드가 단순해지며 가독성이 좋아진다.
- 인스턴스 생성과 상속을 방지할 수 있다.
- enum 키워드로 의도가 분명해 진다.
 */

enum Fruit {
    /*
    // enum의 상수는 아래의 내용과 같다. 이렇게 하는 이유는 서로다른 인터페이스에 정의된 상수의 값이 같아도 다르게 보기 위함이다.
    // 숫자가 아니라 객체로 값을 표현했기 때문에 컴파일 타임에 오류를 확인 할 수 있다.
    public static final Fruit APPLE = new Fruit();
    public static final Fruit PEACH = new Fruit();
    public static final Fruit BANANA = new Fruit();

    // 위의 선언과 동일하다.
    enum Fruit {
        APPLE, PEACH, BANANA
    }
     */
    APPLE("사과", 57), PEACH("복숭아", 34), BANANA("바나나",93);

    // enum은 클래스이기 때문에 변수를 가질 수 있다.
    private String kinds;
    private int kcal;

    public String getKinds() { return this.kinds; }
    public int getKcal(){
        return this.kcal;
    }

    // 생성자 (중요 : 상수의 갯수만큼 생성자가 호출된다. 선언된 상수를 이용해 객체를 생성한다. 상수는 프로그램 실행시 즉시 만들어진다.)
    Fruit(String kinds, int kcal){
        System.out.println("Call Constructor " + this);
        this.kinds = kinds;
        this.kcal = kcal;
    }
}

public class TestMain {
    public static void main(String[] args) {
        Fruit type = Fruit.APPLE;
        // switch 조건문에 올 수 있는 값 : byte, short, char, int, enum, String, Character, Byte, Short, Integer
        switch (type) {
            case APPLE:
                System.out.println(Fruit.APPLE.getKinds() + " : " + Fruit.APPLE.getKcal());
                break;
            case PEACH:
                System.out.println(Fruit.PEACH.getKinds() + " : " + Fruit.PEACH.getKcal());
                break;
            case BANANA:
                System.out.println(Fruit.BANANA.getKinds() + " : " + Fruit.BANANA.getKcal());
                break;
        }

        // 아래와 같이 연속된 처리도 가능하다.
        for (Fruit f : Fruit.values()) {
            System.out.println(f);
        }
    }

}