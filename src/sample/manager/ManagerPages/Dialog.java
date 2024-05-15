package sample.manager.ManagerPages;

import com.jfoenix.controls.JFXTextArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Date;

public class Dialog{
    JFXTextArea screen;
    JTextField input;
    JTextField who;
    JButton submit;
    public ChatRemote r;
    public Dialog(ChatRemote r) {
        this.r = r;

       /* this.setTitle("Chat");
        this.setLayout(new FlowLayout());

        screen=new JTextArea();
        who=new JTextField();
        input=new JTextField();
        submit=new JButton("Submit");

        screen.setPreferredSize(new Dimension(400,200));
        input.setPreferredSize(new Dimension(400,50));
        who.setPreferredSize(new Dimension(400,50));
        submit.setPreferredSize(new Dimension(400,50));

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message=input.getText();
                try {
                    r.AddMsg(new Message(message,d,pseudo,who.getText()));
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        JScrollPane scroll=new JScrollPane(screen);
        scroll.setPreferredSize(new Dimension(400,400));
        this.add(scroll);
        this.add(who);
        this.add(input);
        this.add(submit);
        this.setSize(new Dimension(500,600));
        ThreadAff t=new ThreadAff(r,screen,pseudo);
        t.start();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);*/
    }


}