package com.threewks.restdelivery.service;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.LatLng;
import com.threewks.restdelivery.db.RestaurantsDeliveryRepositoryInterface;
import com.threewks.restdelivery.db.dto.RestaurantDto;
import com.threewks.restdelivery.db.dto.UserAddressForRestaurantDto;
import com.threewks.restdelivery.exceptions.AddressIsOutOfDeliveryRangeException;
import com.threewks.restdelivery.utils.LocationUtils;

import javax.inject.Inject;
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
    public List<RestaurantDto> getAllRestaurants() throws SQLException {
        return restaurantsDeliveryRep.selectAllRestaurants();
    }

    @Override
    public void saveUserAddress(String address, Integer restaurantId) throws AddressIsOutOfDeliveryRangeException, SQLException {
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

        RestaurantDto restaurantDto = restaurantsDeliveryRep.selectRestaurantInfo(restaurantId);
        Double restLat = restaurantDto.getLatitude();
        Double restLng = restaurantDto.getLongitude();
        double distance = LocationUtils.calculateDistance(addressLat, addressLng,
                restLat, restLng, LocationUtils.DISTANCE_UNIT.MILES);

        if (distance > restaurantDto.getDeliveryRadius()) {
            throw new AddressIsOutOfDeliveryRangeException("Specified address is out of delivery range");
        }

        UserAddressForRestaurantDto userAddressForRestaurantDto = new UserAddressForRestaurantDto();
        // User id 1 is used for simplicity to just implement saving user address for restaurant.
        userAddressForRestaurantDto.setUserId(1);
        userAddressForRestaurantDto.setRestaurantId(restaurantId);
        userAddressForRestaurantDto.setLatitude(addressLat);
        userAddressForRestaurantDto.setLongitude(addressLng);
        userAddressForRestaurantDto.setUserAddress(address);

        restaurantsDeliveryRep.insertUserAddressForRestaurant(userAddressForRestaurantDto);

    }
}
