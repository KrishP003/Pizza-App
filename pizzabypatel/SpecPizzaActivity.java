package com.example.pizzabypatel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pizzabypatel.pizzastore.pizza.Pizza;
import com.example.pizzabypatel.pizzastore.pizza.PizzaMaker;

import java.util.ArrayList;

/**
 * Activity for spec pizzas
 * @author Dharmik Patel and Krish Patel
 */
public class SpecPizzaActivity extends AppCompatActivity {
    private ArrayList<Pizza> specPizzas = new ArrayList<>();
    private SpecPizzaAdapter specPizzaAdapter;

    /**
     * Set up the views with find view by id
     * Alos set up the recylver view
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it
     *     most recently supplied in {@link #onSaveInstanceState}.  <b><i
     *     >Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spec_pizza);
        RecyclerView recyclerView = findViewById(R.id.recylcerview);
        setUpPizzas();

        specPizzaAdapter = new SpecPizzaAdapter(this, specPizzas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(specPizzaAdapter);
    }


    /**
     * Makes the list of spec pizza, and adds all of them
     */
    private void setUpPizzas(){
        specPizzas.add(PizzaMaker.createPizza("Deluxe"));
        specPizzas.add(PizzaMaker.createPizza("Meatzza"));
        specPizzas.add(PizzaMaker.createPizza("Pepperoni"));
        specPizzas.add(PizzaMaker.createPizza("Seafood"));
        specPizzas.add(PizzaMaker.createPizza("Supreme"));
        specPizzas.add(PizzaMaker.createPizza("Buffalo"));
        specPizzas.add(PizzaMaker.createPizza("Cheese"));
        specPizzas.add(PizzaMaker.createPizza("Mediterranean"));
        specPizzas.add(PizzaMaker.createPizza("Pesto"));
        specPizzas.add(PizzaMaker.createPizza("Primavera"));
    }
}