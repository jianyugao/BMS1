package com.bms.utils;

import java.sql.*;


/**
 * Created by xuxu on 7/27/16.
 */
public class JDBCUtils {

    private static Connection conn = null;

    static {
        try {
            //load jdbc driver class
            Class.forName("com.mysql.jdbc.Driver");
            //get connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bms", "root", "root");
            //System.out.println("conn");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Connection getConn() {
        return conn;
    }

    public static void close(ResultSet rs, Statement ps, Connection conn) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            if(conn != null){
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        try{
            if(rs != null){
                rs.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
