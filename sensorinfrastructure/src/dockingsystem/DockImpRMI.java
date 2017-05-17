/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockingsystem;

import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
import sensorsystem.SensorSystem;

public class DockImpRMI extends UnicastRemoteObject implements DockIntRMI {

    SensorSystem sensorsystem;

    public DockImpRMI(SensorSystem _sensorsystem) throws java.rmi.RemoteException {

        sensorsystem = _sensorsystem;

    }

    @Override
    public boolean transferDataRMI(String eUsername, String ePassword, String eData, int count, int Sensor_ID) throws java.rmi.RemoteException, Exception {

        return sensorsystem.transferData(eUsername, ePassword, eData, count, Sensor_ID);
    }

    @Override
       public int requestConnection(String name, String location, String unit, int ownerID, int Sensor_ID) throws java.rmi.RemoteException {
        return sensorsystem.requestConnection(name, location, unit, ownerID, Sensor_ID);
    }

    @Override
    public String getNonsense(int Sensor_ID) throws java.rmi.RemoteException {
        return sensorsystem.getNonsense(Sensor_ID);
    }

    @Override
    public String getPublicKey(int Sensor_ID) throws java.rmi.RemoteException {
        try {
            return sensorsystem.getPublicKey(Sensor_ID);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void sendCipherInonsense(int Sensor_ID, String encryptedMessage) throws java.rmi.RemoteException {
        sensorsystem.sendCipherInonsense(Sensor_ID, encryptedMessage);
    }

    @Override
    public void sendLogHashCipher(int Sensor_ID, String hashLog) throws java.rmi.RemoteException {
        sensorsystem.sendLogHashCipher(Sensor_ID, hashLog);
    }

    @Override
    public boolean recieveOK(int Sensor_ID) throws NoSuchAlgorithmException, java.rmi.RemoteException {
        return sensorsystem.recieveOK(Sensor_ID);
    }
}
