package com.tayyabsa.diamondproblem;

public class DiamondProblem implements B,C{


    @Override
    public void method() {
        B.super.method();
        C.super.method();
        //cant call A's method
    }
}

interface A{

    default void method(){
        System.out.println("Hello from A");
    }
}

interface B extends A{
    default void method(){
        System.out.println("Hello from B");
    }
}

interface C extends A{
    default void method(){
        System.out.println("Hello from C");
    }
}
