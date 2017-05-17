/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockingsystem;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import sensorsystem.SensorSystem;

@WebService(endpointInterface = "dockingsystem.DockIntSOAP")

public class DockImpSOAP implements DockIntSOAP {

    private final SensorSystem sensorsystem;

    DockImpSOAP(SensorSystem _sensorsystem) {
       sensorsystem = _sensorsystem;
    }
    
    @Override
    public boolean transferDataSOAP(String username, String password, String data, int count, int Sensor_ID) {
        
        try {
            return sensorsystem.transferData(username, password, data, count, Sensor_ID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    return false;
    }
    
    @Override
    public int requestConnectionSOAP(String name, String location, String unit, int ownerID, int Sensor_ID) {
        
        return sensorsystem.requestConnection(name, location, unit, ownerID, Sensor_ID);
    
    }
    
    @Override
    public String getNonsenseSOAP(int Sensor_ID) {
        
        return sensorsystem.getNonsense(Sensor_ID);
    
    }
    
    @Override
    public String getPublicKeySOAP(int Sensor_ID) throws NoSuchAlgorithmException {
        
        return sensorsystem.getPublicKey(Sensor_ID);
    
    }
    
    @Override
    public void sendCipherInonsenseSOAP(int Sensor_ID, String encryptedMessage) {
        
        sensorsystem.sendCipherInonsense(Sensor_ID, encryptedMessage);
        
    }
    
    @Override
    public void sendLogHashCipherSOAP(int Sensor_ID, String hashLog) {
        
        sensorsystem.sendLogHashCipher(Sensor_ID, hashLog);
        
    }
    
    @Override
    public boolean recieveOKSOAP(int Sensor_ID) throws NoSuchAlgorithmException {
        
        return sensorsystem.recieveOK(Sensor_ID);
        
    }
}