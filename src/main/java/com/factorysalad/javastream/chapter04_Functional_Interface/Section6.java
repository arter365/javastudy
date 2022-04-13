package com.factorysalad.javastream.chapter04_Functional_Interface;

import com.factorysalad.javastream.chapter04_Functional_Interface.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Comparator : 비교를 위한 인터페이스

@FunctionalInterface
public interface Comparator<T> {
    int compare (T o1, T o2);
}

- 리턴값이 음수면 : o1 < o2
- 리턴값이 0이면 : o1 = 02
- 리턴값이 양수면 : o1 > 02
 */
public class Section6 {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User(3, "Alice"));
        users.add(new User(1, "Charlie"));
        users.add(new User(5, "Bob"));
        System.out.println("원본 : " + users);
        // 결과 : 원본 : [User{id=3, name='Alice'}, User{id=1, name='Charlie'}, User{id=5, name='Bob'}]

        // java util의 Collections.sort는 인자로 리스트와 Comparator를 받는다.
        Comparator<User> idComparator = (u1, u2) -> u1.getId() - u2.getId();
        Collections.sort(users, idComparator);
        System.out.println("id 정렬 : " + users);
        // 결과 : id 정렬 : [User{id=1, name='Charlie'}, User{id=3, name='Alice'}, User{id=5, name='Bob'}]

        // String 정렬
        Collections.sort(users, (u1, u2) -> u1.getName().compareTo(u2.getName()));
        System.out.println("name 정렬 : " + users);
        // 결과 : name 정렬 : [User{id=3, name='Alice'}, User{id=5, name='Bob'}, User{id=1, name='Charlie'}]
    }
}
