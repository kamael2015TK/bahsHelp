/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockingsystem;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import sensorsystem.SensorSystem;

/**
 *
 * @author nb
 */
public class DockPubRMI {
    
    static String onlineaddress = "rmi://localhost:53712/sensorRMI";
    
    public static void publish(SensorSystem _sensorsystem) throws NoSuchAlgorithmException, RemoteException, MalformedURLException {
        
        System.out.println("Starting RMI Sensor Data interface");

        java.rmi.registry.LocateRegistry.createRegistry(53712);

        DockImpRMI si = new DockImpRMI(_sensorsystem);// creates object for RMI Sensor data tranfer            

            Naming.rebind(onlineaddress, (Remote) si);            
            System.out.println(onlineaddress);
        
    }
    
}