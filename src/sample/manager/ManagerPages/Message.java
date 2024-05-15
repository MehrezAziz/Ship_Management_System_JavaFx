package sample.manager.ManagerPages;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private String message;
    Date time;
    String pseudo;
    String who;
    public Message(String message, Date time, String pseudo,String who) {
        this.message = message;
        this.time = time;
        this.pseudo = pseudo;
        this.who=who;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
