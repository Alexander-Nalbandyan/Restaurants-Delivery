package com.threewks.restdelivery;

import com.google.code.geocoder.Geocoder;
import com.threewks.restdelivery.db.RestaurantsDeliveryRepositoryImpl;
import com.threewks.restdelivery.db.RestaurantsDeliveryRepositoryInterface;
import com.threewks.restdelivery.service.RestaurantsDeliveryServiceImpl;
import com.threewks.restdelivery.service.RestaurantsDeliveryServiceInterface;
import com.threewks.thundr.injection.InjectionConfiguration;
import com.threewks.thundr.injection.UpdatableInjectionContext;
import org.apache.commons.dbcp.*;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        //Database datasource injection
        String dbUrl = updatableInjectionContext.get(String.class, "db_url");
        String dbUser = updatableInjectionContext.get(String.class, "db_user");
        String dbPass = updatableInjectionContext.get(String.class, "db_password");
        DataSource dataSource = setUpDataSource(dbUrl, dbUser, dbPass);

        updatableInjectionContext.inject(dataSource).as(DataSource.class);
        updatableInjectionContext.inject(RestaurantsDeliveryRepositoryImpl.class).as(RestaurantsDeliveryRepositoryInterface.class);
        updatableInjectionContext.inject(RestaurantsDeliveryServiceImpl.class).as(RestaurantsDeliveryServiceInterface.class);

        Geocoder geocoder = new Geocoder();
        updatableInjectionContext.inject(geocoder).as(Geocoder.class);
    }

    private DataSource setUpDataSource(String dbUrl, String dbUser, String dbPass) {
        //Data source configuration.
        BasicDataSource dbDataSource = new BasicDataSource();
        try {


            dbDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dbDataSource.setUrl(dbUrl);
            dbDataSource.setUsername(dbUser);
            dbDataSource.setPassword(dbPass);
            dbDataSource.setInitialSize(2);
            dbDataSource.setMaxActive(5);
            dbDataSource.setValidationQuery("SELECT 1");
        }   catch (Throwable e) {
            logger.error("Could not initialize db data source");
        }

        return dbDataSource;

    }




}
