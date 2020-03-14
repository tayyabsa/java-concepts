package com.tayyabsa.inheritance;

public class Child extends Parent {

    private String childData;

    public Child(){
        System.out.println("Child constructor called");
    }

    @Override
    protected void protectedMethod() {
        super.protectedMethod();
    }

    @Override
    public void method() {
        super.method();
    }

    @Override
    void defaultMethod() {
        super.defaultMethod();
        Parent.staticMethod();
    }

    public static void staticMethod(){
        System.out.println("Parent static method");
    }


}
