package com.tayyabsa.inheritance;


public abstract class Abstract {

    public static String DATA = "data";

    public String abstractData = "abstract Data";
    public static final String abstractData1 = "abstract Data";

    public int stringData;

    public abstract void abstractMethod();

    public Abstract() {
        System.out.println("Abstract Class constructor called");
    }

    public Abstract(String abstractData) {
        System.out.println("Abstract Class overloaded constructor called");
        this.abstractData = abstractData;
    }

    public void abstractClassMethod(){
        System.out.println("Abstract Class method");
    }

    public static void staticMethodInAbstractClass(){
        System.out.println("static data");
    }
}
