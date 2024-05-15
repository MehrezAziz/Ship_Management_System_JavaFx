package sample.customer.CustomerPages.CustomerInfo;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.customer.CustomerPages.Usermain;
import sample.manager.ManagerPages.ManagerMain;
import sample.manager.ManagerPages.Message;
import sample.manager.ManagerPages.ThreadAff;

public class UserChat implements Initializable {

    public JFXTextArea  AllChat;
    public JFXTextArea MyMessage;
    private static String pseudo ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pseudo= "(Customer "+Usermain.UserNID+") "+Usermain.UserName;
        ThreadAff t=new ThreadAff(ManagerMain.aff.r,AllChat,Usermain.UserNID);
        t.start();
    }

    public void SendMessage(ActionEvent actionEvent){
        String msg=MyMessage.getText();
        MyMessage.setText("");
        if (msg!=""){
            try {
                ManagerMain.aff.r.AddMsg(new Message(msg,new Date(),pseudo,""));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void handleEnterKeyPressed(KeyEvent event){
        if (event.getCode() == KeyCode.ENTER) {
            String msg=MyMessage.getText();
            MyMessage.setText("");

            if (msg!=""){
                try {
                    ManagerMain.aff.r.AddMsg(new Message(msg,new Date(),pseudo,""));
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
