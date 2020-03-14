package com.tayyabsa.abstraction;

public class Apple implements Fruits{

    public int kgs;

    public Apple(int kgs) {
        System.out.println("Apple constructor::");
        this.kgs = kgs;
    }
}
