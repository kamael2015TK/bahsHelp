/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensorDataType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author taras
 */
public class SensorDataType {

    public int Data_ID;
    public int Sensor_ID;
    public String Location;
    public String Type;
    public String Unit;
    public float Value;
    public String Date;
    public int Checksum;
    public String table;

    
    public SensorDataType(){
    
    }
    public SensorDataType(String s) throws NumberFormatException {
        UpdateAll(s);

    }

    public SensorDataType(ResultSet obj) throws SQLException {

        UpdateAll(obj);

    }

    /**
     * Object to String
     *
     * @return
     */
    public String objToString() {
        return (Data_ID + " " + Sensor_ID + " " + Location + " " + Type + " " + Unit + " " + Value + " " + Date + " " + Checksum);
    }

    public void UpdateAll(ResultSet obj) throws SQLException {
        try {
            Data_ID = (int) obj.getObject(1);
            Sensor_ID = (int) obj.getObject(2);
            Location = (String) obj.getObject(3);
            Type = (String) obj.getObject(4);
            Unit = (String) obj.getObject(5);
            Value = (float) obj.getObject(6);
            Date = ((Timestamp) obj.getObject(7)).toString();
            Checksum = (int) obj.getObject(8);
        } catch (SQLException ex) {
            throw new SQLException("ERROR converting object and " + ex.getMessage());
        }
    }
    public void UpdateAll(String s){
    String[] parts = s.split(" ");

        try {
            Data_ID = Integer.parseInt(parts[1]);
            try {
                Sensor_ID = Integer.parseInt(parts[0]);
                Location = parts[2];
                Type = parts[3];
                Unit = parts[4];
                Value = Float.parseFloat(parts[5]);
                Date = parts[6] + " " + parts[7];
                Checksum = Integer.parseInt(parts[8]);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("ERROR converting object and " + e.getMessage());
            }

        } catch (NumberFormatException e) {
            try {
                Data_ID = -1;
                Sensor_ID = Integer.parseInt(parts[0]);
                Location = parts[1];
                Type = parts[2];
                Unit = parts[3];
                Value = Float.parseFloat(parts[4]);
                Date = parts[5] + " " + parts[6];
                Checksum = Integer.parseInt(parts[7]);
            }catch(NumberFormatException ex) {
                throw new NumberFormatException("ERROR converting object and " + ex.getMessage());
            }
        }
    }
}
