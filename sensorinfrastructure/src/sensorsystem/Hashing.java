/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensorsystem;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//import static sensorserver.SensorGatherKlient.handshakeLog;

/**
 *
 * @author triblex
 */
public class Hashing {
    
    public String stringHash(String text) throws NoSuchAlgorithmException {

        //client hashing the handshake
        
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        
        md.update(text.getBytes());
        
        byte byteData[] = md.digest();

        //convert the byte to hex format
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < byteData.length; i++) {
        
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        
        }

        System.out.println("Hex format : " + sb.toString());
        
        return sb.toString();
    }
}
