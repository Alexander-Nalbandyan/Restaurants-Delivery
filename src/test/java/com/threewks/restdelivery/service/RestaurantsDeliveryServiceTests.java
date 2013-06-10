package com.threewks.restdelivery.service;

import com.google.code.geocoder.Geocoder;
import com.threewks.restdelivery.DBConnector;
import com.threewks.restdelivery.db.RestaurantsDeliveryRepositoryImpl;
import com.threewks.restdelivery.db.RestaurantsDeliveryRepositoryInterface;
import com.threewks.restdelivery.exceptions.AddressIsOutOfDeliveryRangeException;
import com.threewks.restdelivery.utils.DBUtils;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 6/9/13
 * Time: 10:24 PM
 *
 * Contains tests for RestaurantDeliveryService.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class RestaurantsDeliveryServiceTests {

    private RestaurantsDeliveryServiceInterface restaurantsDeliveryService;

    @Before
    public void prepare() {
        RestaurantsDeliveryRepositoryInterface restDeliveryRep = new RestaurantsDeliveryRepositoryImpl(DBConnector.getDataSource());
        Geocoder geocoder = new Geocoder();
        this.restaurantsDeliveryService = new RestaurantsDeliveryServiceImpl(restDeliveryRep, geocoder);
    }

    @Test
    public void testSuccessSaveUserAddressForRest() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            String address = "737 N Central Ave";
            int restaurantId = 1;
            restaurantsDeliveryService.saveUserAddress(address, restaurantId);
            connection = DBConnector.getConnection();
            preparedStatement = connection.prepareStatement("SELECT restaurant_id,user_address  FROM user_address_for_restaurant WHERE user_address = ?");
            preparedStatement.setString(1, address);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Assert.assertEquals("Saved restaurant id didn't match", restaurantId, resultSet.getInt(1));
                Assert.assertEquals("Saved address didn't match", address, resultSet.getString(2));
            } else {
                Assert.fail("User Address Was not save for restaurant");
            }
        } catch (AddressIsOutOfDeliveryRangeException e) {
            Assert.fail("Unexpected error address must be saved successfully");
        } catch (SQLException e) {
            Assert.fail("Unexpected error address must be saved successfully");
        } finally {
            DBUtils.closeAll(null, preparedStatement, connection);
        }

    }

    @Test(expected = AddressIsOutOfDeliveryRangeException.class)
    public void testUnsuccessfulSaveUserAddressForRest() throws AddressIsOutOfDeliveryRangeException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;


        try {
            String address = "Paris, France";
            int restaurantId = 1;
            restaurantsDeliveryService.saveUserAddress(address, restaurantId);
            connection = DBConnector.getConnection();

            preparedStatement = connection.prepareStatement("SELECT restaurant_id,user_address  FROM user_address_for_restaurant WHERE user_address = ?");
            preparedStatement.setString(1, address);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Assert.fail("User Address Was saved in the database");
            }
        } catch (SQLException e) {
            Assert.fail("Unexpected error for unsuccessful save");
        } finally {
            DBUtils.closeAll(null, preparedStatement, connection);
        }


    }

    @After
    public void cleanup() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnector.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM user_address_for_restaurant");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            DBUtils.closeAll(null, preparedStatement, connection);
        }
    }

}
