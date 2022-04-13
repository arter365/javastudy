package com.factorysalad.javastream.chapter03_Lambda_Expression.util;

/*
인자가 3개인 함수는 없기 때문에 아래와 같이 만들어서 사용해야 한다.
 */
@FunctionalInterface
public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}
