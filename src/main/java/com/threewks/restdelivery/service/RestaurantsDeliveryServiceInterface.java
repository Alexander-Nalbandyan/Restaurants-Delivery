package com.threewks.restdelivery.service;

import com.threewks.restdelivery.db.dto.RestaurantDto;
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
     * @throws SQLException     When there is repository problem.
     */
    public List<RestaurantDto> getAllRestaurants() throws SQLException;

    /**
     * Save user address for provided restaurant.
     * @param address       User address.
     * @param restaurantId  Restaurant id.
     *
     * @throws AddressIsOutOfDeliveryRangeException     If restaurant could not deliver
     * to provided address.
     * @throws SQLException   If there is repository problem
     */
    void saveUserAddress(String address, Integer restaurantId) throws AddressIsOutOfDeliveryRangeException, SQLException;
}
