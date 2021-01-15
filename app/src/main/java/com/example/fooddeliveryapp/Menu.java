package com.example.fooddeliveryapp;

import java.util.ArrayList;
import java.util.HashMap;

public class Menu {

    // Singleton
    public static Menu getInstance() {
        if (sharedInstance == null) {
            sharedInstance = new Menu();
        }
        return sharedInstance;
    }
    private static Menu sharedInstance = null;
    private Menu() {  // Don't allow instantiation outside of this class

    }

    public HashMap<String, ArrayList<Dish>> dishesByCuisine() {
        if (menu == null) { // lazy instantiation
            populateMenu();
        }
       return menu;
    }
    private HashMap<String, ArrayList<Dish>> menu = null;

    private void populateMenu() {
        menu = new HashMap<String, ArrayList<Dish>>();

                // Iterate through every possible Cuisine type
        for (Cuisine cuisine : Cuisine.values()) {
            ArrayList<Dish> dishesList = new ArrayList<Dish>();
            // insert 6 dishes for each cuisine
            for ( int i = 0; i < 6; i++) {
                Dish dish = new Dish("");
                dishesList.add(dish);
            }

            //Here, dishesList will have 6 dishes added to it
            // so add it to the menu dictionary
            menu.put(cuisine.toString(), dishesList);
        }

        //Here, my menu dictioanry should be all populated
        System.out.println("menu: " + menu);
    }

    public Cuisine selectedDishCuisine;
    public int selectedDishPosition;
} 
