package com.example.fooddeliveryapp;

import androidx.annotation.NonNull;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

public class Dish {

    // public properties
  public String name;
  public String description;
  public Integer priceInCents;
  public String imageResourceName;

        //Constructor
    public Dish(String imageResourceName){
        this.imageResourceName = imageResourceName;

        populateProperties();

    }

    //public methods


    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", princeInCents=" + priceInCents +
                ", imageResourceName='" + imageResourceName + '\'' +
                '}';
    }

    public Boolean equals(Dish dish){
        return (name.equals(dish.name) &&
               description.equals(dish.description)&&
               imageResourceName.equals(dish.imageResourceName)&&
               priceInCents == dish.priceInCents);
    }

    //private properties
    private static Lorem lorem = LoremIpsum.getInstance();
    private int maxPriceInCents = 1999;
    private int minPriceInCents = 799;

    //private methods
    private void populateProperties(){
        String name = lorem.getTitle(1, 4);
        String description = lorem.getParagraphs(2, 4);

        this.name = name;
        this.description = description;
        this.priceInCents = (int)(Math.random() * (maxPriceInCents = minPriceInCents + 1) + minPriceInCents);


    }

}
