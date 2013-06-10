package com.threewks.restdelivery;


import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Provides database connection pulling.
 */
public class DBConnector {
	private static Logger LOGGER = Logger.getLogger(DBConnector.class);

	private static BasicDataSource dbDataSource;

    static {
        init();
    }

	public static void init()
	{
        try {
            String dBUrl = PropertyConfig.getDbHost();
            String dBUserName = PropertyConfig.getDbUser();
            String dBPassword = PropertyConfig.getDbPass();

            dbDataSource = new BasicDataSource();
            dbDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dbDataSource.setUrl(dBUrl);
            dbDataSource.setUsername(dBUserName);
            dbDataSource.setPassword(dBPassword);
            dbDataSource.setInitialSize(1);
            dbDataSource.setMaxActive(1);
            dbDataSource.setValidationQuery("SELECT 1");
        }   catch (Throwable e) {
            e.printStackTrace();
        }
	}

	public static Connection getConnection() throws SQLException {
        Connection conn;
		try{
			conn = dbDataSource.getConnection();
		}catch(SQLException e){
            LOGGER.error("Could not connect to: \""+ dbDataSource.getUrl()+"\"");
            throw  e;
		}

		return conn;
	}

    public static DataSource getDataSource() {
        return dbDataSource;
    }


}
