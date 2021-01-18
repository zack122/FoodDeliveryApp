package com.example.fooddeliveryapp;

public enum Cuisine {
    BREAKFAST("Breakfast"),
    BURGERS("Burgers"),
    CHINESE("Chinese"),
    DESSERT("Dessert"),
    DRINK("Drink"),
    JAPANESE("Japanese"),
    KOREAN("Korean"),
    MEXICAN("Mexican"),
    BEANS("Beans"),
    STEAK("Steak"),
    PIZZA("Pizza");


    Cuisine(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    @Override
    public String toString() {
        return cuisineName;
    }

    // Private properties
    private String cuisineName;
}
