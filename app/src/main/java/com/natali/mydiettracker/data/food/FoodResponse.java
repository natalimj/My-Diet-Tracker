package com.natali.mydiettracker.data.food;


import com.google.gson.annotations.SerializedName;
import com.natali.mydiettracker.model.Food;

import java.util.List;

public class FoodResponse {

    @SerializedName("items")
    private List<ItemValue> items;

    public List<ItemValue> getItems() {
        return items;
    }

    private class ItemValue {

        private String name;
        private double calories;
    }


    public Food getFood() {

        return new Food(items.get(0).name, items.get(0).calories);

    }
}