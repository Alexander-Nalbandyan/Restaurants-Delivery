package com.threewks.restdelivery.db;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 6/9/13
 * Time: 4:42 PM
 *
 * Contains SQL queries.
 */
public class SQLConstants {




    private SQLConstants() {}

    /**
     * Query for selecting all restaurants.
     */
    public static final String SQL_SELECT_RESTAURANTS = "SELECT `id`, `name`, address, latitude, longitude, delivery_radius FROM restaurant";

    /**
     * Query for selecting restaurant by id.
     */
    public static final String SQL_SELECT_RESTAURANT_BY_ID = "SELECT `id`, `name`, address, latitude, longitude, delivery_radius FROM restaurant WHERE id = ?";

    /**
     * Query for inserting user address for restaurant.
     */
    public static final String SQL_INSERT_USER_ADDRESS_FOR_RESTAURANT = "INSERT INTO user_address_for_restaurant(user_id, restaurant_id,user_address,latitude,longitude) VALUES(?,?,?,?,?)";
}
