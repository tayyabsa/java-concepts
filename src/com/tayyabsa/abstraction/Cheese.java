package com.tayyabsa.abstraction;

public class Cheese implements Pizza {

    public int cheeseSlices;

    public Cheese(int cheeseSlices) {
        System.out.println("Cheese pizza constructor::");
        this.cheeseSlices = cheeseSlices;
    }
}
