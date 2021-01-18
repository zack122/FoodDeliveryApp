package com.example.fooddeliveryapp;

public enum Cuisine {
    BREAKFAST("Breakfast"),
    BURGERS("Burgers"),
    CHINESE("Chinese"),
    DESSERT("Dessert"),
    DRINKS("Drinks"),
    BEANS("Beans"),
    JAPANESE("Japanese"),
    KOREAN("Korean"),
    MEXICAN("Mexican"),
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
