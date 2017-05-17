/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockingsystem;

import javax.xml.ws.Endpoint;
import sensorsystem.SensorSystem;

public class DockPubSOAP {
    
    static String onlinehttp = "http://localhost:7777/sensor"; // Javabog.dk
    
    public static void publish(SensorSystem _sensorsystem) {        
        System.out.println("Setting up SOAP API WSDL @:");      
        Endpoint.publish(onlinehttp, new DockImpSOAP(_sensorsystem));        
        System.out.println("WSDL Generated.");
    }
    
}
