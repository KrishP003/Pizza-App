package com.example.pizzabypatel.pizzastore.pizza.enums;

import java.util.ArrayList;

/**
 * This is an enum class that holds the size types
 * @author Dharmik Patel and Krish Patel
 */
public enum Size {
    SMALL("Small", 0.0),
    MEDIUM("Medium", 2.0),
    LARGE("Large", 4.0);
    private final String name;
    private final double price_add;

    /**
     * This is only called by JVM to make the enums
     * @param name The proper name of the size
     * @param price_add The price jump for each size
     */
    private Size(String name, Double price_add){
        this.name = name;
        this.price_add = price_add;
    }

    /**
     * This is the overided tostirng method 
     * @return the proper name of the size
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * This returns a list of strings of the sizes to use in
     * list view
     * @return list of sizes as strings
     */
    public static ArrayList<String> getSizes(){
        ArrayList<String> listOfSizes = new ArrayList<String>();

        for(Size s : Size.values()){
            listOfSizes.add(s.toString());
        }
        return listOfSizes;
    }

    /**
     * This is the price jump
     * @return The price jump per size
     */
    public double getPriceAdd() {
        return price_add;
    }
}
