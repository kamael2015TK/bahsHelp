/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockingsystem;

import java.security.NoSuchAlgorithmException;

/**
 *
 * @author nb
 */
public interface DockIntRMI extends java.rmi.Remote {

    public boolean transferDataRMI(String eUsername, String ePassword, String eData, int count, int Sensor_ID) throws java.rmi.RemoteException, Exception;

    public int requestConnection(String name, String location, String unit, int ownerID, int Sensor_ID) throws java.rmi.RemoteException;

    public String getNonsense(int Sensor_ID) throws java.rmi.RemoteException;

    public String getPublicKey(int Sensor_ID) throws java.rmi.RemoteException;

    public void sendCipherInonsense(int Sensor_ID, String encryptedMessage) throws java.rmi.RemoteException;

    public void sendLogHashCipher(int Sensor_ID, String hashLog) throws java.rmi.RemoteException;

    public boolean recieveOK(int Sensor_ID) throws NoSuchAlgorithmException, java.rmi.RemoteException;

}
