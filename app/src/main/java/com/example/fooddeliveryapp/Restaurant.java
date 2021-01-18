package com.example.fooddeliveryapp;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;


public class Restaurant {

    // Singleton
    public static Restaurant getInstance() {
        if (sharedInstance == null) {
            sharedInstance = new Restaurant();
        }
        return sharedInstance;
    }
    private static Restaurant sharedInstance = null;
    private Restaurant() {  // Don't allow instantiation outside of this class
        setupProperties();
    }

    // Public properties
    public String restaurantName;
    public String shortDescription;
    public String streetAddress;
    public String city;
    public String phoneNumber;
    public int imageResource;

    // Private properties
    private Lorem lorem = LoremIpsum.getInstance();

    // Private methods
    private void setupProperties() {
        restaurantName = lorem.getTitle(1, 2);
        shortDescription = lorem.getWords(3, 7);
        streetAddress = randomStreetAddress();
        city = lorem.getCity();
        phoneNumber = lorem.getPhone();
        imageResource = R.drawable.restaurant;
    }

    private String randomStreetAddress() {
        int min = 100;
        int max = 9999;
        int randomNumber = (int)(Math.random() * (max - min + 1) + min);
        String randomStreetAddress = randomNumber + " " + lorem.getCity() + " " + "Street";
        return randomStreetAddress;
    }
}