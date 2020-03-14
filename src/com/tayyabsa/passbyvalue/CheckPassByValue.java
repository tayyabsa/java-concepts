package com.tayyabsa.passbyvalue;

public class CheckPassByValue {

    int a;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void testPassByValueForObject(A a) {
        a.setTestData(10);
    }

    public void testPassByValueForLocalVariable(int a) {
        a = 10;
    }

    public void testPassByValueForInstanceVariable() {
        a = 10;
    }
}
