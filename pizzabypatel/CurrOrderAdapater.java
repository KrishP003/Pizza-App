package com.example.pizzabypatel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzabypatel.pizzastore.Order;
import com.example.pizzabypatel.pizzastore.pizza.Pizza;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This adapter is for the recycler view in Curr Order and All orders
 * @author Dharmik Patel and Krish Patel
 */
public class CurrOrderAdapater extends
        RecyclerView.Adapter<CurrOrderAdapater.PizzaHolder> {
    private ArrayList<Pizza> pizzas;
    private Context context;
    private boolean showRMBtn;
    private Order currOrder;

    /**
     * This constructor makes the adapter
     * @param pizzas list of pizzas in curr order
     * @param context context from
     * @param showRMBtn true->show the rmv pizza btn false-> do not show
     * the button
     */
    public CurrOrderAdapater(ArrayList<Pizza> pizzas, Context context,
                             boolean showRMBtn){
        this.pizzas = pizzas;
        this.context = context;
        this.showRMBtn = showRMBtn;
        currOrder = GlobalPizzaData.getInstance().getCurrOrder();
    }

    /**
     * Makes each view holder, and sets up the listeners if showRMBtn
     * @param parent The ViewGroup into which the new View will be added after
     *               it is bound to an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return the holder
     */
    @NonNull
    @Override
    public PizzaHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                          int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view_curr_order,
                parent, false);
        PizzaHolder holder = new PizzaHolder(view);
        if(showRMBtn){
            holder.rmvPizza.setOnClickListener(v -> {
                pizzas.remove(pizzas.get(holder.getAdapterPosition()));
                int pos = holder.getAdapterPosition();
                CurrOrderAdapater.this.notifyItemRemoved(pos);
                CurrOrderAdapater.this.notifyItemRangeChanged(pos,
                        getItemCount() - pos);
                CurrentOrderActivity.updatePrices();
            });
        } else {
            holder.rmvPizza.setVisibility(View.GONE);
            holder.rmvPizza = null;
        }
        return holder;
    }

    /**
     *
     * @param holder The ViewHolder which should be updated to represent the
     *        contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull PizzaHolder holder, int position) {
        Pizza currPizza = pizzas.get(position);
        holder.title.setText(currPizza.getClass().getSimpleName());
        holder.ings.setText(currPizza.getToppingsSepByCommas());
        holder.extras.setText(currPizza.getExtrasSepByCommas());
        holder.size.setText(currPizza.getSize().toString());
        holder.sauce.setText(currPizza.getSauce().toString());
        holder.price.setText(
                new DecimalFormat("$#,##0.00")
                        .format(currPizza.price()));
        holder.img.setImageResource(currPizza.getImgID());
    }

    /**
     * num of pizzas in curr order
     * @return num of pizas
     */
    @Override
    public int getItemCount() {
        return pizzas.size();
    }

    /**
     * THis inner class is the view holder
     */
    public static class PizzaHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView ings;
        private TextView extras;
        private TextView size;
        private TextView sauce;
        private TextView price;
        private Button rmvPizza;
        private ImageView img;

        /**
         * Like on create, make all the views
         * @param itemView the view
         */
        public PizzaHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.currPizaTitle);
            ings = itemView.findViewById(R.id.currPizza_ings);
            extras = itemView.findViewById(R.id.currPizza_extras);
            size = itemView.findViewById(R.id.currPizza_size);
            sauce = itemView.findViewById(R.id.currPizza_sauce);
            price = itemView.findViewById(R.id.currPizza_price);
            img = itemView.findViewById(R.id.currPizza_img);
            rmvPizza = itemView.findViewById(R.id.currPizza_rmvBtn);
        }
    }
}
