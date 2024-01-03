package com.example.pizzabypatel;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pizzabypatel.pizzastore.Order;
import com.example.pizzabypatel.pizzastore.StoreOrder;

import java.text.DecimalFormat;

/**
 * Activity for all orders
 * @author Dharmik Patel and Krish Patel
 */
public class StoreOrderActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CurrOrderAdapater rv_adapater;
    private ArrayAdapter sp_adapter;
    private TextView cost;
    private Spinner picker;
    private Button cancelOrder;
    private StoreOrder storeOrder;

    /**
     * Makes all the views with find view by id
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note:
     *     Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order);
        recyclerView = findViewById(R.id.storeOrderRV);
        cost = findViewById(R.id.totalPrice_storeOrders);
        picker = findViewById(R.id.orderPickerSpinner);
        cancelOrder = findViewById(R.id.cancelOrder);
        storeOrder = GlobalPizzaData.getInstance().getStoreOrder();
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false));
        //udpated spiiner
        updateSPAdapter();
        picker.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                updateRVAdapter(getSelectedOrderNum());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        updateRVAdapter(getSelectedOrderNum());
        cancelOrder.setOnClickListener(v -> {
            if(!getSelectedOrderNum().isEmpty()){
                storeOrder.removeOrder(getSelectedOrderNum());
                updateSPAdapter();
                updateRVAdapter(getSelectedOrderNum());
            } else {
                Toast.makeText(StoreOrderActivity.this,
                        "No Order Selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Gets the curr selected spinner value
     * @return
     */
    private String getSelectedOrderNum(){
        if(picker.getSelectedItem() != null)
            return picker.getSelectedItem().toString();
        else
            return "";
    }

    /**
     * Updates the spinner value, if the selected order is removed
     */
    private void updateSPAdapter(){
        sp_adapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout
                        .support_simple_spinner_dropdown_item, storeOrder
                .getOrderNumbersAsListOfStrings());
        picker.setAdapter(sp_adapter);
    }

    /**
     * Updates the RV once spinner value is changed
     * @param orderNumber
     */
    private void updateRVAdapter(String orderNumber) {
        if(storeOrder.getOrder(orderNumber) == null){
            rv_adapater = null;
            recyclerView.setAdapter(rv_adapater);
            cost.setText("$0.00");
            return;
        }
        Order currOrder = storeOrder.getOrder(orderNumber);
        rv_adapater = new CurrOrderAdapater(currOrder.getPizzas(),
                this, false);
        recyclerView.setAdapter(rv_adapater);
        cost.setText(
                new DecimalFormat("$#,##0.00")
                        .format(currOrder.orderTotal()));
    }
}