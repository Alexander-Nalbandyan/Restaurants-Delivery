package com.threewks.restdelivery;

import com.google.code.geocoder.Geocoder;
import com.googlecode.objectify.ObjectifyService;
import com.threewks.restdelivery.repository.RestaurantsDeliveryRepositoryImpl;
import com.threewks.restdelivery.repository.RestaurantsDeliveryRepositoryInterface;
import com.threewks.restdelivery.repository.entity.UserAddressForRestaurant;
import com.threewks.restdelivery.service.RestaurantsDeliveryServiceImpl;
import com.threewks.restdelivery.service.RestaurantsDeliveryServiceInterface;
import com.threewks.thundr.injection.InjectionConfiguration;
import com.threewks.thundr.injection.UpdatableInjectionContext;
import org.apache.commons.dbcp.*;
import org.apache.log4j.Logger;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 6/9/13
 * Time: 2:12 PM
 *
 * Contains restaurant delivery injection configurations.
 */
public class RestdeliveryInjectionConfiguration implements InjectionConfiguration {

    Logger logger = Logger.getLogger(RestdeliveryInjectionConfiguration.class);

    @Override
    public void configure(UpdatableInjectionContext updatableInjectionContext) {

        updatableInjectionContext.inject(RestaurantsDeliveryRepositoryImpl.class).as(RestaurantsDeliveryRepositoryInterface.class);
        updatableInjectionContext.inject(RestaurantsDeliveryServiceImpl.class).as(RestaurantsDeliveryServiceInterface.class);

        Geocoder geocoder = new Geocoder();
        updatableInjectionContext.inject(geocoder).as(Geocoder.class);

    }





}
