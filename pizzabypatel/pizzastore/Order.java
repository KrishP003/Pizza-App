package com.example.pizzabypatel.pizzastore;

import com.example.pizzabypatel.pizzastore.pizza.Pizza;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * This ADT represents a restaurant order
 * @author Dharmik Patel and Krish Patel
 */
public class Order {
    private static double NJ_SALES_TAX_PERCENT = 6.625;
    private ArrayList<Pizza> pizzas;
    private int orderNumer;

    /**
     * This constructor makes an empty order with
     * the next available order number
     */
    public Order(){
        pizzas = new ArrayList<>();
        orderNumer = StoreOrder.NEXT_ORDER_NUMBER++;
    }

    /**
     * Returns the numbers of pizza in the current order
     * @return num of pizzas
     */
    public int getNumOfPizzas(){
        return pizzas.size();
    }

    /**
     * This returns a list of pizzas as strings to be used in
     * observable lists
     * @return The list of pizzas as a list of strings
     */
    public ArrayList<String> getPizzasAsString() {
        List<String> list = pizzas.stream()
                .map(Pizza::toString)
                .toList();
        return new ArrayList<>(list);
    }

    /**
     * This returns the order number of the current order
     * @return Order number
     */
    public int getOrderNumber() {
        return orderNumer;
    }

    /**
     * This adds a pizza to the order
     * @param pizza Pizza to add
     */
    public void addPizza(Pizza pizza){
        pizzas.add(pizza);
    }

    /**
     * This removes a pizza from the order
     * @param selectedIndex The index of the pizza to remove
     */
    public void removePizza(int selectedIndex){
        pizzas.remove(selectedIndex);
    }

    /**
     * This cost of all the pizzas
     * @return The cost of all the pizzas
     */
    public double subTotal(){
        double subTotal = 0;
        for(Pizza pizza : pizzas){
            subTotal += pizza.price();
        }
        return subTotal;
    }

    /**
     * The tax on the order subjugated by the NJ Government
     * @return The tax amount
     */
    public double tax(){
        return subTotal() * NJ_SALES_TAX_PERCENT / 100;
    }

    /**
     * Returns the total cost the patron pays.
     * @return subTotal + tax
     */
    public double orderTotal(){
        return subTotal() + tax();
    }

    /**
     * Gets the pizzas
     * @return pizzas
     */
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * This prints an order with order number and pizzas, and total cost.
     * @return The order
     */
    @Override
    public String toString() {
        String order = "Order Number: " + orderNumer + "\n";
        for(Pizza pizza : pizzas){
            order += "\t" + pizza.toString() + "\n";
        }
        order += "Sub Total: $" + new DecimalFormat(
                "#,##0.00").format(subTotal()) + "\n";
        order += "Tax: $" + new DecimalFormat(
                "#,##0.00").format(tax()) + "\n";
        order += "Order Total: $" + new DecimalFormat(
                "#,##0.00").format(orderTotal()) + "\n";
        return order;
    }
}
