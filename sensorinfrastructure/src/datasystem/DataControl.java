/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasystem;

import SensorDataType.SensorDataType;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author taras
 */
public class DataControl {
    /*
    private String std_dbname = "jdbc:mysql://localhost/";
    private String std_uname = "root";
    private String std_password = "";
    /**/
    
    private String std_dbname = "jdbc:mysql://ubuntu4.javabog.dk:53067/";
    private String std_uname = "root";
    private String std_password = "sensorDB";/**/
    
    private String DBName = "DataBase";
    private String DB_table;
    private Connection con;
    
    
    public DataControl(String dbName) throws SQLException{
            DB_table = dbName;
            
        try{
            con = DriverManager.getConnection(std_dbname+DBName, std_uname, std_password);
            
            System.out.println("Connected!");
               
            Statement stmt_exp_data;
            
            String sql = "CREATE TABLE IF NOT EXISTS `"+DB_table+"` ("
                    + "`Data_ID` int(11) NOT NULL AUTO_INCREMENT,"
                    + "`Sensor_ID` int(11) NOT NULL,"
                    + "`Location` text NOT NULL,"
                    + "`Type` text NOT NULL,"
                    + "`Unit` text NOT NULL,"
                    + "`Value` float(11) NOT NULL,"
                    + "`Date` timestamp NOT NULL,"
                    + "`Checksum` int(11) NOT NULL,"
                    + " PRIMARY KEY (`Data_ID`)"
                    + ") ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1";
                       
            stmt_exp_data = con.createStatement();             
            stmt_exp_data.executeUpdate(sql);
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot connect the database!" + ex.getMessage());
        }
    }
    /**
     *
     * @param SensID - What sensor is writing
     * @param loc - location of the server
     * @param type - type of the sensor
     * @param unit - unit of data
     * @param value - value of the data
     * @param s - date as a string type 2017-03-12 22:00:00
     * @param chsm - check sum inserts data into the table
     * 
     * Inserts data into DB 
     */
    public void insertData(int SensID, String loc, String type, String unit, float value, String s, int chsm) {
        try {
            
            /// dette funktion kan opdateres....... så det gemmer alt på et hug 
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO `" + DBName + "`.`"+DB_table+"` "
                    + "(`Data_ID`, `Sensor_ID`, `Location`, `Type`, `Unit`, `Value`, `Date`, `Checksum`) VALUES "
                    + "(NULL, '" + SensID + "', '" + loc + "', '" + type + "', '" + unit + "', '" + value + "', '" + s + "', '" + chsm + "');";
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
            System.out.println("no");
        }
    }

    /**
     *
     * @param ID
     * @return array with SensorData object that are requested by ID
     */
    public ArrayList<String> getAllBySensorID(int ID){

        String req = "SELECT * FROM `"+DB_table+"` WHERE `Sensor_ID` = " + ID;
        return getData(req);
    }
    /**
     * 
     * @param ID
     * @param start
     * @param end
     * @return 
     * 
     * Returns ArrayList<String> by  Sensor ID and date interval
     */
    public ArrayList<String> getIntervalBySensorID(int ID, Date start, Date end){

        String req = "SELECT * FROM `"+DB_table+"` WHERE `Sensor_ID` = " + ID + " and `Date` >= '" + start.toString() + " 00:00:00' and `Date` <= '" + end + " 23:59:59'";
        return getData(req);
    }
    
    /**
     * 
     * @param type
     * @return ArrayList<String> by Sensor Data type
     */
    public ArrayList<String> getAllByType(int type){

        String req = "SELECT * FROM `"+DB_table+"` WHERE `Type` = " + type;
        return getData(req);
    }
    /**
     * 
     * @param type
     * @param start
     * @param end
     * @return ArrayList<String> by Sensor Data type and date intervall
     */
    public ArrayList<String> getIntervalByType(int type, Date start, Date end){

        String req = "SELECT * FROM `"+DB_table+"` WHERE `Type` = " + type + " and `Date` >= '" + start.toString() + " 00:00:00' and `Date` <= '" + end + " 23:59:59'";
        return getData(req);
    }
    
    /**
     * 
     * @param loc
     * @return ArrayList<String>  by sensor location 
     */
    public ArrayList<String> getAllByLocation(String loc){

        String req = "SELECT * FROM `"+DB_table+"` WHERE `Location` = " + loc;
        return getData(req);
    }
    
    /**
     * 
     * @param loc
     * @param start
     * @param end
     * @return ArrayList<String>  by sensor location and date intervall 
     */
    public ArrayList<String> getIntervalByLocation(String loc, Date start, Date end){

        String req = "SELECT * FROM `"+DB_table+"` WHERE `Location` = " + loc + " and `Date` >= '" + start.toString() + " 00:00:00' and `Date` <= '" + end + " 23:59:59'";
        return getData(req);
    }
    
    /**
     * 
     * @param d
     * @return ArrayList<String> by date 
     */
    public ArrayList<String> getAllByDate(Date d){

        String req = "SELECT * FROM `"+DB_table+"` WHERE `Date` = " + d;
        return getData(req);
    }
    
    /**
     * 
     * @param start
     * @param end
     * @return ArrayList<String>  by date intervall 
     */
    public ArrayList<String> getIntervalByDate(Date start, Date end){

        String req = "SELECT * FROM `"+DB_table+"` WHERE `Date` >= '" + start + " 00:00:00' and `Date` <= '" + end + " 23:59:59'";
        return getData(req);
    }
    
    /**
     * 
     * @param sql here you can write direct SQL command 
     * @return ArrayList<String> 
     */
    public ArrayList<String> directSQL(String sql){

        // securing for abuse
        String temp_sql = sql.toLowerCase();
        if (!temp_sql.contains("update") && !temp_sql.contains("delete") && !temp_sql.contains("insert") && !temp_sql.contains("create") && !temp_sql.contains("alter")) {
            return getData(sql);
        } else {
            return null;
        }
    }

        public ArrayList<String> GetSensorIDList(){
        String sql = "SELECT DISTINCT Sensor_ID FROM "+DB_table;
        ArrayList<String> temp = new ArrayList<>();
        try {
            Statement stmt = con.createStatement(); // creates new sql statment
            ResultSet rs = null;
            rs = stmt.executeQuery(sql);  //executes query  
            while (rs.next()) {         // while more data to read 
                temp.add(rs.getString("Sensor_ID"));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
            System.out.println("ERROR GETTING LIST");
        }
        return temp;
        
    }
    public ArrayList<String> GetLocationList(){
        String sql = "SELECT DISTINCT Location FROM "+DB_table;
        
        ArrayList<String> temp = new ArrayList<>();
        try {
            Statement stmt = con.createStatement(); // creates new sql statment
            ResultSet rs = null;
            rs = stmt.executeQuery(sql);  //executes query  
            while (rs.next()) {         // while more data to read 
                temp.add(rs.getString("Location"));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
            System.out.println("ERROR GETTING LIST");
        }
        return temp;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    /**
     * 
     * @param s
     * @return ArrayList<DataStruct>
     * 
     * This Executes sql command given by prev functions
     */
    private ArrayList<String> getData(String s) {
        ArrayList<String> data = new ArrayList<>(); // new Array list of the data
        try {
            Statement stmt = con.createStatement(); // creates new sql statment
            ResultSet rs = null;
            rs = stmt.executeQuery(s);  //executes query  
            SensorDataType test = new SensorDataType();
            while (rs.next()) {         // while more data to read 
                test.UpdateAll(rs);
                data.add(test.objToString());
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
            System.out.println("Data convertion error... this should not happen");
        }
        return data;
    }    
    

}

    
