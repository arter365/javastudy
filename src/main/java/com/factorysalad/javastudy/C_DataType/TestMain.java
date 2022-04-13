package com.factorysalad.javastudy.C_DataType;

import java.util.Arrays;
import java.util.Comparator;

/*

[4일차]
(#1 : String, 숫자(원시자료형과 래퍼클래스)) <br>

변수 (variable) : 하나의 값을 저장하기 위한 공간
상수 (constant) : 한 번만 값을 저장 가능한 변수
리터럴 (literal) : 그 자체로 값을 의미하는 것. 아래의 예에서 100, 100, 'A', "abc"가 리터럴이다. (상수와 동일)
                  예) int score = 100 , final int MAX = 100 , char ch = 'A' , String str = "abc"

기본형 (Primitive type) : 실제 값을 저장 -> boolean, char, byte, short, int ,long, float, double (오직 8개)
참조형 (Reference type) : 메모리 주소를 저장 -> String, System등

참고 : StringBuffer는 equals()가 오버라이딩 되어 있지 않다. (주소비교)
      StringBuilder는 StringBuffer와 동일한데 동기화 되어있지 않다. (동기화 : 쓰레드에 안전, 성능저하) 싱글쓰레드일 경우 필요 없음.

비교 인터페이스 (결과 : 0 = 같다 , 양수 = 왼쪽크다 , 음수 = 오른쪽크다)
    Comparable : 기본 정렬기준을 구현하는데 사용. (예 : String, Integer등에 구현되어 있음)
                 int compareTo(Object o);           // 주어진 객체(o)를 자신(this)과 비교.
    Comparator : 기본 정렬기준 외에 다른 기준으로 정렬하고자 할 때 사용.
                 int compare(Object o1, Object o2); // o1과 o2 두 객체를 비교
                 boolean equals(Object obj);        // equals를 오버라이딩하라는 뜻. (중요하지 않다)

*/
public class TestMain {

    public static void main(String[] args) {
        TestMain d4 = new TestMain();
        d4.example();
        d4.compareTest();
    }

