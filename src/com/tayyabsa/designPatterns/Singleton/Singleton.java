package com.tayyabsa.designPatterns.Singleton;

public class Singleton {

    private static Singleton singleton = null;
    private int a;
    private int b;

    private Singleton(){
    }

    private Singleton(int a, int b) {
        this.a = a;
        this.b = b;
    }


    public static Singleton getInstance(){
        if(singleton==null)
            return new Singleton();

        return singleton;
    }
}
