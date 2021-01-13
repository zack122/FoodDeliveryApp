package com.example.fooddeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().setTitle("Cart");

        setupTotalTextView();
        setupCheckoutButton();
        setupRecyclerView();
    }

    // XML views
    private TextView totalAmountTextView;
    private RecyclerView recyclerView;

    // Private methods
    private void setupTotalTextView() {
        totalAmountTextView = findViewById(R.id.totalAmountTextView);
        Double total = Cart.getInstance().totalPriceInCents() / 100.0;
        totalAmountTextView.setText(String.format("$%.2f", total));
    }

    private void setupCheckoutButton() {
        Button checkoutButton = findViewById(R.id.checkoutButton);
        final Context context = this;
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle(R.string.checkout)
                        .setMessage(R.string.thank_you_for_your_purchase)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Cart.getInstance().clear();
                                finish();
                            }
                        })
                        .setIcon(R.drawable.ic_baseline_done_24)
                        .show();
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        CartAdapter cartAdapter = new CartAdapter(this, Cart.getInstance().getCartItems());
        recyclerView.setAdapter(cartAdapter);
    }

    // Adapter for Cart list
    private class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

        public CartAdapter(@NonNull Context context, @NonNull ArrayList<Cart.CartItem> cartItems) {
            this.context = context;
            this.cartItems = cartItems;
        }

        @NonNull
        @Override
        public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View itemView = inflater.inflate(R.layout.item_cart, parent, false);
            CartAdapter.CartViewHolder viewHolder = new CartAdapter.CartViewHolder(itemView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, final int position) {
            Cart.CartItem cartItem = cartItems.get(position);
            holder.quantityTextView.setText(cartItem.getQuantity().toString() + "x");
            Double itemPrice = cartItem.itemPriceInCents() / 100.0;
            holder.itemPriceTextView.setText(String.format("$%.2f", itemPrice));
            holder.descriptionTextView.setText(cartItem.dishName());
            Double subtotal = cartItem.subtotalPriceInCents() / 100.0;
            holder.subtotalTextView.setText(String.format("$%.2f", subtotal));
        }

        @Override
        public int getItemCount() {
            return cartItems.size();
        }

        // Private properties
        private final Context context;
        private final ArrayList<Cart.CartItem> cartItems;

        // Cart ViewHolder
        private class CartViewHolder extends RecyclerView.ViewHolder {

            public View itemView;
            public TextView quantityTextView;
            public TextView itemPriceTextView;
            public TextView descriptionTextView;
            public TextView subtotalTextView;

            public CartViewHolder(@NonNull View itemView) {
                super(itemView);
                this.itemView = itemView;
                quantityTextView = itemView.findViewById(R.id.quantityTextView);
                itemPriceTextView = itemView.findViewById(R.id.itemPriceTextView);
                descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
                subtotalTextView = itemView.findViewById(R.id.subtotalTextView);
            }
        }
    }
}