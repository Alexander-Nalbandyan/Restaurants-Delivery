package com.threewks.restdelivery.repository.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 6/9/13
 * Time: 4:15 PM
 *
 * Represents entity object for restaurant table row.
 */
@Entity
public class Restaurant {
    /**
     * Restaurant unique identifier.
     */
    @Id
    private Long id;

    /**
     * Restaurant name.
     */
    private String name;

    /**
     * Restaurant address string.
     */
    private String address;

    /**
     * Restaurant latitude coordinate.
     */
    private Double latitude;

    /**
     * Restaurant longitude coordinate.
     */
    private Double longitude;

    /**
     * Restaurant delivery radius in miles.
     */
    private Double deliveryRadius;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getDeliveryRadius() {
        return deliveryRadius;
    }

    public void setDeliveryRadius(Double deliveryRadius) {
        this.deliveryRadius = deliveryRadius;
    }
}
