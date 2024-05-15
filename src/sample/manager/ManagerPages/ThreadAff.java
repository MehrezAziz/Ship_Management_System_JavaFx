package sample.manager.ManagerPages;

import com.jfoenix.controls.JFXTextArea;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ThreadAff extends Thread {
    JFXTextArea screen;
    ChatRemote r;
    String pseudo;
    public ThreadAff(ChatRemote r, JFXTextArea screen, String pseudo){
        this.screen = screen;
        this.r = r;
        this.pseudo=pseudo;
    }

    public void run(){
        while(true){
            try {
                String aff="";
                ArrayList<Message> recu=r.getDisc();
                for(Message m:recu){   //"all"
                    if(m.getWho().equals("")|m.getWho().equals(pseudo)|m.getWho().equals(m.getPseudo()))
                        aff+=m.getPseudo()+":  "+m.getMessage()+"\n-----\n";
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                screen.setText(aff);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
