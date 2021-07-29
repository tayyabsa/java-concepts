package com.tayyabsa.testPackage;

import com.tayyabsa.designPatterns.Factory.JuiceFactory;
import com.tayyabsa.inheritance.Parent;

import java.util.Objects;

public class TestClass extends Parent implements Cloneable{

    public int testClassData = 15;

    public JuiceFactory juiceFactory;

    public TestClass() {
        this.juiceFactory = new JuiceFactory();
    }

    @Override
    public void method() {
        super.method();
    }

    @Override
    protected void protectedMethod() {
        super.protectedMethod();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestClass testClass = (TestClass) o;
        return testClassData == testClass.testClassData && Objects.equals(juiceFactory, testClass.juiceFactory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testClassData, juiceFactory);
    }
}
