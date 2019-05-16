/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.niit.simplewebapp.conn;

/**
 *
 * @author Zinachi Computer
 */
import java.sql.Connection;
import java.sql.SQLException;
 
public class ConnectionUtils {
 
    public static Connection getConnection() 
              throws ClassNotFoundException, SQLException {
 
        // Here am using MySQL Database.
        // (You can change to use another database.)
        return MySQLConnUtils.getMySQLConnection();
         
        // return OracleConnUtils.getOracleConnection();
        // return OracleConnUtils.getOracleConnection();
        // return SQLServerConnUtils_JTDS.getSQLServerConnection_JTDS();
        // return SQLServerConnUtils_SQLJDBC.getSQLServerConnection_SQLJDBC();
        // return PostGresConnUtils.getPostGresConnection();
    }
     
    public static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
        }
    }
 
    public static void rollbackQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
        }
    }
}
