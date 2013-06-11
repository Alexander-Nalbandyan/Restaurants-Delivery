package com.threewks.restdelivery.service;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.code.geocoder.Geocoder;
import com.threewks.restdelivery.repository.RestaurantsDeliveryRepositoryImpl;
import com.threewks.restdelivery.repository.RestaurantsDeliveryRepositoryInterface;
import com.threewks.restdelivery.exceptions.AddressIsOutOfDeliveryRangeException;
import com.threewks.restdelivery.repository.entity.UserAddressForRestaurant;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static com.threewks.restdelivery.repository.OfyService.ofy;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 6/9/13
 * Time: 10:24 PM
 * <p/>
 * Contains tests for RestaurantDeliveryService.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class RestaurantsDeliveryServiceTests {

    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    private RestaurantsDeliveryServiceInterface restaurantsDeliveryService;

    @Before
    public void prepare() {
        helper.setUp();


        RestaurantsDeliveryRepositoryInterface restDeliveryRep = new RestaurantsDeliveryRepositoryImpl();
        Geocoder geocoder = new Geocoder();
        this.restaurantsDeliveryService = new RestaurantsDeliveryServiceImpl(restDeliveryRep, geocoder);
    }

    @Test
    public void testSuccessSaveUserAddressForRest() {
        String address = "737 N Central Ave";
        Long restaurantId = 1L;

        try {
            restaurantsDeliveryService.saveUserAddress(address, restaurantId);
            UserAddressForRestaurant userAddressForRestaurant = ofy().load().type(UserAddressForRestaurant.class).first().now();
            if (userAddressForRestaurant != null) {
                Assert.assertEquals("Saved restaurant id didn't match", restaurantId, userAddressForRestaurant.getRestaurantId());
                Assert.assertEquals("Saved address didn't match", address, userAddressForRestaurant.getUserAddress());
            } else {
                Assert.fail("User Address Was not saved for restaurant");
            }

        } catch (AddressIsOutOfDeliveryRangeException e) {
            Assert.fail("Unexpected error address must be saved successfully");
        }


    }

    @Test(expected = AddressIsOutOfDeliveryRangeException.class)
    public void testUnsuccessfulSaveUserAddressForRest() throws AddressIsOutOfDeliveryRangeException {
        String address = "Paris, France";
        Long restaurantId = 1L;
        restaurantsDeliveryService.saveUserAddress(address, restaurantId);
        Assert.fail("Save user address must throw Address is out of delivery range exception");
    }

    @After
    public void cleanup() {
        helper.tearDown();
    }

}
