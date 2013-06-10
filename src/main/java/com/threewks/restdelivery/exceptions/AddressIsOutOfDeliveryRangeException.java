package com.threewks.restdelivery.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 6/9/13
 * Time: 6:27 PM
 *
 * Thrown when address is out of restaurant delivery range.
 */
public class AddressIsOutOfDeliveryRangeException extends Exception {
    public AddressIsOutOfDeliveryRangeException(String message) {
        super(message);
    }

    public AddressIsOutOfDeliveryRangeException(String message, Throwable cause) {
        super(message, cause);
    }
}
