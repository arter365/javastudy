package com.factorysalad.javastudy.M_MethodReferences;

public class Greething {
    private String name;

    public Greething() {
        this.name = "hi";
    }

    public Greething(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String hello(String name) {
        return "hello " + name;
    }

    public static String hi(String name) {
        return "hi " + name;
    }

}
