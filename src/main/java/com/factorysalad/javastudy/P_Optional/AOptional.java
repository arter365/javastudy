package com.factorysalad.javastudy.P_Optional;

import com.factorysalad.javastudy.P_Optional.dto.OnlineClass;
import com.factorysalad.javastudy.P_Optional.dto.Progress;

import java.util.Optional;

/*
 Optional
 메서드가 반환할 결과값이 ‘없음’을 명백하게 표현할 필요가 있고, null을 반환하면 에러를 유발할 가능성이 높은 상황에서 메서드의 반환 타입으로 Optional을 사용하자는 것이
 Optional을 만든 주된 목적이다. Optional 타입의 변수의 값은 절대 null이어서는 안 되며, 항상 Optional 인스턴스를 가리켜야 한다.

 [주의사항 : example4() ]
    1. isPresent()-get() 대신 orElse()/orElseGet()/orElseThrow()
    2. orElse(new ...) 대신 orElseGet(() -> new ...)
    3. Optional은 비싸다. 단지 값을 얻을 목적이라면 Optional 대신 null 비교
    4. Optional은 비싸다. Optional 대신 비어있는 컬렉션 반환
    5. of(), ofNullable() 혼동 주의
    6. Optional<T> 대신 OptionalInt, OptionalLong, OptionalDouble
    7. Optional을 필드, 생성자나 메서드 인자, 컬렉션의 원소로 사용 금지
    사용법. Optional 필터 (값이 참이면 필터를 통과)

*/
public class AOptional {
    public static void main(String[] args) {
        // null 에러가 발생한다.
        /*
        OnlineClass spring_boot1 = new OnlineClass(1, "spring test", true);
        Duration studyDuration1 = spring_boot1.getProgress().getStudyDuration();
        System.out.println(studyDuration1);
        */

        // 이것도 에러를 만들기 쉬운 코드이다.
        /*
        OnlineClass spring_boot2 = new OnlineClass(1, "spring test", true);
        Progress progress = spring_boot2.getProgress();
        if (progress != null) { // 체크를 잊기 쉽다.
            System.out.println(progress.getStudyDuration());
        }
        */

        // null을 리턴하는것 자체가 문제이다.
        OnlineClass spring_boot3 = new OnlineClass(1, "spring test", true);
        Optional<Progress> progress = spring_boot3.getProgress();
        progress.ifPresent(p -> System.out.println(p.getStudyDuration()));

    }
}
