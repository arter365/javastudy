package com.factorysalad.javastudy.F_BigDecimal;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/*

[5일차] 
(#2 : 빅데시멀) <br>

*/
public class TestMain {

    public static void main(String[] args) {
        TestMain testMain = new TestMain();
        testMain.example();
    }

    private void example() {
        // 2.4 / 0.8 = 3
        double a = 2.4;
        double b = 0.8;
        System.out.println(a / b); // 2.9999999999999996

        // 2.9999999999999996 (float과 double 연산은 99.9%의 정확도를 가진다. )
        // 내부적으로 수를 저장할 때 이진수의 근사치를 저장하기 때문이다.
        // 그렇기 때문에 돈과 소수점을 다룬다면 BigDecimal 은 선택이 아니라 필수이며 유일한 방법이다.
        // 금융권에서는 시스템 개발 시 혼란을 막기 위해 요구사항에 반올림 정책을 명확히 명시하여 개발한다.
        // 문제점 1) 느리다. 2) 불편하다. ---> 그래도 반드시 사용해야 한다.

        // 생성
        System.out.println(new BigDecimal(0.01)); // 0.01000000000000000020816681711721685132943093776702880859375

        System.out.println(BigDecimal.valueOf(0.01)); // 0.01
        System.out.println(new BigDecimal("0.01")); // 0.01

        // ===============값 비교====================
        BigDecimal c = new BigDecimal("2.01");
        BigDecimal d = new BigDecimal("2.010");

        // == 연산자는 레퍼런스 값을 비교하기 때문에 값을 비교하지 않는다.
        if (c == d) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }

        // equals는 2.01과 2.010을 다른 값으로 본다.
        if (c.equals(d)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }

        // compareTo는 2.01과 2.010을 같은 값으로 본다.
        BigDecimal e = new BigDecimal("2.01");
        BigDecimal f = new BigDecimal("2.010");
        switch (e.compareTo(f)) {
        case 0:
            System.out.println("두 값이 같습니다.");
            break;
        case 1:
            System.out.println("e가 f보다 값이 크다.");
            break;
        case -1:
            System.out.println("e가 f보다 값이 작다.");
            break;
        default:
            System.out.println("오류");
            break;
        }

        // 사칙연산
        BigDecimal g = new BigDecimal("10");
        BigDecimal h = new BigDecimal("3");

        System.out.println("더하기 : " + g.add(h));
        System.out.println("빼기 : " + g.subtract(h));
        System.out.println("곱하기 : " + g.multiply(h));
        // java.lang.ArithmeticException: Non-terminating decimal expansion; no exact
        // representable decimal result.
        // System.out.println("나누기 : " + g.divide(h)); // 아무리 BigDecimal이라고 해도 소수점 몇
        // 자리까지 계산할지 정해야 한다.

        // 반올림 정책 결정
        System.out.println(g.divide(h, RoundingMode.HALF_EVEN)); // 3
        // System.out.println(g.divide(h, 0, RoundingMode.HALF_EVEN)); // 3
        System.out.println(g.divide(h, 6, RoundingMode.HALF_EVEN)); // 3.333333

        // 전체 자리수 제한 후 반올림
        System.out.println(g.divide(h, MathContext.DECIMAL32)); // 전체 자리수 7개 제한 후 반올림 (3.333333)
        System.out.println(g.divide(h, MathContext.DECIMAL64)); // 전체 자리수 16개로 제한 후 반올림 (3.333333333333333)
        System.out.println(g.divide(h, MathContext.DECIMAL128)); // 전체 자리수 34개로 제한하고 반올림
                                                                 // (3.333333333333333333333333333333333)

        // 생성 시 빅데시멀 소수점 지정
        // 소수점 이하 절사
        System.out.println(new BigDecimal("1.1234567890").setScale(0, RoundingMode.FLOOR)); // 1
        // 소수점 이하 절사 후 1 증가
        System.out.println(new BigDecimal("1.1234567890").setScale(0, RoundingMode.CEILING)); // 2
        // 음수에서 소수점 이하 절사
        System.out.println(new BigDecimal("-1.1234567890").setScale(0, RoundingMode.CEILING)); // -1
        // 소수부에서 0제거
        System.out.println(new BigDecimal("0.99990").stripTrailingZeros()); // 0.9999

        // 소수점 자리수 재정의
        // System.out.println(new BigDecimal("0.1234").setScale(3)); //
        // java.lang.ArithmeticException: Rounding necessary (원래 소수점 자리수 보다 적으면 발생)
        System.out.println(new BigDecimal("0.1234").setScale(3, RoundingMode.HALF_EVEN)); // 0.123 (반올림 정책을 적으면 에러 발생
                                                                                          // 안함.)
        // 소수점을 남기지 않고 반올림
        System.out.println(new BigDecimal("0.1234").setScale(0, RoundingMode.HALF_EVEN)); // 0
        System.out.println(new BigDecimal("0.9876").setScale(0, RoundingMode.HALF_EVEN)); // 1
    }
}