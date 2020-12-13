package com.example.fooddeliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dish dish1 = new Dish("");
        System.out.println("dish 1: " + dish1);
        Dish dish2 = new Dish("");
        System.out.println("dish 1: " + dish2);
        Dish dish3 = dish1;
        System.out.println("dish 3: " + dish3);


        System.out.println("dish1 == dish2 " + dish1.equals(dish2));
        System.out.println("dish1 == dish3 " + dish1.equals(dish3));
        System.out.println("dish2 == dish3 " + dish2.equals(dish3));
    }
}