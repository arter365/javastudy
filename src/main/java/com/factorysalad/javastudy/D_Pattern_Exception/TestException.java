package com.factorysalad.javastudy.D_Pattern_Exception;

/*
프로그램 오류
1) 컴파일 에러(compile-time error) : 컴파일 할 때 발생하는 에러

2) 런타임 에러(runtime error) : 실행 할 때 발생하는 에러 -> 프로그램 종료 (예 : ArrayIndexOutOfBoundsException)
    * 에러는 어떨 수 없지만, 예외는 처리하자.
    - 에러(error) : 프로그램 코드에 의해서 수습될 수 없는 심각한 오류. (예 : OutOfMemory error)
    - 예외(exception) : 프로그램 코드에 의해서 수습될 수 있는 다소 미약한 오류.
        - uhecked 예외(Exception) : 컴파일러가 예외처리 에부를 체크함. 예외 처리(try-catch) 필수. (예 : throw new Exception();)
        - unchecked 예외(RuntimeException) : 컴파일러가 예외 처리 여부를 체크하지 않음. 예외처리 선택. (예 : throw new RuntimeException();)
            예외처리(exception handling)
            - 정의 : 프로그램 실행 시 발생할 수 있는 예외의 발생에 대비한 코드를 작성하는 것.
            - 목적 : 프로그램의 비정상 종료를 막고, 정상적인 실행상태를 유지하는 것.

3) 논리적 에러(logical error) : 작성 의도와 다르게 동작 -> 프로그램 종료되지 않는다.
*/

/*
예외정보 출력 :
printStackTrace() : 예외발생 당시의 호출스택(Call Stack)에 있었던 메서드의 정보와 예외 메시지를 화면에 출력한다.
getMessage() : 발생한 예외클래스의 인스턴스에 저장된 메시지를 얻을 수 있다.
 */

public class TestException extends Exception{

    public TestException() {
        // 상속받은 Exception의 생성자 중 하나를 호출하기 위해 super를 사용하였다. 
        super("테스트 exception 입니다.");
    }
}