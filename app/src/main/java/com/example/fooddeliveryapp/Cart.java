package com.example.fooddeliveryapp;

import java.util.ArrayList;

public class Cart {

    //singleton
    public static Cart getInstance(){
        if(sharedInstance == null){
            sharedInstance = new Cart();
        }
        return sharedInstance;
    }
    private static Cart sharedInstance = null;
    private Cart(){} //dont allow instantiation outside of this class

    //public properties
    public Integer numberOfItems(){
        Integer numberOfItems = 0;
        for (CartItem cartItem : cartItems){
            numberOfItems += cartItem.getQuantity();
        }
        return numberOfItems;
    }

    public Integer totalPriceInCents(){
        Integer totalPrice = 0;
        for (CartItem cartItem : cartItems){
            totalPrice += cartItem.subtotalPriceInCents();
        }
        return  totalPrice;
    }

    public void add(Dish dish, Integer quantity){
        //check is dish already in our cart
        CartItem existingCartItem = null;
        for (CartItem cartItem : cartItems){
            if(cartItem.dish.equals(dish)){
                existingCartItem = cartItem;
                break;
            }
        }

        //did we find a matching cart item
        if(existingCartItem != null){
            // we found a matching dish in our cart
            existingCartItem.quantity += quantity;

        }
        else {
            //TODO: we don't have this dish in our car later
            CartItem newCartItem = new CartItem(dish, quantity);
            cartItems.add(newCartItem);
        }
    }

    //private properties

    private ArrayList<CartItem> cartItems = new ArrayList<>();

    //nested classes
    public class CartItem {


        //constructor

        public CartItem(Dish dish, Integer quantity){
            this.dish = dish;
            this.quantity = quantity;
        }
        //public methods
        public Integer getQuantity(){
            return quantity;
        }

        public Integer itemPriceInCents(){

            return dish == null ? 0: dish.priceInCents;
        }

        public String dishName(){
            return dish ==null ? "": dish.name;
        }

        public Integer subtotalPriceInCents(){
            return quantity * itemPriceInCents();
        }


        //private properties
        private Dish dish;
        private Integer quantity;

    }
}
