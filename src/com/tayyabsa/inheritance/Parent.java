package com.tayyabsa.inheritance;

public class Parent extends Abstract{

    private String parentData;

    @Override
    public void abstractMethod() {
        System.out.println("Overridden method in Parent class from Abstract class");
    }

    public Parent(){
        System.out.println("Parent constructor Called");
    }

    public void method(){
        System.out.println("Parent Method");
    }

    public static void staticMethod(){
        System.out.println("Parent static method");
    }

    private void privateMethod(){
        System.out.println("Private Parent method");
    }

    protected void protectedMethod(){
        System.out.println("Protected Parent method");
    }

    void defaultMethod(){
        System.out.println("default Parent method");
    }

}
