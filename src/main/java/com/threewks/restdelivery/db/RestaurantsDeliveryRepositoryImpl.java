package com.threewks.restdelivery.db;

import com.threewks.restdelivery.db.dto.RestaurantDto;
import com.threewks.restdelivery.db.dto.UserAddressForRestaurantDto;
import com.threewks.restdelivery.utils.DBUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 6/9/13
 * Time: 4:05 PM
 *
 * Implementation of restaurants delivery repository.
 */
public class RestaurantsDeliveryRepositoryImpl implements RestaurantsDeliveryRepositoryInterface {

    private DataSource dataSource;

    public RestaurantsDeliveryRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    @Override
    public List<RestaurantDto> selectAllRestaurants() throws SQLException {

        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SQLConstants.SQL_SELECT_RESTAURANTS);
            preparedStatement.execute();

            resultSet = preparedStatement.getResultSet();

            List<RestaurantDto> restaurantDtos = new ArrayList<RestaurantDto>();

            while (resultSet.next()) {
                RestaurantDto restaurantDto = new RestaurantDto();
                restaurantDto.setId(resultSet.getInt(1));
                restaurantDto.setName(resultSet.getString(2));
                restaurantDto.setAddress(resultSet.getString(3));
                restaurantDto.setLatitude(resultSet.getDouble(4));
                restaurantDto.setLongitude(resultSet.getDouble(5));
                restaurantDto.setDeliveryRadius(resultSet.getDouble(6));

                restaurantDtos.add(restaurantDto);
            }

            return restaurantDtos;


        } finally {
            DBUtils.closeAll(resultSet, preparedStatement, connection);
        }


    }

    @Override
    public RestaurantDto selectRestaurantInfo(Integer restaurantId) throws SQLException {


        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SQLConstants.SQL_SELECT_RESTAURANT_BY_ID);
            preparedStatement.setInt(1, restaurantId);
            preparedStatement.execute();

            resultSet = preparedStatement.getResultSet();

            RestaurantDto restaurantDto = null;

            if (resultSet.next()) {
                restaurantDto = new RestaurantDto();
                restaurantDto.setId(resultSet.getInt(1));
                restaurantDto.setName(resultSet.getString(2));
                restaurantDto.setAddress(resultSet.getString(3));
                restaurantDto.setLatitude(resultSet.getDouble(4));
                restaurantDto.setLongitude(resultSet.getDouble(5));
                restaurantDto.setDeliveryRadius(resultSet.getDouble(6));
            }

            return restaurantDto;


        } finally {
            DBUtils.closeAll(resultSet, preparedStatement, connection);
        }

    }

    @Override
    public void insertUserAddressForRestaurant(UserAddressForRestaurantDto userAddressForRestaurantDto) throws SQLException {


        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SQLConstants.SQL_INSERT_USER_ADDRESS_FOR_RESTAURANT);
            preparedStatement.setInt(1, userAddressForRestaurantDto.getUserId());
            preparedStatement.setInt(2, userAddressForRestaurantDto.getRestaurantId());
            preparedStatement.setString(3, userAddressForRestaurantDto.getUserAddress());
            preparedStatement.setDouble(4, userAddressForRestaurantDto.getLatitude());
            preparedStatement.setDouble(5, userAddressForRestaurantDto.getLongitude());
            preparedStatement.executeUpdate();

        } finally {
            DBUtils.closeAll(resultSet, preparedStatement, connection);
        }

    }
}
