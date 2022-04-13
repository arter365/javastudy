package com.factorysalad.javastudy.P_Optional;

import com.factorysalad.javastudy.O_Stream.dto.OnlineClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
1. isPresent()-get() 대신 orElse()/orElseGet()/orElseThrow()
2. orElse(new ...) 대신 orElseGet(() -> new ...)
3. 단지 값을 얻을 목적이라면 Optional 대신 null 비교
4. Optional 대신 비어있는 컬렉션 반환 (jpa도 비어있는 컬렉션을 반환한다)
5. Optional을 필드로 사용 금지
6. Optional을 생성자나 메서드 인자로 사용 금지
7. Optional을 컬렉션의 원소로 사용 금지
8. of(), ofNullable() 혼동 주의
9. Optional<T> 대신 OptionalInt, OptionalLong, OptionalDouble
 */

public class BOptional {
    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        Optional<OnlineClass> optional = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        // present(본문)가 있는지 확인한다.
        boolean present1 = optional.isPresent();
        boolean present2 = optional.isEmpty();
        System.out.println(present1);
        System.out.println(present2);

        // if를 사용하지 않고 optional의 function을 사용한다. (optional의 get() 보다 아래 예제와 같이 사용한다.)
        optional.ifPresent(oc -> System.out.println(oc.getTitle()));

        // 없으면 객체를 생성한다.
        // OnlineClass onlineClass = optional.orElseGet(BOptional::createNewClass); // 아래와 동일한 방법이다.
        OnlineClass onlineClass = optional.orElseGet(() -> createNewClass());
        System.out.println(onlineClass.getTitle());

        // 에러를 발생하고 시키고 싶으면 아래와 같이 사용한다.
        OnlineClass onlineClass1 = optional.orElseThrow(IllegalStateException::new);

        // 필터를 적용하면 Optional이 리턴된다.
        Optional<OnlineClass> onlineClass2 = optional.filter(oc -> !oc.isClosed());

        // Map을 사용 할 수 있다.
        Optional<Integer> integer = optional.map(OnlineClass::getId);
    }

    private static OnlineClass createNewClass() {
        System.out.println("creating new online class");
        return new OnlineClass(10, "new Class", false);
    }
}
