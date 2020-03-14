package com.tayyabsa.abstraction;

public class Pepperoni implements Pizza {

    public int pepperonies;

    public Pepperoni(int pepperonies) {
        System.out.println("Pepperoni pizza constructor::");
        this.pepperonies = pepperonies;
    }
}
