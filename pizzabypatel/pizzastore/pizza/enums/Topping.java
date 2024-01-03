package com.example.pizzabypatel.pizzastore.pizza.enums;

import java.util.ArrayList;

/**
 * This is an enum class that holds the size types
 * @author Dharmik Patel and Krish Patel
 */
public enum Topping {
    SAUSAGE ("Sausage"),
    PEPPERONI ("Pepperoni"),
    GREEN_PEPPER ("Green Pepper"),
    ONION ("Onion"),
    MUSHROOM ("Mushroom"),
    HAM ("Ham"),
    BLACK_OLIVE ("Black Olive"),
    BEEF ("Beef"),
    SHRIMP ("Shrimp"),
    SQUID ("Squid"),
    CRAB_MEAT ("Crab Meat"),
    FETA ("Feta"),
    TOMATOES ("Tomatoes"),
    PESTO ("Pesto"),
    PINEAPPLE ("Pineapple"),
    RED_ONIONS ("Red Onions"),
    ZUCCHINI ("Zucchini"),
    GOUDA_CHEESE ("Gouda Cheese"),
    CILANTRO ("Cilantro"),
    PINE_NUTS ("Pine Nuts"),
    SPINACH_LEAVES("Spinach Leaves"),
    MOZZARELLA("Mozzarella"),
    BLUE_CHEESE("Blue Cheese"),
    CELERY("Celery"),
    BUFFALO_CHICKEN("Buffalo Chicken"),
    CHERRY_TOMATOES ("Cherry Tomatoes");
    private final String name;

    /**
     * This is only used by JVM to make the enums
     * @param name the proper name of the topping
     */
    private Topping(String name){
        this.name = name;
    }

    /**
     * This returns a list of topping as strings
     * to be used in the list views
     * @return list of toppings as strings
     */
    public static ArrayList<String> getToppings(){
        ArrayList<String> listOfToppings = new ArrayList<>();

        for(Topping t : Topping.values()){
            listOfToppings.add(t.toString());
        }
        return listOfToppings;
    }

    /**
     * This is overided toString method
     * @return the proper name of the topping
     */
    @Override
    public String toString() {
        return name;
    }
}
