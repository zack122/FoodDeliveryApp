package com.example.fooddeliveryapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CuisineListFragment extends Fragment {

    public CuisineListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param cuisine The cuisine for this cuisine list fragment.
     * @return A new instance of fragment CuisineListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CuisineListFragment newInstance(Cuisine cuisine) {
        CuisineListFragment fragment = new CuisineListFragment();
        Bundle args = new Bundle();
        args.putString(CUISINE, cuisine.name());  // Pass name of cuisine (a String) as parameter into fragment
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Get cuisine name parameter
            String cuisineName = getArguments().getString(CUISINE);

            // Get cuisine enum object from cuisine name
            Cuisine cuisine = Cuisine.valueOf(cuisineName);

            // Get all dishes from the menu for this particular cuisine
            ArrayList<Dish> dishes = Menu.getInstance().dishesByCuisine().get(cuisineName);

            // Save cuisine and list of dishes to private properties for use later
            this.cuisine = cuisine;
            this.dishes = dishes;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_cuisine_list, container, false);

        setupCuisineName();
        setupRecyclerView();

        return rootView;
    }

    // Private constants
    private static final String CUISINE = "cuisine";   // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // Private properties
    private Cuisine cuisine;
    private ArrayList<Dish> dishes;

    // XML views
    private View rootView;
    private RecyclerView recyclerView;

    // Private methods
    private void setupCuisineName() {
        if (cuisine != null) {
            // Display name of cuisine on fragment display
            TextView cuisineNameTextView = rootView.findViewById(R.id.cuisine_name_text_view);
            cuisineNameTextView.setText(cuisine.toString());
        }
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        // Set adapter for recycler view
        DishAdapter dishAdapter = new DishAdapter(getContext(), dishes);
        recyclerView.setAdapter(dishAdapter);
    }

    private void userTappedOnDishAtPosition(int position) {
        // Save the selected cuisine and dish so we can add them to the Cart later in case user decides to
        Menu.getInstance().selectedDishCuisine = cuisine;
        Menu.getInstance().selectedDishPosition = position;

        Dish dish = Menu.getInstance().dishesByCuisine().get(cuisine.name()).get(position);
        System.out.println("User tapped on dish " + dish.name);

        // Navigate to Dish details screen
        Intent intent = new Intent(getContext(), DishActivity.class);
        getActivity().startActivity(intent);
    }

    // Adapter for Dishes list in recycler view
    private class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> {

        public DishAdapter(@NonNull Context context, @NonNull ArrayList<Dish> dishes) {
            this.context = context;
            this.dishes = dishes;
        }

        @NonNull
        @Override
        public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View itemView = inflater.inflate(R.layout.item_dish, parent, false);
            DishViewHolder viewHolder = new DishViewHolder(itemView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull DishViewHolder holder, final int position) {
            Dish dish = dishes.get(position);
            holder.nameTextView.setText(dish.name);
            Context context = getContext();
            int id = context.getResources().getIdentifier(dish.imageResourceName, "drawable", context.getPackageName());
            holder.imageView.setImageResource(id);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userTappedOnDishAtPosition(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return dishes.size();
        }

        // Private properties
        private final Context context;
        private final ArrayList<Dish> dishes;

        // Dish ViewHolder
        private class DishViewHolder extends RecyclerView.ViewHolder {

            public View itemView;
            public ImageView imageView;
            public TextView nameTextView;

            public DishViewHolder(@NonNull View itemView) {
                super(itemView);
                this.itemView = itemView;
                imageView = itemView.findViewById(R.id.imageView);
                nameTextView = itemView.findViewById(R.id.nameTextView);
            }
        }
    }
}