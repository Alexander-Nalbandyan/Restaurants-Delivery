package com.threewks.restdelivery.repository;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.threewks.restdelivery.repository.entity.Restaurant;
import com.threewks.restdelivery.repository.entity.RestaurantBlacklistedAddress;
import com.threewks.restdelivery.repository.entity.UserAddressForRestaurant;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 6/11/13
 * Time: 1:07 PM
 *
 * Wraps objectify service for safe registration of entities.
 */
public class OfyService {
    static {
        factory().register(Restaurant.class);
        factory().register(UserAddressForRestaurant.class);
        factory().register(RestaurantBlacklistedAddress.class);

        initializeRestSampleDB();
    }

    private static void initializeRestSampleDB() {
        List<Restaurant> restaurants = new ArrayList<Restaurant>();

        Restaurant restaurant = new Restaurant();
        restaurant.setName("Denali Pizza");
        restaurant.setAddress("725 North Central Avenue");
        restaurant.setLatitude(33.444d);
        restaurant.setLongitude(-112.3152d);
        restaurant.setDeliveryRadius(1400d);
        restaurants.add(restaurant);

        restaurant = new Restaurant();
        restaurant.setName("Papa Razzini");
        restaurant.setAddress("1825 E Guadalupe Rd #110");
        restaurant.setLatitude(33.364d);
        restaurant.setLongitude(-111.9316d);
        restaurant.setDeliveryRadius(2000d);
        restaurants.add(restaurant);

        restaurant = new Restaurant();
        restaurant.setName("Cafe Amsterdam");
        restaurant.setAddress("530 East Benson Boulevard");
        restaurant.setLatitude(61.1877d);
        restaurant.setLongitude(-149.889d);
        restaurant.setDeliveryRadius(1500d);
        restaurants.add(restaurant);

        restaurant = new Restaurant();
        restaurant.setName("China Wok");
        restaurant.setAddress("3736 Airport Boulevard");
        restaurant.setLatitude(30.683d);
        restaurant.setLongitude(-88.2974d);
        restaurant.setDeliveryRadius(3000d);
        restaurants.add(restaurant);

        ofy().save().entities(restaurants).now();

    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
