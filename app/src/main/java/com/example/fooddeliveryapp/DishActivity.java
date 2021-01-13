package com.example.fooddeliveryapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);

        // Get our data model and determine what is the currently selected dish (the dish the user tapped to get here)
        System.out.println("Selected cuisine: " + Menu.getInstance().selectedDishCuisine + ", position: " + Menu.getInstance().selectedDishPosition);
        final HashMap<String, ArrayList<Dish>> dishesByCuisine = Menu.getInstance().dishesByCuisine();
        final ArrayList<Dish> dishesForCuisine = dishesByCuisine.get(Menu.getInstance().selectedDishCuisine.name());
        selectedDish = dishesForCuisine.get(Menu.getInstance().selectedDishPosition);

        // Set the screen title to the dish name
        getSupportActionBar().setTitle(selectedDish.name);

        // Connect and populate the image
        final ImageView dishImageView = findViewById(R.id.dishImageView);
        final int imageId = getResources().getIdentifier(selectedDish.imageResourceName, "drawable", getPackageName());
        dishImageView.setImageResource(imageId);

        // Connect and populate the dish name
        final TextView dishNameTextView = findViewById(R.id.dishNameTextView);
        dishNameTextView.setText(selectedDish.name);

        // Connect and populate the dish item price
        final TextView itemPriceTextView = findViewById(R.id.itemPriceTextView);
        itemPriceTextView.setText(String.format("$%.2f", selectedDish.priceInCents / 100.0));

        // Connect and configure the picker stepper custom widget
        final PickerNumberStepper numberPicker = findViewById(R.id.numberPicker);
        numberPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedQuantity = numberPicker.currentValue;
                refreshSubTotal();
            }
        });

        // Connect and configure add to cart button
        final ImageButton cartButton = findViewById(R.id.addToCartButton);
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });

        // Connect and populate the dish description
        final TextView dishDescription = findViewById(R.id.dishDescriptionTextView);
        dishDescription.setText(selectedDish.description);

        // Display initial value for subtotal
        refreshSubTotal();
    }

    // Private properties
    private Dish selectedDish;
    private Integer selectedQuantity = 0;

    // Private methods
    private void refreshSubTotal() {
        final TextView subtotalTextView = findViewById(R.id.subtotalTextView);
        subtotalTextView.setText(String.format("$%.2f", selectedQuantity * selectedDish.priceInCents / 100.0));
    }

    private void addToCart() {
        // Check if a valid quantity was selected
        if (selectedQuantity == 0) {
            // Display an alert message if nothing to add to cart
            new AlertDialog.Builder(this)
                    .setTitle(R.string.cannot_add_to_cart)
                    .setMessage(R.string.choose_one_or_more_items)
                    .setPositiveButton(R.string.ok, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        else {
            // Add the selected dish and quantity to the cart
            Cart.getInstance().add(selectedDish, selectedQuantity);

            // Dismisses this activity (goes back to previous screen)
            finish();
        }
    }
}
