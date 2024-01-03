package com.example.pizzabypatel.pizzastore.pizza;

import com.example.pizzabypatel.R;
import com.example.pizzabypatel.pizzastore.pizza.enums.Sauce;
import com.example.pizzabypatel.pizzastore.pizza.enums.Size;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This is an ADT to represent a Build Your Own Pizza
 * @author Dharmik Patel and Krish Patel
 */
public class BuildYourOwn extends Pizza{
    private static double BASE_PRICE = 8.99;
    public static int MIN_NUM_TOPPING = 3;
    public static int MAX_NUM_TOPPING = 7;

    /**
     * This protected constructor can only be
     * called by the PizzaMaker class
     * to make a Build Your Own Pizza
     */
    protected BuildYourOwn(){
        extraSauce = false;
        extraCheese = false;
        imgID = R.drawable.byopizza;
        size = Size.SMALL;
        sauce = Sauce.TOMATO;
        toppings = new ArrayList<>();
    }

    /**
     * This implements the price() method from the abstract pizza class
     * @return The price of the pizza
     */
    @Override
    public double price() {
        double subtotal = 0;
        if(extraCheese)
            subtotal += EXTRA_CHEESE_OR_SAUCE_PRICE;
        if(extraSauce)
            subtotal += EXTRA_CHEESE_OR_SAUCE_PRICE;
        if(toppings.size() > 3)
            subtotal += (numOfTopping() - MIN_NUM_TOPPING) *
                    PER_TOPPING_NET_PRICE;
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
