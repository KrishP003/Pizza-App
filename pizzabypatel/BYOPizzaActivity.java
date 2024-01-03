package com.example.pizzabypatel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.pizzabypatel.pizzastore.pizza.BuildYourOwn;
import com.example.pizzabypatel.pizzastore.pizza.Pizza;
import com.example.pizzabypatel.pizzastore.pizza.PizzaMaker;
import com.example.pizzabypatel.pizzastore.pizza.enums.Sauce;
import com.example.pizzabypatel.pizzastore.pizza.enums.Size;
import com.example.pizzabypatel.pizzastore.pizza.enums.Topping;

import java.text.DecimalFormat;
import java.util.Collections;

/**
 * Activity for BYO menu
 * @author Dharmik Patel and Krish Patel
 */
public class BYOPizzaActivity extends AppCompatActivity {
    private ObservableList<String> allToppings;
    ArrayAdapter<String> items;
    private Pizza currentPizza;
    private ListView allIndyLV;
    private Button addToOrderBtn;
    private CheckBox extraSauceCB;
    private CheckBox extraCheeseCB;
    private RadioButton smallRB;
    private RadioButton largeRB;
    private RadioButton mediumRB;
    private RadioButton tomatoRB;
    private RadioButton alfredoRB;
    private TextView priceTV;



    /**
     * This on create method sets up all the views by findviewbyid
     * and also the listeners
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.
     *     <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_byopizza);
        allIndyLV = findViewById(R.id.allIngrLV);
        addToOrderBtn = findViewById(R.id.BYO_addToOrder);
        extraCheeseCB = findViewById(R.id.BYO_extraCheese);
        extraSauceCB = findViewById(R.id.BYO_extraSauce);
        smallRB = findViewById(R.id.BYO_smallBtn);
        mediumRB = findViewById(R.id.BYO_mediumBtn);
        largeRB = findViewById(R.id.BYO_largeBtn);
        tomatoRB = findViewById(R.id.BYO_tomatoBtn);
        alfredoRB = findViewById(R.id.BYO_alfredoBtn);
        priceTV = findViewById(R.id.BYO_price);

        //set up observable list
        currentPizza = PizzaMaker.createPizza("BuildYourOwn");
        currentPizza.setSize(Size.SMALL);
        currentPizza.setSauce(Sauce.TOMATO);

        smallRB.setSelected(true);
        tomatoRB.setSelected(true);

        allToppings = new ObservableArrayList<String>();
        Collections.addAll(allToppings, Topping.getToppings().
                toArray(new String[0]));
        items = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, allToppings);
        allIndyLV.setAdapter(items);
        allIndyLV.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        allIndyLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                addToppings(parent, position);
            }
        });

        updatePrice();
    }

    /**
     * AddToppings method adds toppings to the pizza and updates prices
     * accordingly
     * @param parent
     * @param position
     */
    private void addToppings(AdapterView<?> parent, int position)
    {
        if(allIndyLV.isItemChecked(position))
        {
            if(currentPizza.numOfTopping() < BuildYourOwn.MAX_NUM_TOPPING)
            {
                String selectedIndy = parent.getItemAtPosition(position).
                        toString();
                currentPizza.addTopping(Topping.valueOf(selectedIndy.
                        toUpperCase()
                        .replace(" ", "_")));
                updatePrice();
            }
            else
            {
                Toast.makeText(this, "At most 7 toppings!",
                        Toast.LENGTH_LONG).show();
                allIndyLV.setItemChecked(position, false);
            }
        }
        else
        {
            String selectedIndy = parent.getItemAtPosition(position).
                    toString();
            currentPizza.rmvTopping(Topping.valueOf(selectedIndy.toUpperCase()
                    .replace(" ", "_")));
            updatePrice();
        }
    }

    /**
     * addToOrderBtnOnPress method adds the pizza to the current orders
     * @param view
     */
    public void addToOrderBtnOnPress(View view){
        if(currentPizza.numOfTopping() < BuildYourOwn.MIN_NUM_TOPPING) {
            Toast.makeText(this, "Minimum 3 Toppings Needed",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if(currentPizza.numOfTopping() > BuildYourOwn.MAX_NUM_TOPPING)
        {
            Toast.makeText(this, "At most 7 toppings!",
                    Toast.LENGTH_LONG).show();
            return;
        }
        GlobalPizzaData.getInstance().getCurrOrder().addPizza(currentPizza);
        resetUI();
        Toast.makeText(this, "Pizza has been added!",
                Toast.LENGTH_LONG).show();
    }

    /**
     * Updates the price accordingly to our current pizza
     */
    private void updatePrice() {
        priceTV.setText(
                new DecimalFormat("#,##0.00")
                        .format(currentPizza.price()));
    }

    /**
     * Sets current pizza type to small
     * @param view
     */
    public void setSmallRBOnClick(View view) {
        currentPizza.setSize(Size.SMALL);
        updatePrice();
    }

    /**
     * Sets current pizza type to medium
     * @param view
     */
    public void setMediumRBOnClick(View view) {
        currentPizza.setSize(Size.MEDIUM);
        updatePrice();
    }

    /**
     * Sets current pizza type to large
     * @param view
     */
    public void setLargeRBOnClick(View view) {
        currentPizza.setSize(Size.LARGE);
        updatePrice();
    }

    /**
     * Sets current pizza sauce to alfredo
     * @param view
     */
    public void setAlfredoRBOnClick(View view) {
        currentPizza.setSauce(Sauce.ALFREDO);
        updatePrice();
    }

    /**
     * Sets current pizza sauce to tomato
     * @param view
     */
    public void setTomatoRBOnClick(View view) {
        currentPizza.setSauce(Sauce.TOMATO);
        updatePrice();
    }


    /**
     * Sets extra sauce on current pizza
     * @param view
     */
    public void setExtraSauceCB(View view) {
        currentPizza.setExtraSauce(extraSauceCB.isChecked());
        updatePrice();
    }

    /**
     * Sets extra cheese on current pizza
     * @param view
     */
    public void setExtraCheeseCB(View view) {
        currentPizza.setExtraCheese(extraCheeseCB.isChecked());
        updatePrice();
    }

    /**
     * Resets the UI after clicking the add to order button
     */
    private void resetUI(){
        currentPizza = PizzaMaker.createPizza("BuildYourOwn");
        currentPizza.setSize(Size.SMALL);
        currentPizza.setSauce(Sauce.TOMATO);

        smallRB.setChecked(true);
        tomatoRB.setChecked(true);

        extraCheeseCB.setChecked(false);
        extraSauceCB.setChecked(false);

        allIndyLV.clearChoices();
        items.notifyDataSetChanged();

        updatePrice();
    }
}