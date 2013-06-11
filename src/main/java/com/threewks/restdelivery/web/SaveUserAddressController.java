package com.threewks.restdelivery.web;

import com.threewks.restdelivery.exceptions.AddressIsOutOfDeliveryRangeException;
import com.threewks.restdelivery.service.RestaurantsDeliveryServiceInterface;
import com.threewks.thundr.view.jsp.JspView;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 6/9/13
 * Time: 1:10 PM
 *
 * Handles Requests for saving user address info.
 */
public class SaveUserAddressController {

    private Logger logger = Logger.getLogger(SaveUserAddressController.class);

    private RestaurantsDeliveryServiceInterface restDeliveryService;

    public SaveUserAddressController(RestaurantsDeliveryServiceInterface restDeliveryService) {
        this.restDeliveryService = restDeliveryService;
    }

    public JspView handleGet() throws SQLException {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("restaurants", restDeliveryService.getAllRestaurants());
            return new JspView("pages/save-user-address.jsp", model);
    }

    public JspView handlePost(String address, Long restaurantId) throws SQLException {
        Map<String, Object> model = new HashMap<String, Object>();
        try {
            model.put("restaurants", restDeliveryService.getAllRestaurants());

            restDeliveryService.saveUserAddress(address, restaurantId);

            model.put("resultStatus", "success");
            return new JspView("pages/save-user-address.jsp", model);
        } catch (AddressIsOutOfDeliveryRangeException e) {
            model.put("address", address);
            model.put("restaurantId", restaurantId);
            model.put("resultStatus", "error");

            return new JspView("pages/save-user-address.jsp", model);
        }
    }
}
