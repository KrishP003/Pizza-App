package com.example.pizzabypatel.pizzastore.pizza;

import com.example.pizzabypatel.R;
import com.example.pizzabypatel.pizzastore.pizza.enums.Sauce;
import com.example.pizzabypatel.pizzastore.pizza.enums.Size;
import com.example.pizzabypatel.pizzastore.pizza.enums.Topping;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is an ADT to represent a Primavera Pizza
 * @author Dharmik Patel and Krish Patel
 */
public class Primavera extends Pizza{
    private static double BASE_PRICE = 20.99;

    /**
     * This protected constructor can only be
     * called by the PizzaMaker class
     * to make a Deluxe Pizza
     */
    protected Primavera(){
        extraSauce = false;
        extraCheese = false;
        size = Size.SMALL;
        imgID = R.drawable.primva;

        toppings = new ArrayList<>(
                Arrays.asList(
                        Topping.GREEN_PEPPER,
                        Topping.MUSHROOM,
                        Topping.ZUCCHINI,
                        Topping.MOZZARELLA,
                        Topping.CHERRY_TOMATOES));
        sauce = Sauce.ALFREDO;
    }
    /**
     * This implements the price() method
     * from the abstract pizza class
     * @return The price of the pizza
     */
    @Override
    public double price() {
        double subtotal = 0;
        if(extraCheese)
            subtotal += EXTRA_CHEESE_OR_SAUCE_PRICE;
        if(extraSauce)
            subtotal += EXTRA_CHEESE_OR_SAUCE_PRICE;
        subtotal +=  BASE_PRICE + size.getPriceAdd();
        return subtotal;
    }
    /**
     * This returns a human-readable version of the pizza with
     * Pizza Type, Topping List, Size, Sauce[Extra if applicable], Cost
     * @return a string version of the pizza
     */
    @Override
    public String toString() {
        return String.format("[%s] %s, %s, %s, %s%s%s",
                this.getClass().getSimpleName(),
                toppings.toString()
                        .replace("[","")
                        .replace("]", ""),
                size.toString(),
                sauce.toString(),
                extraSauce ? "Extra Sauce, " : "",
                extraCheese ? "Extra Cheese, " : "",
                new DecimalFormat("#,##0.00").format(price())
        );
    }
}