package com.factorysalad.javastudy.O_Stream;

import com.factorysalad.javastudy.O_Stream.dto.OnlineClass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BStream {
    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1,"spring boot",true));
        springClasses.add(new OnlineClass(2,"spring data jpa",true));
        springClasses.add(new OnlineClass(3,"spring mvc",false));
        springClasses.add(new OnlineClass(4,"spring core",false));
        springClasses.add(new OnlineClass(5,"rest api development",false));

        System.out.println("spring 으로 시작하는 수업=====================================");
        springClasses.stream()
                .filter(c -> c.getTitle().startsWith("spring"))
                .forEach(c -> System.out.println(c.getTitle()));

        System.out.println("close 되지 않은 수업=========================================");
        springClasses.stream()
                .filter(Predicate.not(OnlineClass::isClosed))  // .filter(c -> c.isClosed())   // ! 연산자를 사용 할 수 없다.
                .forEach(c -> System.out.println(c.getTitle()));

        System.out.println("수업 이름만 모아서 스트림 만들기================================");
        Stream<String> classNames = springClasses.stream()
                .map(OnlineClass::getTitle);
        classNames.forEach(System.out::println);

        // 아래와 같이 축약해서 표현가능.
        System.out.println("--------------");
        springClasses.stream()
                .map(OnlineClass::getTitle)     // 이제 String 스트림을 리턴한다.
                .forEach(System.out::println);

        //=================================================================================================

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        List<List<OnlineClass>> twoLists = new ArrayList<>();
        twoLists.add(springClasses);
        twoLists.add(javaClasses);

        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력=====================");
        twoLists.stream()
                .forEach(l -> l.stream().forEach(c -> System.out.println(c.getId())));

        twoLists.stream()                       // List는 Collection이다. 이 클래스의 스트림을 사용하여 스트림을 가져온다. (Collection::stream)은 (l -> l.stream())과 동일한 구문이다.
                .flatMap(Collection::stream)    // flatMap()은 Array나 Object로 감싸져 있는 모든 원소를 단일 원소 스트림으로 반환한다. 그래서 위와는 달리 첫번째 forEach구문이 필요없다.
                .forEach(c -> System.out.println(c.getId()));

        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만====");
        Stream.iterate(10, i -> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인 ========================");
        boolean check = javaClasses.stream().anyMatch(c -> c.getTitle().contains("Test"));
        System.out.println(check);

        System.out.println("스프링 수업 중에 제목에 spring이 들거간 제목만 모아서 List로 만들기 =========");
        List<String> springList1 = twoLists.stream()
                .flatMap(Collection::stream)
                .filter(c -> c.getTitle().contains("spring"))
                .map(c -> c.getTitle())
                .collect(Collectors.toList());

        springList1.forEach(System.out::println);

        // 위의 내용을 순서를 바꾸면 아래와 같다.
        List<String> springList2 = twoLists.stream()
                .flatMap(Collection::stream)    //
                .map(OnlineClass::getTitle)
                .filter(t -> t.contains("spring"))
                .collect(Collectors.toList());

        springList2.forEach(System.out::println);

    }
}
