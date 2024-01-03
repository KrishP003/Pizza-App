package com.example.pizzabypatel;

import com.example.pizzabypatel.pizzastore.Order;
import com.example.pizzabypatel.pizzastore.StoreOrder;

/**
 * This ADT holds the singleton data
 * @author Dharmik Patel and Krish Patel
 */
public final class GlobalPizzaData {
    private static GlobalPizzaData globalData;
    private static StoreOrder storeOrder;
    private static Order currOrder;

    /**
     * private constructor for the data
     */
    private GlobalPizzaData() {
        storeOrder = new StoreOrder();
        currOrder = new Order();
    }

    /**
     * THis is what client uses to get the global data. maeks sure there
     * is only 1
     * @return data obj
     */
    public static synchronized GlobalPizzaData getInstance() {
        if (globalData == null){
            globalData = new GlobalPizzaData();
        }
        return globalData;
    }

    /**
     * This gets the store order
     * @return storeorder
     */
    public synchronized StoreOrder getStoreOrder() {
        return storeOrder;
    }

    /**
     * This gets the curr order
     * @return curr order
     */
    public synchronized Order getCurrOrder() {
        return currOrder;
    }

    /**
     * This adanvces curr store order by 1
     * call after order has been placed
     * @return ref to new order
     */
    public synchronized Order resetCurrOrder() {
        currOrder = new Order();
        return currOrder;
    }

}
