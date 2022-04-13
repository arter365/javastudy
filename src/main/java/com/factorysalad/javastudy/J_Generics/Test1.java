package com.factorysalad.javastudy.J_Generics;

import java.util.ArrayList;
import java.util.List;

/*
지네릭스(Generics)
- 컴파일 시 타입을 체크해 주는 기능(compile-time type check) - JDK1.5
- 객체의 타입 안정성을 높이고 형변환의 번거로움을 줄여준다.
 */
public class Test1 {
    public static void main(String[] args) {
        Test1 test1 = new Test1();
        test1.One();
        test1.Two();
    }

    private void One() {
        List list = new ArrayList();
        list.add(10);
        list.add(20);
        list.add("30"); // String을 추가 할 수 있다.

        // Object를 받고 Object를 리턴하기 때문에 형변환 해 준다.
        Integer i = (Integer) list.get(2);  // 이 구문은 런타임 시 에러가 발생한다.

        System.out.println(list);
    }

    private void Two() {
        List<Integer> list = new ArrayList<>();    // 뒷부분은 추론으로 생략가능.
        list.add(10);
        list.add(20);
        //list.add("30"); // String을 추가 할 수 없다.

        // Object를 받고 Object를 리턴하기 때문에 형변환 해 준다.
        Integer i = list.get(2);  // 이미 타입을 알고 있기 때문에 형변환이 필요없다.

        System.out.println(list);
    }
}


