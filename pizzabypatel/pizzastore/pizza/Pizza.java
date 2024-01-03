package com.example.pizzabypatel.pizzastore.pizza;

import com.example.pizzabypatel.pizzastore.pizza.enums.Sauce;
import com.example.pizzabypatel.pizzastore.pizza.enums.Size;
import com.example.pizzabypatel.pizzastore.pizza.enums.Topping;

import java.util.ArrayList;
import java.util.List;

/**
 * This ADT is a template for all pizza types
 * @author Dharmik Patel and Krish Patel
 */
public abstract class Pizza {
    protected static double PER_TOPPING_NET_PRICE = 1.49;
    protected static double EXTRA_CHEESE_OR_SAUCE_PRICE = 1;

    protected ArrayList<Topping> toppings;
    protected int imgID;

    protected Size size;
    protected Sauce sauce;
    protected boolean extraSauce;
    protected boolean extraCheese;

    /**
     * Must be implemented in subclasses
     * @return The price of the pizza
     */
    public abstract double price();

    /**
     * This method is used by all subclasses to
     * set the size of the pizza
     * @param size The size of the pizza {Small, Medium, Large}
     */
    public void setSize(Size size) {
        this.size = size;
    }
    /**
     * This method is used by all subclasses, except BYO to
     * set if the patron wants extra sauce
     * @param extraSauce True: If the user wants extra sauce, false if not.
     */
    public void setExtraSauce(boolean extraSauce) {
        this.extraSauce = extraSauce;
    }
    /**
     * This method is used by all subclasses, except BYO to
     * set if the patron wants extra cheese
     * @param extraCheese True: If the user wants extra cheese, false if not.
     */
    public void setExtraCheese(boolean extraCheese) {
        this.extraCheese = extraCheese;
    }

    /**
     * Gets the image
     * @return image
     */
    public int getImgID() {
        return imgID;
    }

    /**
     * This returns the topping list as a list of strings
     * to be used in an observable list.
     * @return A list of string toppings
     */
    public List<String> getToppingsAsString() {
        List<String> list = toppings.stream()
                .map(Topping::toString)
                .toList();
        return list;
    }

    /**
     * THis gets comma sep toppings
     * @return comma sep topping
     */
    public String getToppingsSepByCommas() {
        return String.join(", ", getToppingsAsString());
    }
    /**
     * THis gets comma sep extras
     * @return comma sep extras
     */
    public String getExtrasSepByCommas() {
        List<String> extras = new ArrayList<>();
        if(extraCheese) extras.add("Extra Cheese");
        if(extraSauce) extras.add("Extra Sauce");
        return String.join(", ", extras);
    }

    /**
     * Gets the size
     * @return size
     */
    public Size getSize() {
        return size;
    }

    /**
     * This getter method returns the sauce
     * @return the sauce of the pizza {Tomato, Alfredo}
     */
    public Sauce getSauce() {
        return sauce;
    }


    /**
     * This method adds a topping to the topping list
     * The min and max amount of topping must be checked by the caller
     * @param topping The topping to add on pizza
     */
    public void addTopping(Topping topping){
        toppings.add(topping);
    }
    /**
     * This method removes a topping to the topping list
     * The min and max amount of topping must be checked by the caller
     * @param topping The topping to remove on pizza
     */
    public void rmvTopping(Topping topping){
        toppings.remove(topping);
    }

    /**
     * This returns the number of topping on the pizza
     * @return Number of topping on the pizza
     */
    public int numOfTopping(){
        return toppings.size();
    }

    /**
     * This sets the sauce type
     * @param sauce The sauce type to make the pizza
     */
    public void setSauce(Sauce sauce){
        this.sauce = sauce;
    }
}
