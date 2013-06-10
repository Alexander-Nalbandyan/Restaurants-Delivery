package com.threewks.restdelivery;


import org.apache.log4j.Logger;

import java.util.Properties;

/**
 * Contains database configuration information(hostname, username and password).
 */
public final class PropertyConfig {
	
	private static Properties db_properties;
	
	private static final Logger LOGGER = Logger.getLogger(PropertyConfig.class);

	static {
		loadDBSettings();
	}
	
	// Get settings of: db.properties
	// -------------------------------------------------------------------------
	public static void loadDBSettings(){
		if(db_properties == null) {
            db_properties = new Properties();
        } else {
            db_properties.clear();
        }

		try {
			db_properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
		} catch (Exception e){
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}		
	}

	public static String getDbHost(){
		return db_properties.getProperty("db_url");
	}
	public static String getDbUser(){
		return db_properties.getProperty("db_user");
	}
	public static String getDbPass(){
		return db_properties.getProperty("db_password");
	}
}
