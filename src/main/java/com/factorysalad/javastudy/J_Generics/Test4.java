package com.factorysalad.javastudy.J_Generics;

/*
제네릭 메소드는 매개 타입과 리턴 타입으로 타입 파라미터를 갖는 메소드를 맗나다.
구현을 하기 위해선 리턴 타입 앞에 <> 기호를 추가하고
 */
public class Test4 {
    public static void main(String[] args) {
        Pack<Integer> pack1 = Util.<Integer>packing(100);
        int intValue = pack1.getT();

        Pack<String> pack2 = Util.packing("암묵적호출");
        String stringValue = pack2.getT();

        System.out.println("intValue : " + intValue + "\nstringValue : " + stringValue);
    }
}

class Pack<T> {
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}

class Util {
    public static <T> Pack<T> packing(T t) {
        Pack<T> pack = new Pack<T>();
        pack.setT(t);
        return pack;
    }
}

class Util2 {
    public static <T> Pack<T> packing(T t) {
        Pack<T> pack = new Pack<T>();
        pack.setT(t);
        return pack;
    }
}
