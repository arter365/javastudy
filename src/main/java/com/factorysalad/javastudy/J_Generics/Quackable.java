package com.factorysalad.javastudy.J_Generics;

public interface Quackable  {
    // 인터페이스는 설계도 이다. 그렇기 때문에 변하지 않는 상수 값은 넣을 수 있다. 
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    public String quick(); //디자인 원칙 : 애플리케이션에서 달라지는 부분을 찾아내고, 달라지지 않는 부분으로부터 분리시킨다. 
}