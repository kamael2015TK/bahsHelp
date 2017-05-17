/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasystem;

import SensorDataType.SensorDataType;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import sensorsystem.SensorSystem;
/**
 *
 * @author nb
 */
public class DataCollector extends Thread {

    DataControl offdata;
    DataControl expdata;
    SensorControl sensors;
    UserControl users;
    private final Object lock;
    SensorSystem sensorsystem;

    public DataCollector(DataControl _offdata, DataControl _expdata, SensorControl _sensors, UserControl _users, SensorSystem _sensorsystem) {

        System.out.println("Creating thread.");

        offdata = _offdata;
        expdata = _expdata;
        sensors = _sensors;
        users = _users;
        sensorsystem = _sensorsystem;
        lock = new Object();

    }

    @Override
    public void run() {

        while (true) {

            System.out.println("Checking for data...");

            if (sensorsystem.dataWaiting()) {
                System.out.println("Uploading data...");
                SensorDataType data;
                synchronized (lock) {            

                    data = sensorsystem.receiveData();
                }                
                if(data.table.equals("Basic")){
                    expdata.insertData(data.Sensor_ID, data.Location, data.Type, data.Unit, data.Value, data.Date, data.Checksum);
                    System.out.println("Data is inserted into db");
                }else{
                    offdata.insertData(data.Sensor_ID, data.Location, data.Type, data.Unit, data.Value, data.Date, data.Checksum);
                    System.out.println("Data is inserted into db");
                }
                
            } else {

                try {
                    // Wait for 1 seconds and then check again.

                    System.out.println("No data, going to sleep...");

                    sleep(10000);

                } catch (InterruptedException ex) {

                    Logger.getLogger(DataCollector.class.getName()).log(Level.SEVERE, null, ex);

                }

            }

        }

    }

}
