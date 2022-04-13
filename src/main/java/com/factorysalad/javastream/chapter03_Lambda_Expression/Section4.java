package com.factorysalad.javastream.chapter03_Lambda_Expression;

import com.factorysalad.javastream.chapter03_Lambda_Expression.util.TriFunction;

/*
인자가 3개인 함수는 없기 때문에 TriFunction 함수를 만들었다.
 */
public class Section4 {
    public static void main(String[] args) {
        TriFunction<Integer, Integer, Integer, Integer> addThreeNumbers = (x, y, z) -> x + y + z;
        int result = addThreeNumbers.apply(3,2,5);
        System.out.println(result);
        // 결과 : 10
    }
}
