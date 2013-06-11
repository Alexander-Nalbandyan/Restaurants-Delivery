package com.threewks.restdelivery.repository.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 6/9/13
 * Time: 8:51 PM
 *
 * Represents entity class for user address.
 */
@Entity
public class UserAddressForRestaurant {

    @Id Long id;

    /**
     * User id.
     */
    private Integer userId;

    /**
     * Restaurant id.
     */
    private Long restaurantId;

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

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
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
