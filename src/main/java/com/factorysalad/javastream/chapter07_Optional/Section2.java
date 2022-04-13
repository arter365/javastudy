package com.factorysalad.javastream.chapter07_Optional;

import com.factorysalad.javastream.chapter07_Optional.model.User;

import java.util.Optional;

/*
Optional 응용을 위해 알아야 할 것들

public void ifPresent(Consumer<? super T> action)
- ifPresent : Optional이 null이 아니라면 action을 실행.

public <U> Optional<U> map(Function<? super T, ? extends U> mapper)
- map : Optional이 null이 아니라면 mapper를 적용.

public <U> Optional<U> flatMap(Function<? super T, ? extends Optional<? extends U>> mapper)
- flatMap : mapper의 리턴 값이 또 다른 Optional이라면 한 단계의 Optional이 되도록 납작하게 해줌.
 */
public class Section2 {
    public static void main(String[] args) {
        // ifPresent : Optional이 null이 아니라면 action을 실행.
        Optional<User> maybeUser = Optional.ofNullable(maybeGetUser(true));
        maybeUser.ifPresent(user -> System.out.println(user));
        // 결과 : User{id=1001, name='Alice', emailAddress='alice@fastcampus.co.kr', isVerified=false, friendUserIds=null}

        // map : Optional이 null이 아니라면 mapper를 적용.
        Optional<Integer> maybeId = Optional.ofNullable(maybeGetUser(true))
                .map(user -> user.getId());
        maybeId.ifPresent(System.out::println);
        // 결과 : 1001

        // map : 두번째 예제 체이닝이 가능하다.
        String userName = Optional.ofNullable(maybeGetUser(false))
                .map(User::getName)
                .map(name -> "The name is " + name)
                .orElse("Name is empty");   // null 일때 적용
        System.out.println(userName);
        // 결과 : Name is empty

        // flatMap : mapper의 리턴 값이 또 다른 Optional이라면 한 단계의 Optional이 되도록 납작하게 해줌.
        // User에서 Optional<String>을 리턴하게 되어있기 때문에 flatMap을 사용하였다.
        Optional<String> maybeEmail = Optional.ofNullable(maybeGetUser(true))
                .flatMap(User::getEmailAddress);
        maybeEmail.ifPresent(System.out::println);  // 값이 없으면 프린트 되지 않는다.
        // 결과 : alice@fastcampus.co.kr
    }

    // 도움 메서드
    public static User maybeGetUser(boolean returnUser) {
        if (returnUser) {
            return new User()
                    .setId(1001)
                    .setName("Alice")
                    .setEmailAddress("alice@fastcampus.co.kr")
                    .setVerified(false);
        }
        return null;
    }
}
