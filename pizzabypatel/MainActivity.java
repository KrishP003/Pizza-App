package com.example.pizzabypatel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

/**
 * Activity for home screen
 * @author Dharmik Patel and Krish Patel
 */
public class MainActivity extends AppCompatActivity {
    private ImageButton launchSpecPizzaMenuBtn;
    private ImageButton launchBYOPizzaMenuBtn;
    private ImageButton launchCurrOrderPizzaMenuBtn;
    private ImageButton launchStoreOrderPizzaMenuBtn;

    /**
     * This sets up all the 4 buttons that launch each page
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it
     *     most recently supplied in {@link #onSaveInstanceState}.  <b><i
     *     >Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        launchSpecPizzaMenuBtn = findViewById(R.id.specPizzaButton);
        launchBYOPizzaMenuBtn = findViewById(R.id.byoButton);
        launchCurrOrderPizzaMenuBtn = findViewById(R.id.currOrderButton);
        launchStoreOrderPizzaMenuBtn = findViewById(R.id.storeOrdersButton);
        launchSpecPizzaMenuBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,
                    SpecPizzaActivity.class);
            startActivity(intent);
        });
        launchBYOPizzaMenuBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,
                    BYOPizzaActivity.class);
            startActivity(intent);
        });
        launchCurrOrderPizzaMenuBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,
                    CurrentOrderActivity.class);
            startActivity(intent);
        });
        launchStoreOrderPizzaMenuBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,
                    StoreOrderActivity.class);
            startActivity(intent);
        });
    }
}