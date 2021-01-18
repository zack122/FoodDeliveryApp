package com.example.fooddeliveryapp;

public enum Cuisine {
    BREAKFAST("Breakfast"),
    BURGERS("Burgers"),
    CHINESE("Chinese"),
    DESSERT("Dessert"),
    FISH("Fish"),
    JAPANESE("Japanese"),
    KOREAN("Korean"),
    MEXICAN("Mexican"),
    SALAD("Salad"),
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
