package com.threewks.restdelivery.db;

import com.threewks.restdelivery.db.dto.RestaurantDto;
import com.threewks.restdelivery.db.dto.UserAddressForRestaurantDto;

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
    List<RestaurantDto> selectAllRestaurants() throws SQLException;

    /**
     * Selects restaurant info from restaurant table by provided id.
     *
     * @param restaurantId Restaurant id
     * @return Restaurant info
     */
    RestaurantDto selectRestaurantInfo(Integer restaurantId) throws SQLException;

    /**
     * Creates new row in user_address_for_restaurant table with provided info.
     *
     * @param userAddressForRestaurantDto   User address info.
     */
    void insertUserAddressForRestaurant(UserAddressForRestaurantDto userAddressForRestaurantDto) throws SQLException;
}
