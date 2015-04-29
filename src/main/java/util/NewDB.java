package main.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * Created by Artsiom Tratsiuk
 */
public class NewDB{
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/";

    //  Database credentials
    private static final String USER = "root";
    private static final String PASS = "";

    public static boolean checkDBExists(String dbName){

        try{
            Connection conn = null;

            //Register JDBC Driver
            Class.forName("com.mysql.jdbc.Driver");
            //Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            ResultSet resultSet = conn.getMetaData().getCatalogs();

            while (resultSet.next()) {
                String databaseName = resultSet.getString(1);
                if(databaseName.equals(dbName)){
                    return true;
                }
            }
            resultSet.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static String getDB_URL() {
        return DB_URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getPASS() {
        return PASS;
    }

    public static String getJDBC_DRIVER() {

        return JDBC_DRIVER;
    }
}
