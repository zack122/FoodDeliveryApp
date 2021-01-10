package com.example.fooddeliveryapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class RestaurantHeaderFragment extends Fragment {


    public RestaurantHeaderFragment() {
        // Required empty public constructor
    }



    public static RestaurantHeaderFragment newInstance() {
        RestaurantHeaderFragment fragment = new RestaurantHeaderFragment();

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_restaurant_header, container, false);
        setupViews();
        return rootView;
    }

    //private properties
    private View rootView;
    //private methods
    private void setupViews(){
        Restaurant restaurant = Restaurant.getInstance();

        ImageView imageView = rootView.findViewById(R.id.restaurant_image);
        TextView nameTextView =  rootView.findViewById(R.id.name_text_View);
        TextView shortDescriptionTextView =  rootView.findViewById(R.id.short_Description_text_view);
        TextView streetAddressTextView =  rootView.findViewById(R.id.street_address_text_view);
        TextView cityTextView =  rootView.findViewById(R.id.city_text_view);
        TextView phoneNumberTextView =  rootView.findViewById(R.id.phone_number_text_view);

        imageView.setImageResource(restaurant.imageResource);
        nameTextView.setText(restaurant.restaurantName);
        shortDescriptionTextView.setText(restaurant.shortDesciption);
        streetAddressTextView.setText(restaurant.streetAddress);
        cityTextView.setText(restaurant.city);
        phoneNumberTextView.setText(restaurant.phoneNumber);
    }
}