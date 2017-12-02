/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author dadsadsadasdsa
 */
public class TestMySQL {
    public static void main(String[] args) throws Exception {
        
        Properties connProps = new Properties();
        
        //connection properties to connect to the MySQL database; omitted for security purposes
        connProps.put("user", "username");
        connProps.put("password", "password");
        connProps.put("useSSL", "true");
        
        //connection object that conneects to the MySQL URL
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms_name?;create=true", connProps);
        
        //statement object that allows execution of MySQL queries and DDL statements
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        Statement dbStmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        
        //database name
        String dbName = "db_name";
        //table name
        String tblName = "table_1";
        //variable to combine database name to the table name
        String dbTblName = dbName + "." + tblName;
        
        //create array list to store columns
        ArrayList<String> dbColArr = new ArrayList<String>();
        
        //add column names to the column array list
        dbColArr.add("Column_1");
        dbColArr.add("Column_2");
        dbColArr.add("Column_3");
        
        //create variable to store SQL statement to create a schema (or database)
        String createSchema = "CREATE DATABASE IF NOT EXISTS " + dbName;
        
        //create variable to store SQL statement to create a table
        String createTable =
	//dbName = DB name you're making the table in
        "CREATE TABLE IF NOT EXISTS " + dbTblName + " \n" +
        //add column that behaves as the table's primary key
        "(" + dbColArr.get(0) + " integer NOT NULL AUTO_INCREMENT PRIMARY KEY, \n" +
        //add column that stores varchar data types
        dbColArr.get(1) + " varchar(40) NOT NULL DEFAULT 'EMPTY', \n" +
        //add column that stores date data
        dbColArr.get(2) + " DATE);";
        
        //execute statement to create table
        dbStmt.executeUpdate(createSchema);
        stmt.executeUpdate(createTable);
    }
}
