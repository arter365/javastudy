package com.factorysalad.javastudy.J_Generics;

import java.util.ArrayList;
import java.util.List;

/*
지네릭 타입에 와일드 카드를 사용하면, 여러 타입을 대입가능
단, 와일드 카드에서 <? extends T & E>와 같이 '&'를 사용불가.

- <? extends T> : 와일드 카드의 상한 제한. T와 그 자손들만 가능. (가장 많이 사용한다)
- <? super T> : 와일드 카드의 하한 제한. T와 그 조상들만 가능.
- <?> : 제한 없음. 모든 타입이 가능. <? extends Object>와 동일.
 */
public class Test3 {
    public static void main(String[] args) {
        FruitBox<Fruit> fruitBox = new FruitBox<>();    // 참조변수와 생성자의 제네릭타입은 일치해야 한다.
        // 이렇게도 가능하다. : FruitBox<? extends Fruit> appleBox = new FruitBox<Apples>();
        FruitBox<Apples> appleBox = new FruitBox<>();

        // 제네릭타입으로 Frute와 그 자손들이 들어올 수 있다.
        fruitBox.add(new Apples());
        fruitBox.add(new Grape());
        appleBox.add(new Apples());
        appleBox.add(new Apples());

        System.out.println(Juicer1.makeJuice(fruitBox)); // Apple Grape
        System.out.println(Juicer1.makeJuice(appleBox)); // Apple Apple

        // 지네릭 메서드 : 메서드를 호출 할 때마다 타입을 대입해야 한다. (대붑분 생략 가능)
        //               메서드를 호출할 때 타입을 생략하지 않을 때는 클래스 이름 생략 불가
        System.out.println(Juicer2.<Fruit>makeJuice(fruitBox)); // Apple Grape
        System.out.println(Juicer2.<Apples>makeJuice(appleBox)); // Apple Apple
    }
}

// ------------------------------------------------------------------
// 제네릭타입으로 Frute와 그 자손들이 들어올 수 있다.
class FruitBox<T extends Fruit> extends Case<T> {

}

class Case<T> {
    private List<T> list = new ArrayList<>();

    public void add(T item) {
        this.list.add(item);
    }

    public T get(int i) {
        return this.list.get(i);
    }

    public List<T> getList() {
        return this.list;
    }

    public int size() {
        return this.list.size();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}

// ------------------------------------------------------------------
class Juicer1 {
    static Juice makeJuice(FruitBox<? extends Fruit> box) {
        String tmp = "";
        for(Fruit f : box.getList())
            tmp += f + " ";
        return new Juice(tmp);
    }
}

/*
지네릭 메서드 : 지네릭 타입이 선언된 메서드(타입 변수는 메서드 내에서만 유효)
              클래스의 타입 매개변수<T>와 메서드의 타입 매개변수 <T>는 별개
              class FruitBox<T> {
                    static <T> void sort(List<T> list, Comparator<? super T> c) {
                        ...
                    }
              }
 */
class Juicer2 {
    static <T extends Fruit> Juice makeJuice(FruitBox<T> box) {
        String tmp = "";
        for(Fruit f : box.getList())
            tmp += f + " ";
        return new Juice(tmp);
    }
}

class Juice {
    private String name;

    Juice(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

// ----------------------------------------------------------------

class Fruit {
    @Override
    public String toString() {
        return "Fruit";
    }
}

class Apples extends Fruit {
    @Override
    public String toString() {
        return "Apple";
    }
}

class Grape extends Fruit {
    @Override
    public String toString() {
        return "Grape";
    }
}




