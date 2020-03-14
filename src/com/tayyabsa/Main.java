package com.tayyabsa;

import com.tayyabsa.abstraction.AbstractionConstants;
import com.tayyabsa.abstraction.Tiffin;
import com.tayyabsa.designPatterns.Singleton.Singleton;
import com.tayyabsa.diamond.problem.DiamondProblem;
import com.tayyabsa.inheritance.Abstract;
import com.tayyabsa.inheritance.Child;
import com.tayyabsa.inheritance.ChildTwo;
import com.tayyabsa.inheritance.Parent;
import com.tayyabsa.passbyvalue.A;
import com.tayyabsa.passbyvalue.CheckPassByValue;

public class Main {

    public static void main(String[] args) {


        System.out.println("*******************Inheritance*************************");
        // inheritance
        Child child = new Child();

        System.out.println("******************************Polymorphism**************");
        //polymorphism
        Parent parent = new ChildTwo();
        System.out.println("*********************************Abstract class***********");

        //Abstract in action
        Abstract abs = new ChildTwo();

        System.out.println("**************************Interface******************");

        Tiffin tifin = new Tiffin();
        tifin.addEatables(AbstractionConstants.APPLE);
        tifin.addEatables(AbstractionConstants.BANANA);
        tifin.addEatables(AbstractionConstants.CHEESE_PIZZA);
        tifin.addEatables(AbstractionConstants.PEPPERONI_PIZZA);

        System.out.println("***********************Diamond Problem*********************");

        DiamondProblem diamondProblem = new DiamondProblem();
        diamondProblem.method();
        System.out.println("***********************Singleton*********************");
        Singleton.getInstance();

        System.out.println("***********************Check Pass By value*********************");
        A a = new A();
        a.setTestData(1);
        CheckPassByValue checkPassByValue = new CheckPassByValue();
        System.out.println("Value before the method called :: " + a.getTestData());
        checkPassByValue.testPassByValueForObject(a);
        System.out.println("Value after the method called :: " + a.getTestData());

        int data = 1;
        System.out.println("Value before the method called :: " + data);
        checkPassByValue.testPassByValueForLocalVariable(data);
        System.out.println("Value after the method called :: " + data);

        checkPassByValue.setA(1);
        System.out.println("Value before the method called :: " + checkPassByValue.getA());
        checkPassByValue.testPassByValueForInstanceVariable();
        System.out.println("Value after the method called :: " + checkPassByValue.getA());


    }
}
