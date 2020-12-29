package com.example.fooddeliveryapp;

import androidx.annotation.NonNull;

public enum Cuisine {
    BREAKFAST( "Breakfast"),
    BURGERS( "Burgers"),
    CHINESE( "Chinese"),
    DESSERT( "Dessert"),
    FISH( "Fish"),
    JAPANESE( "Japanese"),
    KOREAN( "Korean"),
    MEXICAN("Mexican"),
    SALAD( "Salad"),
    STEAK( "Steak"),
    SUSHI( "Sushi");



    Cuisine(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    @Override
    public String toString() {
        return cuisineName;
    }

    private  String cuisineName;
}
