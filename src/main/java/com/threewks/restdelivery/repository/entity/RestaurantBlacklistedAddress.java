package com.threewks.restdelivery.repository.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 6/9/13
 * Time: 8:51 PM
 *
 * Represents entity class for restaurant_blacklisted_address table.
 */
@Entity
public class RestaurantBlacklistedAddress {

    /**
     * Blacklisted address id.
     */
    @Id
    private Long blacklistedAddressId;

    /**
     * Restaurant id.
     */
    @Index
    private Long restaurantId;

    /**
     * Blacklisted address.
     */
    private String blacklistedAddress;

    /**
     * Blacklisted address geo location latitude.
     */
    private double latitude;

    /**
     * Blacklisted address geo location longitude.
     */
    private double longitude;

    /**
     * Blacklisted address radius.
     */
    private double radius;


    public Long getBlacklistedAddressId() {
        return blacklistedAddressId;
    }

    public void setBlacklistedAddressId(Long blacklistedAddressId) {
        this.blacklistedAddressId = blacklistedAddressId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getBlacklistedAddress() {
        return blacklistedAddress;
    }

    public void setBlacklistedAddress(String blacklistedAddress) {
        this.blacklistedAddress = blacklistedAddress;
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

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
