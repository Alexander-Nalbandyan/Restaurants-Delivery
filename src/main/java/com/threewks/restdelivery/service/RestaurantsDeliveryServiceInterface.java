package com.threewks.restdelivery.service;

import com.threewks.restdelivery.repository.entity.Restaurant;
import com.threewks.restdelivery.exceptions.AddressIsOutOfDeliveryRangeException;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 6/9/13
 * Time: 4:13 PM
 *
 * Provides Restaurants delivery service interface.
 */
public interface RestaurantsDeliveryServiceInterface {
    /**
     * Returns list of all registered restaurants in the system.
     *
     * @return  List of restaurants.
     */
    public List<Restaurant> getAllRestaurants();

    /**
     * Save user address for provided restaurant.
     * @param address       User address.
     * @param restaurantId  Restaurant id.
     *
     * @throws AddressIsOutOfDeliveryRangeException     If restaurant could not deliver
     * to provided address.
     */
    void saveUserAddress(String address, Long restaurantId) throws AddressIsOutOfDeliveryRangeException;


    /**
     * Add new blacklisted address for provided restaurant.
     * @param address       Blacklisted address.
     * @param radius        Blacklisted address radius.
     * @param restaurantId  Restaurant id.
     *
     * @throws AddressIsOutOfDeliveryRangeException     If provided address could not be located.
     */
    void addBlacklistedAddress(String address, Double radius, Long restaurantId) throws AddressIsOutOfDeliveryRangeException;

    /**
     * Checks whether provided address is in blacklisted area for
     * specified restaurant or not.
     *
     * @param address        Address string to check.
     * @param restaurantId   Restaurant id.
     * @return True only if address is in blacklisted area for restaurant.
     */
    boolean isAddressBlacklisted(String address, Long restaurantId) throws AddressIsOutOfDeliveryRangeException;
}
