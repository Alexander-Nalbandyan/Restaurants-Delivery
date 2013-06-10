package com.threewks.restdelivery.test;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;

import java.security.InvalidKeyException;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 6/9/13
 * Time: 6:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class Snippet {
    public static void main(String[] args) {
        final Geocoder geocoder;
        geocoder = new Geocoder();
        GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress("Paris, France").setLanguage("en").getGeocoderRequest();
        GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
        System.out.println(geocoderResponse.getResults().get(0).getGeometry().getLocation().getLat());
        System.out.println(geocoderResponse.getResults().get(0).getGeometry().getLocation().getLng());

    }
}
