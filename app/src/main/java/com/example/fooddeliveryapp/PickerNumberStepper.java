package com.example.fooddeliveryapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class PickerNumberStepper extends LinearLayout implements View.OnClickListener {

    // Public properties
    Integer minimumValue = 0;
    Integer maximumValue = 10;
    Integer currentValue = 0;

    // Constructor
    public PickerNumberStepper(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        // Inflate our layout file
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.picker_number_stepper, this, true);

        // Connect the edit text view
        numberEditText = findViewById(R.id.numberEditText);

        // Connects and configure the subtract button
        ImageButton subtractButton = findViewById(R.id.subtractImageButton);
        subtractButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentValue - 1 >= minimumValue) {
                    currentValue--;
                }
                refreshCurrentValue();
                performClick();
            }
        });

        // Connect and configure the add button
        ImageButton addButton = findViewById(R.id.addImageButton);
        addButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentValue + 1 <= maximumValue) {
                    currentValue++;
                }
                refreshCurrentValue();
                performClick();
            }
        });

        refreshCurrentValue();
    }

    // Overridden OnClickListener methods
    @Override
    public void onClick(View v) {
        // Do nothing
    }

    // Private properties
    private EditText numberEditText;

    // Private methods
    private void refreshCurrentValue() {
        numberEditText.setText(currentValue.toString());
    }
}

