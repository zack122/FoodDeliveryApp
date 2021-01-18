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
    private Menu() {}  // Don't allow instantiation outside of this class

    // Public properties
    public Cuisine selectedDishCuisine;
    public Integer selectedDishPosition;

    // Public method
    public HashMap<String, ArrayList<Dish>> dishesByCuisine() {
        if (menu == null) {
            populateMenu();
        }
        return menu;
    }

    // Private property
    private HashMap<String, ArrayList<Dish>> menu = null;  // "CuisineName" : [ Dish }

    // Private method
    private void populateMenu() {
        menu = new HashMap<String, ArrayList<Dish>>();

        // Iterate through every possible Cuisine type
        for (Cuisine cuisine : Cuisine.values()) {
            ArrayList<Dish> dishesList = new ArrayList<Dish>();

            // Insert 6 dishes for each cuisine
            for (int i = 0; i < 6; i++) {
                String imageResourceName = cuisine.name().toLowerCase() + "0" + (i+1);
                Dish dish = new Dish(imageResourceName);
                dishesList.add(dish);
            }

            // Here, dishesList will have 6 dishes added to it
            // So add it to the menu dictionary
            menu.put(cuisine.name(), dishesList);
        }

        // Here, my menu dictionary should be all populated
        System.out.println("Menu: " + menu);
    }
}