/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcommunication;

import datasystem.DataControl;
import datasystem.UserControl;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import securitysystem.UserAuthentication;
import sensorinfrastructure.Main;

/**
 *
 * @author nb
 */
public class WebCommunication extends UnicastRemoteObject implements WebInterface {

    DataControl offdata;
    DataControl expdata;

    UserControl users = Main.users;

    static String localaddress = "rmi://localhost:53168/data";

    public WebCommunication(DataControl _offdata, DataControl _expdata) throws RemoteException {

        System.out.print("Setting up webserver RMI interface... ");
        offdata = _offdata;
        expdata = _expdata;

        try {
            this.expdata = new DataControl("DataBase");
        } catch (SQLException ex) {
            Logger.getLogger(WebCommunication.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Done!");
    }

    public void publish() {

        try {

            System.out.print("Publishing RMI Data interface... ");

            java.rmi.registry.LocateRegistry.createRegistry(53168);

            Naming.rebind(localaddress, (Remote) this);

            System.out.println("Web RMI: " + localaddress);
            System.out.println("Succes!");

        } catch (Exception e) {

            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();

        }

    }

    public String getMessage() {
        return "Hello World";
    }

    @Override
    public ArrayList<String> CallgetAllBySensorID(String data, int ID) throws RemoteException {

        System.out.println("CallgetAllBySensorID " + data + " " + ID);

        if (data.equals("offdata")) {
            return offdata.getAllBySensorID(ID);
        } else {
            return expdata.getAllBySensorID(ID);
        }

    }

    @Override
    public ArrayList<String> CallgetIntervalBySensorID(String data, int ID, Date start, Date end) throws RemoteException {

        System.out.println("CallgetIntervalBySensorID " + data + " " + ID + " " + start + " " + end);

        if (data.equals("offdata")) {
            return offdata.getIntervalBySensorID(ID, start, end);
        } else {
            return expdata.getIntervalBySensorID(ID, start, end);
        }

    }

    public boolean CallLogin(String username, String password) throws RemoteException {
        UserAuthentication ua = new UserAuthentication();

        return ua.login(username, password);
    }

    @Override
    public ArrayList<String> CallgetAllByType(String data, int type) throws RemoteException {
        System.out.println("CallgetAllByType " + data + " " + type);

        if (data.equals("offdata")) {
            return offdata.getAllByType(type);
        } else {
            return expdata.getAllByType(type);
        }
    }

    @Override
    public ArrayList<String> CallgetIntervalByType(String data, int type, Date start, Date end) throws RemoteException {
        System.out.println("CallgetIntervalByType " + data + " " + type + " " + start + " " + end);

        if (data.equals("offdata")) {
            return offdata.getIntervalByType(type, start, end);
        } else {
            return expdata.getIntervalByType(type, start, end);
        }
    }

    @Override
    public ArrayList<String> CallgetAllByLocation(String data, String loc) throws RemoteException {
        System.out.println("CallgetAllByLocation " + data + " " + loc);

        if (data.equals("offdata")) {
            return offdata.getAllByLocation(loc);
        } else {
            return expdata.getAllByLocation(loc);
        }
    }

    @Override
    public ArrayList<String> CallgetIntervalByLocation(String data, String loc, Date start, Date end) throws RemoteException {
        System.out.println("CallgetIntervalByLocation " + data + " " + loc + " " + start + " " + end);

        if (data.equals("offdata")) {
            return offdata.getIntervalByLocation(loc, start, end);
        } else {
            return expdata.getIntervalByLocation(loc, start, end);
        }
    }

    @Override
    public ArrayList<String> CallgetAllByDate(String data, Date d) throws RemoteException {
        System.out.println("CallgetAllByDate " + data + " " + d);

        if (data.equals("offdata")) {
            return offdata.getAllByDate(d);
        } else {
            return expdata.getAllByDate(d);
        }
    }

    @Override
    public ArrayList<String> CallgetIntervalByDate(String data, Date start, Date end) throws RemoteException {
        System.out.println("CallgetIntervalByDate " + data + " " + start + " " + end);

        if (data.equals("offdata")) {
            return offdata.getIntervalByDate(start, end);
        } else {
            return expdata.getIntervalByDate(start, end);
        }
    }

    @Override
    public ArrayList<String> CalldirectSQL(String data, String sql) throws RemoteException {
        System.out.println("CalldirectSQL " + data + " " + sql);

        if (data.equals("offdata")) {
            return offdata.directSQL(sql);
        } else {
            return expdata.directSQL(sql);
        }
    }

    @Override
    public ArrayList<String> CallgetData(String data, String s) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean CallcreateUser(String uname, String password, String email, String name) throws RemoteException {
        System.out.println("CallcreateUser " + uname + " " + password + " " + email + " " + name);

        return users.createUser(uname, password, email, name);
    }

    @Override
    public void CallchangePassword(String Uname, String changeParam) throws RemoteException {
        System.out.println("CallchangePassword " + Uname + " " + changeParam);

        users.changePassword(Uname, changeParam);
    }

    @Override
    public int CallgetID(String user, String password) throws RemoteException {
        System.out.println("CallgetID " + user + " " + password);

        return users.getID(user, password);
    }

    @Override
    public void CallchangeStatus(int UserId, String status) throws RemoteException {
        System.out.println("CallchangeStatus " + UserId + " " + status);

        users.changeStatus(UserId, status);
    }

    @Override
    public void CallinsertData(String data, int SensID, String loc, String type, String unit, float value, String s, int chsm) throws RemoteException {
        System.out.println("CallinsertData " + SensID + " " + loc + " " + type + " " + unit + " " + value + " " + s + " " + chsm);

        if (data == "offdata") {
            offdata.insertData(SensID, loc, type, unit, value, s, chsm);
        } else {
            expdata.insertData(SensID, loc, type, unit, value, s, chsm);
        }

    }

    @Override
    public ArrayList<String> CallgetSensorIDlist(String data) throws RemoteException {
        if (data.equals("offdata")) {
            return offdata.GetSensorIDList();
        } else {
            return expdata.GetSensorIDList();
        }
    }

    @Override
    public ArrayList<String> CallgetLocationList(String data) throws RemoteException {
        if (data.equals("offdata")) {
            return offdata.GetLocationList();
        } else {
            return expdata.GetLocationList();
        }
    }
}
