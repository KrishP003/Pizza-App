package com.example.pizzabypatel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzabypatel.pizzastore.pizza.Pizza;
import com.example.pizzabypatel.pizzastore.pizza.PizzaMaker;
import com.example.pizzabypatel.pizzastore.pizza.enums.Size;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This adapter is for the recycler view in Spec Pizza Activity
 * @author Dharmik Patel and Krish Patel
 */
public class SpecPizzaAdapter extends RecyclerView.
        Adapter<SpecPizzaAdapter.SpecPizzaHolder> {
    private Context context; //need the context to inflate the layout
    private ArrayList<Pizza> pizzas;
    //need the data binding to each row of RecyclerView

    /**
     * This is the constructor for the adpater
     * @param context the context
     * @param pizzas the pizza list
     */
    public SpecPizzaAdapter(Context context, ArrayList<Pizza> pizzas) {
        this.context = context;
        this.pizzas = pizzas;
    }

    /**
     * This method makes the view, and inflates. Also sets up on click listens
     * @param parent The ViewGroup into which the new View will be added after
     *              it is bound to an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return the holder
     */
    @NonNull
    @Override
    public SpecPizzaHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                              int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view_spec_pizza, parent,
                false);
        final SpecPizzaHolder holder = new SpecPizzaHolder(view);
        holder.smallBtn.setOnClickListener(v -> {
            pizzas.get(holder.getAdapterPosition()).setSize(Size.SMALL);
            holder.updatePrice(pizzas.get(holder.getAdapterPosition()),
                    holder.numberPicker.getValue());
        });
        holder.mediumBtn.setOnClickListener(v -> {
            pizzas.get(holder.getAdapterPosition()).setSize(Size.MEDIUM);
            holder.updatePrice(pizzas.get(holder.getAdapterPosition()),
                    holder.numberPicker.getValue());
        });
        holder.largeBtn.setOnClickListener(v -> {
            pizzas.get(holder.getAdapterPosition()).setSize(Size.LARGE);
            holder.updatePrice(pizzas.get(holder.getAdapterPosition()),
                    holder.numberPicker.getValue());
        });
        holder.extraSauceCheckBox.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
            pizzas.get(holder.getAdapterPosition()).setExtraSauce(isChecked);
            holder.updatePrice(pizzas.get(holder.getAdapterPosition()),
                    holder.numberPicker.getValue());
        });
        holder.extraCheeseCheckBox.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
            pizzas.get(holder.getAdapterPosition()).setExtraCheese(isChecked);
            holder.updatePrice(pizzas.get(holder.getAdapterPosition()),
                    holder.numberPicker.getValue());
        });
        holder.addToOrderBtn.setOnClickListener(v -> {
            makeAlert(holder);
        });
        holder.numberPicker.setOnValueChangedListener(
                (picker, oldVal, newVal) -> {
            holder.updatePrice(pizzas.get(holder.getAdapterPosition()),
                    holder.numberPicker.getValue());
        });
        return holder;
    }

    /**
     * Just makes the alert to confirm person wants to add to order
     * @param holder the holder of the view
     */
    private void makeAlert(SpecPizzaHolder holder){
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Confirmation");
        alert.setMessage("Do you want to add " + pizzas.get(
                holder.getAdapterPosition()).getClass().getSimpleName()
                + " pizza to your current order?");
        alert.setPositiveButton("Add", (dialog, which) -> {
            Toast.makeText(context, "Added!", Toast.LENGTH_LONG).show();
            //add and reset the pizza
            for(int i = 0; i < holder.numberPicker.getValue(); i++){
                GlobalPizzaData.getInstance()
                        .getCurrOrder()
                        .addPizza(pizzas.get(holder.getAdapterPosition()));
            }
            String ptype = pizzas.get(holder.getAdapterPosition()).
                    getClass().getSimpleName();
            Pizza pizza = PizzaMaker.createPizza(ptype);
            pizzas.set(holder.getAdapterPosition(), pizza);
            holder.smallBtn.setChecked(true);
            holder.extraCheeseCheckBox.setChecked(false);
            holder.extraSauceCheckBox.setChecked(false);
            SpecPizzaAdapter.this.notifyItemChanged(
                    holder.getAdapterPosition());
        }).setNegativeButton("Cancel", (dialog, which) ->
                Toast.makeText(context, "Not Added", Toast.LENGTH_LONG)
                        .show());
        AlertDialog dialog = alert.create();
        dialog.show();
    }
    /**
     * This resets the view
     * @param holder The ViewHolder which should be updated to represent the
     *        contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull SpecPizzaHolder holder,
                                 int position) {
        final Pizza[] currPiza = {pizzas.get(position)};
        holder.sauceText.setText(currPiza[0].getSauce().toString());
        ArrayAdapter<String> toppings =
                new ArrayAdapter<String>(this.context,
                        android.R.layout.simple_list_item_1,
                        currPiza[0].getToppingsAsString());
        holder.ingredientLV.setAdapter(toppings);
        holder.pizzaTitle.setText(currPiza[0].getClass().getSimpleName());
        holder.img.setImageResource(currPiza[0].getImgID());
        holder.updatePrice(currPiza[0], holder.numberPicker.getValue());
    }

    /**
     * This gets the numbs of pizzas
     * @return the size
     */
    @Override
    public int getItemCount() {
        return pizzas.size();

    }

    /**
     * This is inner class that extens view holder.
     */
    public static class SpecPizzaHolder extends RecyclerView.ViewHolder {
        private NumberPicker numberPicker;
        private TextView pizzaTitle;
        private ListView ingredientLV;
        private TextView sauceText;
        private TextView priceText;
        private Button addToOrderBtn;
        private CheckBox extraSauceCheckBox;
        private CheckBox extraCheeseCheckBox;
        private RadioButton smallBtn;
        private RadioButton mediumBtn;
        private RadioButton largeBtn;
        private ImageView img;

        /**
         * This is the constructor for each view
         * @param itemView the view
         */
        public SpecPizzaHolder(@NonNull View itemView) {
            super(itemView);
            pizzaTitle = itemView.findViewById(R.id.specPizzaRowTitle);
            ingredientLV = itemView.findViewById(R.id.ingredintsListView);
            sauceText = itemView.findViewById(R.id.sauceTpye);
            priceText = itemView.findViewById(R.id.price);
            addToOrderBtn = itemView.findViewById(R.id.addToOrder);
            extraCheeseCheckBox = itemView.findViewById(R.id.extracheese);
            extraSauceCheckBox = itemView.findViewById(R.id.extrasauce);
            smallBtn = itemView.findViewById(R.id.smallBtn);
            mediumBtn = itemView.findViewById(R.id.mediumBtn);
            largeBtn = itemView.findViewById(R.id.largeBtn);
            numberPicker = itemView.findViewById(R.id.numberPickup);
            img = itemView.findViewById(R.id.specpicimage);
            smallBtn.setChecked(true);
            extraCheeseCheckBox.setChecked(false);
            extraSauceCheckBox.setChecked(false);
            numberPicker.setMinValue(1);
            numberPicker.setMaxValue(Integer.MAX_VALUE);
            numberPicker.setWrapSelectorWheel(false);
        }

        /**
         * This updates the price on screen
         * @param currPizza the pizza
         */
        public void updatePrice(Pizza currPizza, int amount) {
            priceText.setText(
                    new DecimalFormat("#,##0.00")
                            .format(currPizza.price() * amount));
        }

    }

}
