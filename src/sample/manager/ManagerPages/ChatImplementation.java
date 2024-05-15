package sample.manager.ManagerPages;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ChatImplementation  extends UnicastRemoteObject implements  ChatRemote{
    //déclarer les ressources
    String ch="";
    ArrayList <Message> disc=null;
    public ChatImplementation() throws RemoteException {
        this.disc= new ArrayList<Message>();
    }

    @Override
    public String getCh() {
        return ch;
    }

    @Override
    public void setCh(String ch) {
        this.ch = ch;
    }
    @Override

    public ArrayList<Message> getDisc() {
        return disc;
    }
    @Override

    public void AddMsg(Message msg) {
        this.disc.add(msg);
    }
}
