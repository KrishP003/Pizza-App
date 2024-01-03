package com.example.pizzabypatel.pizzastore.pizza;
/**
 * This ADT is a pizza factory class
 * @author Dharmik Patel and Krish Patel
 */
public class PizzaMaker {

    /**
     * This is a factory static method to make any type of pizza.
     * @param pizzaType the type of the pizza
     * @return A pizza of the given type
     */
    public static Pizza createPizza(String pizzaType) {
        if(pizzaType.equals(Deluxe.class.getSimpleName())){
            return new Deluxe();
        } else if (pizzaType.equals(Supreme.class.getSimpleName())) {
            return new Supreme();
        } else if (pizzaType.equals(Meatzza.class.getSimpleName())) {
            return new Meatzza();
        } else if(pizzaType.equals(Pepperoni.class.getSimpleName())){
            return new Pepperoni();
        } else if (pizzaType.equals(Seafood.class.getSimpleName())) {
            return new Seafood();
        } else if (pizzaType.equals(BuildYourOwn.class.getSimpleName())) {
            return new BuildYourOwn();
        } else if (pizzaType.equals(Buffalo.class.getSimpleName())) {
            return new Buffalo();
        } else if (pizzaType.equals(Cheese.class.getSimpleName())) {
            return new Cheese();
        } else if (pizzaType.equals(Mediterranean.class.getSimpleName())) {
            return new Mediterranean();
        } else if (pizzaType.equals(Pesto.class.getSimpleName())) {
            return new Pesto();
        } else if (pizzaType.equals(Primavera.class.getSimpleName())) {
            return new Primavera();
        }
        return null;
    }
}
