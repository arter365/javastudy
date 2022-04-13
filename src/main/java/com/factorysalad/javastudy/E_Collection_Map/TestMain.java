package com.factorysalad.javastudy.E_Collection_Map;

import java.util.*;

/*

[2일차]

Collection
    List (ArrayList : 메모리크게 잡음, 배열기반(연속), LinkedList : 데이터 많은수록 접근성 떨어짐, 연결기반(불연속)) : 순서있고 , 중복허용
         성능비교 : 순차추가(406 vs 606), 순차삭제(11 vs 46), 중간추가(11 vs 46), 중간삭제(6694 vs 380), 접근시간(1 vs 432)
    Set (HashSet, TreeSet) : 순서없고 , 중복없고
         성능비교 : TreeSet이 데이터 추가, 삭제에 시간이 더 걸린다.
Map (HashMap, TreeMap) : 순서없고 , {키중복없고, 값중복있고}
         성능비교 : HashMap(new:비동기화)과 HashTable(old:동기화)은 같지만 동기화 유무의 차이가 있다. TreeMap이 데이터 추가, 삭제에 시간이 더 걸린다.



값전달 : 객체안에 객체 넣기 List and Set 안에 객체 넣기 (call by reference 메모리를 그릴 수 있어야 한다) 
연속된 저장공간 : ArrayList(List류 중복허용 순서 있음.) , HashTable(키와 값으로 저장.) 
외부 라이브러리 포함 (많은 사람이 사용하는 프로그램의 좋은 점은 라이브러리가 많다는 점이다. SpringBoot의 의존성이 이 부분에 해당한다.)
예 : XmlRPC : (findByPartiesById를 export=true로 변경한다.) 
프로젝트 바로 아래 lib 폴더를 만들고 프로젝트 - 마우스 오른쪽 - properties - Java Build Path - Add JARs 



*/
public class TestMain {

    public static void main(String[] args) {
        TestMain testMain = new TestMain();
        testMain.collectionTest();
        testMain.mapTest();
    }

    private void collectionTest() {
        // Collection ====================================================================
        // List---------------------------------------------------------------------------
        List<Person> list = new ArrayList<>();     // 뒤의 <> 제네릭은 유추가 가능하기 때문에 생략이 가능하다.

        Person person1 = new Person();
        person1.setName("1111");
        person1.setAddress("11111111111");

        Person person2 = new Person();
        person2.setName("2222");
        person2.setAddress("2222222222222222");

        list.add(person1);
        list.add(person2);

        for (int i = 0; i < list.size(); i++) {
            Person temp = list.get(i);
            System.out.println("arrayList for 문 : " + temp.getName() + " : " + temp.getAddress());
        }

        // Iterator를 사용하는 이유 : 컬렉션에 저장된 요소들을 읽어오는 방법을 표준화 한 것. (Collection에 구현되어 있다)
        // Iterator, ListIterator, Enumeration은 동일하고 Iterator만 사용할 줄 알면된다.
        Iterator<Person> it = list.iterator();  // Iterator는 1회용이다.
        while (it.hasNext()) {
            Person temp = it.next();
            System.out.println("arrayList Iterator 문 : " + temp.getName() + " : " + temp.getAddress());
        }

        // Set  ---------------------------------------------------------------------------
        /*
        HashSet은 객체를 저장하기전에 기존에 같은 객체가 있는지 확인한다.
        그래서 add(Object o) 전에 equals()와 hashCode()를 호출한다. 그래서 내가 만든 클래스의 이 두 매서드는 오버라이딩 되어 있어야 한다.
         */
        Set<Person> set = new HashSet<>();     // 뒤의 <> 제네릭은 유추가 가능하기 때문에 타입파라메터 생략이 가능하다.

        Person person3 = new Person();
        person3.setName("1111");
        person3.setAddress("11111111111");

        Person person4 = new Person();
        person4.setName("2222");
        person4.setAddress("2222222222222222");

        set.add(person3);  // One클래스의 오버라이딩된 equals(), hashCode()를 호출하여 중복을 확인한다.
        set.add(person4);

        // get메서드가 없다.

        // Iterator를 사용하는 이유 : 컬렉션에 저장된 요소들을 읽어오는 방법을 표준화 한 것. (Collection에 구현되어 있다)
        Iterator<Person> it2 = set.iterator();  // Iterator는 1회용이다.
        while (it2.hasNext()) {
            Person temp = it2.next();
            System.out.println("set Iterator 문 : " + temp.getName() + " : " + temp.getAddress());
        }

    }

    private void mapTest() {
        // Map ========================================================================
        // Map에는 Iterator()가 없기 때문에 keySet(), entrySet(), values()를 호출한 후 iterator()를 호출한다. // entry는 키, 값을 가지는 객체이다. (keySet(), values()로 각각 가져 올 수 있다)
        HashMap map = new HashMap();
        map.put("김자바", 90);
        map.put("김자바", 100);
        map.put("이자바", 100);
        map.put("강자바", 80);
        map.put("안자바", 90);

        Set set = map.entrySet();
        Iterator it = set.iterator();

        while(it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            System.out.println("이름 : "+ e.getKey() + ", 점수 : " + e.getValue());
        }

        set = map.keySet();
        System.out.println("참가자 명단 : " + set);

        Collection values = map.values();
        it = values.iterator();

        int total = 0;

        while(it.hasNext()) {
            int i = (int)it.next();
            total += i;
        }

        System.out.println("총점 : " + total);
        System.out.println("평균 : " + (float)total/set.size());
        System.out.println("최고점수 : " + Collections.max(values));
        System.out.println("최저점수 : " + Collections.min(values));
    }

}