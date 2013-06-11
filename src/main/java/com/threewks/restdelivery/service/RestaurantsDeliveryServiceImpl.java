package com.threewks.restdelivery.service;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.LatLng;
import com.threewks.restdelivery.repository.RestaurantsDeliveryRepositoryInterface;
import com.threewks.restdelivery.repository.entity.Restaurant;
import com.threewks.restdelivery.repository.entity.UserAddressForRestaurant;
import com.threewks.restdelivery.exceptions.AddressIsOutOfDeliveryRangeException;
import com.threewks.restdelivery.utils.LocationUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 6/9/13
 * Time: 4:14 PM
 * Restaurant Delivery service implementation.
 */
public class RestaurantsDeliveryServiceImpl implements RestaurantsDeliveryServiceInterface{


    private RestaurantsDeliveryRepositoryInterface restaurantsDeliveryRep;

    private Geocoder geocoder;

    public RestaurantsDeliveryServiceImpl(RestaurantsDeliveryRepositoryInterface restaurantsDeliveryRep, Geocoder geocoder) {
        this.restaurantsDeliveryRep = restaurantsDeliveryRep;
        this.geocoder = geocoder;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantsDeliveryRep.selectAllRestaurants();
    }

    @Override
    public void saveUserAddress(String address, Long restaurantId) throws AddressIsOutOfDeliveryRangeException{
        //Checking address availability in delivery range of restaurant
        GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(address).setLanguage("en").getGeocoderRequest();
        GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
        List<GeocoderResult> results = geocoderResponse.getResults();

        if (results == null || results.size() == 0) {
            throw new AddressIsOutOfDeliveryRangeException("Specified address location could not be found");
        }

        GeocoderResult geocoderResult = results.get(0);
        LatLng addressLocation = geocoderResult.getGeometry().getLocation();
        double addressLat = addressLocation.getLat().doubleValue();
        double addressLng = addressLocation.getLng().doubleValue();

        Restaurant restaurantDto = restaurantsDeliveryRep.selectRestaurantInfo(restaurantId);
        Double restLat = restaurantDto.getLatitude();
        Double restLng = restaurantDto.getLongitude();
        double distance = LocationUtils.calculateDistance(addressLat, addressLng,
                restLat, restLng, LocationUtils.DISTANCE_UNIT.MILES);

        if (distance > restaurantDto.getDeliveryRadius()) {
            throw new AddressIsOutOfDeliveryRangeException("Specified address is out of delivery range");
        }

        UserAddressForRestaurant userAddressForRestaurant = new UserAddressForRestaurant();
        // User id 1 is used for simplicity to just implement saving user address for restaurant.
        userAddressForRestaurant.setUserId(1);
        userAddressForRestaurant.setRestaurantId(restaurantId);
        userAddressForRestaurant.setLatitude(addressLat);
        userAddressForRestaurant.setLongitude(addressLng);
        userAddressForRestaurant.setUserAddress(address);

        restaurantsDeliveryRep.insertUserAddressForRestaurant(userAddressForRestaurant);

    }

    @Override
    public void addBlacklistedAddress(String address, Double radius, Integer restaurantId) throws AddressIsOutOfDeliveryRangeException {
        //Fetching Address physical location.
        GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(address).setLanguage("en").getGeocoderRequest();
        GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
        List<GeocoderResult> results = geocoderResponse.getResults();

        if (results == null || results.size() == 0) {
            throw new AddressIsOutOfDeliveryRangeException("Specified address location could not be found");
        }

        GeocoderResult geocoderResult = results.get(0);
        LatLng addressLocation = geocoderResult.getGeometry().getLocation();
        double addressLat = addressLocation.getLat().doubleValue();
        double addressLng = addressLocation.getLng().doubleValue();


        throw new NotImplementedException();
    }
}
