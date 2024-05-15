package sample.manager.ManagerPages;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ChatRemote extends Remote {
    public String getCh() throws RemoteException;
    public void setCh(String ch) throws RemoteException;
    public ArrayList<Message> getDisc() throws RemoteException;
    public void AddMsg(Message msg) throws RemoteException;

}
