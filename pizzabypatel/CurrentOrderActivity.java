package com.example.pizzabypatel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pizzabypatel.pizzastore.Order;
import com.example.pizzabypatel.pizzastore.StoreOrder;

import java.text.DecimalFormat;

/**
 * Activity for current orders
 * @author Dharmik Patel and Krish Patel
 */
public class CurrentOrderActivity extends AppCompatActivity {
    private TextView orderNumber;
    private static TextView subtotal;
    private static TextView tax;
    private static TextView total;
    private RecyclerView currPizzasRV;
    private Button placeOrder;
    private static Order currOrder;
    private StoreOrder storeOrder;
    private CurrOrderAdapater adapater;

    /**
     * This oncreate sets up the views
     * and also the listerns for all the buttons
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it
     *     most recently supplied in {@link #onSaveInstanceState}.  <b><i
     *     >Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        orderNumber = findViewById(R.id.currorderNumber);
        subtotal = findViewById(R.id.subTotalPrice);
        tax = findViewById(R.id.taxPrice);
        total = findViewById(R.id.totalPrice);
        currPizzasRV = findViewById(R.id.currOrderRV);
        placeOrder = findViewById(R.id.addToStoreOrdersBtn);
        currOrder = GlobalPizzaData.getInstance().getCurrOrder();
        storeOrder = GlobalPizzaData.getInstance().getStoreOrder();
        orderNumber.setText(currOrder.getOrderNumber() + "");
        updatePrices();
        adapater = new CurrOrderAdapater(currOrder.getPizzas(), this, true);
        currPizzasRV.setAdapter(adapater);
        currPizzasRV.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        placeOrder.setOnClickListener(v -> {
            onClickMethod();
        });
    }

    /**
     * This sets up the alert to confirm that user wants to place order
     */
    private void onClickMethod() {
        if(currOrder.getNumOfPizzas() == 0) {
            Toast.makeText(CurrentOrderActivity.this,
                    "Please Add Pizza", Toast.LENGTH_LONG).show();
            return;
        }
        AlertDialog.Builder alert = new
                AlertDialog.Builder(subtotal.getContext());

        alert.setTitle("Confirmation");
        alert.setMessage("Do you want to place the order?");
        //anonymous inner class to handle the onClick event of YES or NO.
        alert.setPositiveButton("Place",
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(CurrentOrderActivity.this,
                        "Order Placed!", Toast.LENGTH_SHORT).show();
                //add and reset the pizza

                storeOrder.addOrder(currOrder);
                currOrder = GlobalPizzaData.getInstance().resetCurrOrder();
                orderNumber.setText(currOrder.getOrderNumber() + "");
                adapater = new CurrOrderAdapater(currOrder.getPizzas(),
                        CurrentOrderActivity.this, true);
                currPizzasRV.swapAdapter(adapater, false);
                updatePrices();
            }
        }).setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(CurrentOrderActivity.this,
                        "Order Not Placed", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     * Updates alll 3 prices in proper format
     */
    public static void updatePrices(){
        subtotal.setText(
                new DecimalFormat("$#,##0.00")
                        .format(currOrder.subTotal()));
        tax.setText(
                new DecimalFormat("$#,##0.00")
                        .format(currOrder.tax()));
        total.setText(
                new DecimalFormat("$#,##0.00")
                        .format(currOrder.orderTotal()));
    }
}