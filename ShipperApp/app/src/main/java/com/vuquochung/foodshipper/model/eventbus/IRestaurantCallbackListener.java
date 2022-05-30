package com.vuquochung.foodshipper.model.eventbus;

import com.vuquochung.foodshipper.model.RestaurantModel;

import java.util.List;

public interface IRestaurantCallbackListener {

    void onRestaurantLoadSuccess(List<RestaurantModel> restaurantModelList);
    void onRestaurantLoadFailed(String message);

}
