package com.factorysalad.javastudy.D_Pattern_Exception;

/**
 * LionAdapter
 */
public class LionAdapter implements Quackable {
    private Crying lion;

    // 디펄트 생성자 
    // 지금까지 new LionAdapter(); 이렇게 사용한 것은 디펄트 생성자를 호출 한 것이다.  
    public LionAdapter() {

    }
    // 오버로딩 된 생성자 (인자가 다르면 여러개 만들 수 있다.)
    public LionAdapter(Crying lion){
        // this는 설계상의 문법으로 앞으로 Heap에 생성될 LionAdapter 주소에 .찍고 변수에 접근하는 것이다. (같은 클래스 내에서는 접근제한이 필요없다.)
        this.lion = lion;
    }

    @Override
    public String quick() {
        return lion.cry();
    }
}