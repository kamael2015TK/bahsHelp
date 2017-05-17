/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensorDataType;

import java.sql.Timestamp;
import java.util.ArrayList;
import sensorsystem.Crypt;
import sensorsystem.Hashing;
import sensorsystem.StringGen;

/**
 *
 * @author nb
 */
public class Sensor {
    public StringGen sg;
public Sensor(){
    sg = new StringGen();
}
    public Crypt c;
    public Hashing h = new Hashing();
    //public StringGen sg = new StringGen();
    
    public String nonsense;
    public String inonsense;
    public String decodeNonsense;
    public String XORNonsense;
    public String publicKey;
    public String ServerHandshakeLogHash;
    public String ClientHandshakeLogHash;
    public String handshakeLog;
    public String XORNonsenseHex;
    public ArrayList<String> hsl = new ArrayList<>();
    public int count = 0;
    
    // Sensor Database
    
    public int ID;
    public String Name;
    public String Location;
    public String Unit;
    public int Owner;
    public java.sql.Timestamp SetupTime;
    public int Status;
    public int DB_Status;
    public String Token;    
    public String PrimaryKey;
    
}
