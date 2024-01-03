package com.example.pizzabypatel.pizzastore;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This ADT holds all the current orders in the restaurant
 * @author Dharmik Patel and Krish Patel
 */
public class StoreOrder {
    static int NEXT_ORDER_NUMBER = 1;
    private ArrayList<Order> orders;

    /**
     * This constructor makes the store
     */
    public StoreOrder(){
        orders = new ArrayList<>();
    }



    /**
     * Returns all the order numbers in use
     * @return All the order numbers
     */
    public List<String> getOrderNumbersAsListOfStrings(){
//        List<String> list = orders.stream()
//                .map(order -> order.getOrderNumber() + "")
//                .toList();
//        return list;
        List<String> ordernums = new ArrayList<>();
        for(Order o : orders){
            ordernums.add(o.getOrderNumber() + "");
        }
        return ordernums;
    }

    /**
     * This adds an order to the store queue
     * @param order order to add
     */
    public void addOrder(Order order){
        orders.add(order);
    }

    /**
     * This returns the order at the given order number
     * @param orderNumberS the order number of the order wanted
     * @return the orders
     */
    public Order getOrder(String orderNumberS) {
        if(orderNumberS.isEmpty()) return null;
        int orderNumber = Integer.parseInt(orderNumberS);
        for (Order order : orders) {
            if (order.getOrderNumber() == orderNumber) {
                return order;
            }
        }
        return null;
    }
    /**
     * This removes an order
     * @param orderNumberS the order number of the order to remove
     */
    public void removeOrder(String orderNumberS) {
        int orderNumber = Integer.parseInt(orderNumberS);
        orders.removeIf(order -> order.getOrderNumber() == orderNumber);
    }
}