    private void example() {
        // String의 생성방식 
        String str1 = new String();
        str1 = "str1";
        String str2 = new String("str2");
        String str3 = "str3";
        // null 방지를 위해 초기값이 없을 경우 아래와 같이 빈 문자열을 생성하는 것이 좋다.
        String strnull = "";
        char ch = ' '; // char은 공백으로 초기화 한다.
        
        // 문자열 비교 시 주의할 점 (String은 재사용 되지 않는다.)
        // 문자열 리터럴은 프로그램 실행 시 자동으로 생성(불변객체)되고 같은 값은 공유한다. (constant pool에 저장된다. : 상수 저장소)
        // new String을 사용하면 같은 내용이라도 공유하지 않는다. (아래는 new를 사용하지 않았기 때문에 리터럴을 공유한다 : 추천)
        String str4 = "asdf";
        String str5 = "asdf";
        if(str4 == str5){
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        String str6 = new String("asdf");
        String str7 = new String("asdf");
        if(str6.toString() == str7.toString()){
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        // 문자열 비교는 .equals()를 사용한다. (재정의 되어 있다)
        if(str6.equals(str7)){
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        //대소문자 변환1
        String str8 = "AaZzDEF";
        
        String res = new String();
        for (int i = 0; i < str8.length(); i++) {
            char temp = str8.charAt(i);
            // 문자를 처리하는 방식은 형을 보고 Ascii 테이블에서 그 값을 찾는다. 
            System.out.println(temp);
            System.out.println((int) temp);
            int tmp = (int)temp;
            if (tmp >=97 && tmp <= 122){
                tmp -= 32;
                res += (char)tmp;
            } else {
                res += temp;
            }
        }
        System.out.println(res);
        // 대소문자 변환2
        System.out.println(str8.toUpperCase());


        // indexOf , subString
        String str9 = new String("abcdefg");
        System.out.println(str9.substring(str9.indexOf('c'), 6)); // "cdef" 출력
        System.out.println(str9.substring(5)); // "fg" 출력 (인덱스 값 이후로 출력)

        // split
        String str = "Afasd,Bfd,C,Der";
        String[] array = str.split(",");			
        for(int i=0;i<array.length;i++) {
            System.out.println(array[i]);
        }

        // Wrapper, 원시자료형
        // Number(모든 숫자 래퍼 클래스의 조상) : Byte, Short, Integer, Long, Float, Double, BigInteger, BigDecimal의 조상은 Number이다.
        Integer i1 = Integer.valueOf("12"); // 자기형 또는 String이 들어간다. set이 없다.
        int i = i1.intValue();
        System.out.println(Integer.MAX_VALUE);

        int aa = 123;
        String tt = String.valueOf(aa);

        // 오토박싱 , 언박싱 
        Integer i2 = Integer.valueOf(10);   // 숫자 , 문자
        Integer i3 = 10; //오토박싱(autoboxing)

        int i4 = i1.intValue();              
        int i5 = i1; //언박싱(unboxing)

        String sss = "123";
        
        int num = Integer.parseInt(sss);

        long ll = 123124;
        int ii = (int)ll;

        int ttt = 123;
        long yyyy = ttt;

        // long의 표현 (-800경 ~ +800경)
        long l1 = 10_000_000_000L;      // java 1.7부터 Long에서 읽기 쉽게 이렇게 적을 수 있다.
        long l2 = 100;                  // 리터럴이 21억을 넘지 않기 때문에 생략이 가능하다.

        // 실수형 리터럴 표현
        double d1 = 10.;                // 10.0
        double d2 = .10;                // 0.10
        double d3 = 1e3;                // 1000.0
        float f1 = 10f;                 // 10.0f
        // 주어진 double 값과 가장 가까운 정수값을 double로 반환
        System.out.println(Math.rint(1.2)); // 1

        // 배열의 표현
        int[] score = new int[5];
        score[3] = 100;
        System.out.println(score.length);   // 배열은 한번 생성하면 그 길이를 바꿀 수 없다.

        int[] iArr = {100, 95, 80, 70, 60}; // 배열을 이렇게도 생성가능.
        String[] strArr = {"가위", "바위", "보"};    // String 배열.

        // 배열의 비교
        int[] arr = {0, 1, 2, 3, 4};
        int[][] arr2D = {{11, 12}, {21, 22}};
        System.out.println(Arrays.toString(arr));       // [0, 1, 2, 3, 4]
        System.out.println(Arrays.deepToString(arr2D)); // [[11, 12], [21, 22]]

        String[][] str2D1 = new String[][]{{"aaa", "bbb"}, {"AAA", "BBB"}};
        String[][] str2D2 = new String[][]{{"aaa", "bbb"}, {"AAA", "BBB"}};
        System.out.println(Arrays.equals(str2D1,str2D2));       // false  레퍼런스값 비교
        System.out.println(Arrays.deepEquals(str2D1, str2D2));  // true   값 비교

        // 배열의 복사
        int[] arr1 = {0, 1, 2, 3, 4};
        int[] arr2 = Arrays.copyOf(arr, arr.length);
        int[] arr3 = Arrays.copyOf(arr, 3);
        int[] arr4 = Arrays.copyOf(arr, 7);
        int[] arr5 = Arrays.copyOfRange(arr, 2, 4);
        int[] arr6 = Arrays.copyOfRange(arr, 0, 7);

        // 배열의 정렬
        int[] arr7 = {3, 2, 0, 1, 4};
        Arrays.sort(arr7);
    }

    private void compareTest() {
        String[] strArr = {"cat", "Dog", "lion", "tiger"};

        Arrays.sort(strArr);    // String의 Comparable 구현에 의한 정렬을 한다.
        System.out.println("strArr1 : " + Arrays.toString(strArr));

        Arrays.sort(strArr, String.CASE_INSENSITIVE_ORDER);    // 대소문자 구분안함.
        System.out.println("strArr2 : " + Arrays.toString(strArr));

        // 오름차순을 위해 Comparator를 익명클래스로 구현함.
        Arrays.sort(strArr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1 instanceof Comparable && o2 instanceof Comparable) {
                    Comparable c1 = (Comparable) o1;
                    Comparable c2 = (Comparable) o2;
                    return c2.compareTo(c1);
                }
                return -1;
            }
        });
        System.out.println("strArr3 : " + Arrays.toString(strArr));

    }
}