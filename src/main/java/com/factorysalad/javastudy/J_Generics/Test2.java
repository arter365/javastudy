package com.factorysalad.javastudy.J_Generics;

import java.util.ArrayList;
import java.util.List;

/*
타입 변수
- 클래스를 작성할 때, 설계 시 Object타입 대신 타입변수 (대문자 한글자 -> E : Element 또는 T : Type을 많이 사용한다.)를 선언해서 사용.
- 클래스를 사용할 때는 실제 타입을 적어준다.
- 클래스에 Object를 포함하고 있는 클래스는 Generics로 바뀌었다.

용어
Box<T>  : 지네릭 클래스. 'T의 Box' 또는 'T Box'라고 읽는다.
T       : 타입 변수 또는 타입 매개변수. (T는 타입 문자)
Box     : 원시 타입 (raw type), 일반클래스

주의 :
ArrayList<Product> list = new ArrayList<Tv>(); // 에러, 상속관계에서도 타입 변수는 일치해야 한다.
list.add(new Product());
list.add(new Tv());         // 이렇게 추가는 가능하다. (부모타입으로 들어간다)

Product p = list.get(0);
Tv t = (Tv)list.get(1);     // 형변환이 필요하다.
 */
public class Test2 {
    public static void main(String[] args) {
        // Generic을 사용해서 클래스 만들기
        Box<Integer> box = new Box<>();
        box.setItem(123);
        Integer res = box.getItem();

        // 특정 타입의 자손들만 대입할 수 있게 제한하기
        Animal animal = new Animal();
        animal.add(new Bird());
        animal.add(new Goose());
        Quackable bird = animal.getItem(0);
        Goose goose = (Goose) animal.getItem(1);
    }
}

// Generic을 사용해서 클래스 만들기
class Box<T> {
    private T item;
    private T[] itemArr;            // T[]을 받는 참조변수는 가능함.
    // public static T name;        // static 멤버에는 타입변수를 사용할 수 없다.

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public T[] getItemArr() {       // T[] 참조변수를 리턴하는 것도 가능
        return this.itemArr;
    }

    public void setT(T t) {
    }

    /*
    // Generic 타입의 배열 T[]를 생성하는 것은 허용되지 않는다.
    public T[] toArray() {
        T[] tmpArr = new T[itemArr.length];
        return tmpArr;
    }
    */

    /*
    // static 멤버에는 타입변수를 사용할 수 없다.
    public static setName(T name) {
        this.name = name
    }
    */
}

// 특정 타입의 자손들만 대입할 수 있게 제한하기
class Animal<T extends Quackable> {
    private List<T> list = new ArrayList<>();

    public void add(T item) {
        list.add(item);
    }

    public T getItem(int index) {
        return list.get(index);
    }
}
