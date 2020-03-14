package com.tayyabsa.testPackage;

import com.tayyabsa.inheritance.Parent;

public class TestClass extends Parent {

    String testClassData;

    @Override
    public void method() {
        super.method();
    }

    @Override
    protected void protectedMethod() {
        super.protectedMethod();
    }
}
