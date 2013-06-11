package com.threewks.restdelivery.repository;

import com.threewks.restdelivery.repository.entity.Restaurant;
import com.threewks.restdelivery.repository.entity.RestaurantBlacklistedAddress;
import com.threewks.restdelivery.repository.entity.UserAddressForRestaurant;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 6/9/13
 * Time: 4:03 PM
 * <p/>
 * <p/>
 * Provides interface for restaurants delivery repository functionality.
 */
public interface RestaurantsDeliveryRepositoryInterface {


    /**
     * Selects all restaurants info from restaurant table.
     *
     * @return List of restaurants
     */
    List<Restaurant> selectAllRestaurants();

    /**
     * Selects restaurant info from restaurant table by provided id.
     *
     * @param restaurantId Restaurant id
     * @return Restaurant info
     */
    Restaurant selectRestaurantInfo(Long restaurantId);

    /**
     * Creates new user address for restaurant with provided info.
     *
     * @param userAddressForRestaurant   User address info.
     */
    void insertUserAddressForRestaurant(UserAddressForRestaurant userAddressForRestaurant);

    /**
     * Creates new restaurant blacklisted address.
     * @param restaurantBlacklistedAddress      restaurant blacklisted address.
     */
    void insertRestBlacklistedAddress(RestaurantBlacklistedAddress restaurantBlacklistedAddress);


    /**
     * Fetches restaurant blacklisted addresses.
     * @param restaurantId      Restaurant id.
     * @return List of restaurant blacklisted addresses.
     */
    List<RestaurantBlacklistedAddress> selectRestBlacklistedAddresses(Long restaurantId);
}
