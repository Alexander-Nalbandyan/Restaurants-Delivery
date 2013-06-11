package com.threewks.restdelivery.repository;

import com.threewks.restdelivery.repository.entity.Restaurant;
import com.threewks.restdelivery.repository.entity.UserAddressForRestaurant;

import java.util.List;

import static com.threewks.restdelivery.repository.OfyService.*;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 6/9/13
 * Time: 4:05 PM
 *
 * Implementation of restaurants delivery repository.
 */
public class RestaurantsDeliveryRepositoryImpl implements RestaurantsDeliveryRepositoryInterface {


    @Override
    public List<Restaurant> selectAllRestaurants() {
        List<Restaurant> restaurants = ofy().load().type(Restaurant.class).list();

        return restaurants;

    }

    @Override
    public Restaurant selectRestaurantInfo(Long restaurantId) {

        return ofy().load().type(Restaurant.class).id(restaurantId).now();

    }

    @Override
    public void insertUserAddressForRestaurant(UserAddressForRestaurant userAddressForRestaurant) {
        ofy().save().entity(userAddressForRestaurant).now();

    }
}
