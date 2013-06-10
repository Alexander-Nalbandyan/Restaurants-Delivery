package com.threewks.restdelivery.db.dto;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 6/9/13
 * Time: 8:51 PM
 *
 * Represents dto class for user_address_for_restaurant table.
 */
public class UserAddressForRestaurantDto {

    /**
     * User id.
     */
    private Integer userId;

    /**
     * Restaurant id.
     */
    private Integer restaurantId;

    /**
     * User address.
     */
    private String userAddress;

    /**
     * User geo location latitude.
     */
    private double latitude;

    /**
     * User geo location latitude.
     */
    private double longitude;

    /**
     * Address for restaurant creation date.
     */
    private Date createDate;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
