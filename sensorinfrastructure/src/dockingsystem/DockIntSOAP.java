/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockingsystem;

import java.security.NoSuchAlgorithmException;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService

@SOAPBinding(style = SOAPBinding.Style.RPC)

public interface DockIntSOAP {
    
    @WebMethod boolean transferDataSOAP(String username, String password, String data, int count, int Sensor_ID);
    @WebMethod public int requestConnectionSOAP(String name, String location, String unit, int ownerID, int Sensor_ID);
    @WebMethod public String getNonsenseSOAP(int Sensor_ID);
    @WebMethod public String getPublicKeySOAP(int Sensor_ID) throws NoSuchAlgorithmException;
    @WebMethod public void sendCipherInonsenseSOAP(int Sensor_ID, String encryptedMessage);
    @WebMethod public void sendLogHashCipherSOAP(int Sensor_ID, String hashLog);
    @WebMethod public boolean recieveOKSOAP(int Sensor_ID) throws NoSuchAlgorithmException;

}