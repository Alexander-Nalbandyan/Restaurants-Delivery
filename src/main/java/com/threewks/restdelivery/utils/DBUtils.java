package com.threewks.restdelivery.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 6/9/13
 * Time: 4:22 PM
 *
 * Provides Database utility functions.
 */
public class DBUtils {

    /**
     * Make the DBUtils class not instantiable.
     */
    private DBUtils() {}

    public static void closeAll(ResultSet rs, PreparedStatement pst,
                                Connection conn){
        try { if (rs != null) rs.close(); } catch(Exception e) { }
        try { if (pst != null) pst.close(); } catch(Exception e) { }
        try { if (conn != null) conn.close(); } catch(Exception e) { }
    }
}
