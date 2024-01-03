package com.example.pizzabypatel.pizzastore.pizza.enums;

/**
 * This is an enum class that holds the sauce types
 * @author Dharmik Patel and Krish Patel
 */
public enum Sauce {
    TOMATO("Tomato"),
    ALFREDO("Alfredo");
    private final String name;

    /**
     * This constructor is only used by JVM to make the enums
     * @param name This is the proper name of the sauce to display
     */
    Sauce(String name){
        this.name = name;
    }

    /**
     * This is a toString method to print the true name of the sauce
     * @return the string
     */
    @Override
    public String toString() {
        return name;
    }
}
