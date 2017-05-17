/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcommunication;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author taras
 */
public interface WebInterface extends java.rmi.Remote {
    
    //Data access functions
    ArrayList<String> CallgetAllBySensorID(String data, int ID)                                  throws java.rmi.RemoteException;
    ArrayList<String> CallgetIntervalBySensorID(String data, int ID, Date start, Date end)       throws java.rmi.RemoteException;
    boolean CallLogin(String username, String password)                                          throws java.rmi.RemoteException;
    public String getMessage() throws RemoteException;
    ArrayList<String> CallgetAllByType(String data, int type)                                    throws java.rmi.RemoteException;
    ArrayList<String> CallgetIntervalByType(String data, int type, Date start, Date end)         throws java.rmi.RemoteException;
    ArrayList<String> CallgetAllByLocation(String data, String loc)                              throws java.rmi.RemoteException;
    ArrayList<String> CallgetIntervalByLocation(String data, String loc, Date start, Date end)   throws java.rmi.RemoteException;
    ArrayList<String> CallgetAllByDate(String data, Date d)                                      throws java.rmi.RemoteException;
    ArrayList<String> CallgetIntervalByDate(String data, Date start, Date end)                   throws java.rmi.RemoteException;
    ArrayList<String> CalldirectSQL(String data, String sql)                                     throws java.rmi.RemoteException;
    ArrayList<String> CallgetData(String data, String s)                                         throws java.rmi.RemoteException;
    public void CallinsertData (String data, int SensID, String loc, String type, String unit, float value, String s, int chsm) throws java.rmi.RemoteException;

    //User functions
    public boolean CallcreateUser(String uname, String password, String email, String name)         throws java.rmi.RemoteException;
    public void CallchangePassword(String Uname, String changeParam)                                throws java.rmi.RemoteException;
    public int CallgetID(String user, String password)                                              throws java.rmi.RemoteException;
    public void CallchangeStatus(int UserId, String status)                                         throws java.rmi.RemoteException;
    
    // get list functions 
    public ArrayList<String> CallgetSensorIDlist(String data)                                       throws RemoteException;
    public ArrayList<String> CallgetLocationList(String data)                                           throws RemoteException;
}
