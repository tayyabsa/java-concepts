package com.tayyabsa.abstraction;

import java.util.ArrayList;
import java.util.List;

public class Tiffin {

    List<Eatable> eatables;

    {
        System.out.println("Tiffin Code block called");
        eatables = new ArrayList<>();
    }

    public void addEatables(String eatable) {

        switch (eatable) {
            case AbstractionConstants.APPLE:
                eatables.add(new Apple(2));
                break;
            case AbstractionConstants.BANANA:
                eatables.add(new Banana(2));
                break;

            case AbstractionConstants.CHEESE_PIZZA:
                eatables.add(new Cheese(2));
                break;
            case AbstractionConstants.PEPPERONI_PIZZA:
                eatables.add(new Pepperoni(2));
                break;
        }
    }
}
