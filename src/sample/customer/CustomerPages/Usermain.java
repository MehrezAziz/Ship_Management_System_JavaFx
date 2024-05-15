package sample.customer.CustomerPages;

import com.jfoenix.controls.JFXDialog;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.Main;
import sample._BackEnd.CommonTask;
import sample._BackEnd.DBConnection;
import sample.manager.ManagerPages.ChatRemote;
import sample.manager.ManagerPages.Dialog;
import sample.manager.ManagerPages.ManagerMain;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import static sample._BackEnd.CommonTask.newStage;
import static sample.customer.Login.UserLogin.currentCustomerNID;

public class Usermain implements Initializable {

    public BorderPane borderpane;
    public Button goHomeID;
    public Button roomDetailsID;
    public Button checkInID;
    public Button checkInOutID;
    public FontAwesomeIconView closeWindow;
    public FontAwesomeIconView minimizeWindow;
    public FontAwesomeIconView maximizeWindow;
    public AnchorPane userMainPane;
    public StackPane rootPane;
    public static String UserName;
    public static String UserNID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        windowLoadStackPane("UserHome.fxml");
        closeWindow.setOnMouseClicked(event -> {
            System.exit(0);
        });

        minimizeWindow.setOnMouseClicked(event -> {
            minimizeStageOfNode((Node) event.getSource());
        });

        AtomicInteger maxWindow = new AtomicInteger();
        maximizeWindow.setOnMouseClicked(event -> {
            Stage stage1 = (Stage) userMainPane.getScene().getWindow();
            stage1.setMaximized(!stage1.isMaximized());
        });
        try {
            if (Main.instanceUsed){
                System.out.println("server is already running, you can't open RMI client..");
            }else{
                System.out.println("(Customer) RMI client part is running now..\n");
                RMIclientCode();
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        Connection connection = DBConnection.getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM CUSTOMERINFO WHERE NID = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, currentCustomerNID);
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    String customerName = resultSet.getString("NAME");
                    String customerNID = resultSet.getString("NID");
                    UserName=customerName;
                    UserNID=customerNID;

                } else {
                    CommonTask.showAlert(Alert.AlertType.ERROR, "ERROR", "Can't get/set Info!");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnection.closeConnections();
        }
    }

    private void minimizeStageOfNode(Node node) {
        ((Stage) (node).getScene().getWindow()).setIconified(true);
    }

    static void  RMIclientCode() throws RemoteException {
        System.out.println("Discussion starting");
        String url="rmi://"+Main.Host+":"+Main.registryPort+"/chat";
        ChatRemote r=null;
        try {
            r=(ChatRemote) Naming.lookup(url);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            throw new RuntimeException(e);
        }
        String pseudo="Manager";
        ManagerMain.aff =new Dialog(r);
    }

    public void windowLoad(String URL)
    {
        try
        {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(URL));
            borderpane.setCenter(pane);
        }
        catch(Exception err)
        {
            System.out.println("Problem :: " + err);
        }

    }


    public void windowLoadStackPane(String URL)
    {
        try
        {
            StackPane pane = FXMLLoader.load(getClass().getResource(URL));
            borderpane.setCenter(pane);
        }
        catch(Exception err)
        {
            System.out.println("Problem : " + err);
        }
    }

    public void GoHome(ActionEvent actionEvent) {
        windowLoadStackPane("UserHome.fxml");
    }

    public void GoRoomDetails(ActionEvent actionEvent) {
        windowLoad("UserRoomDetails.fxml");
    }

    public void GoCheckIn(ActionEvent actionEvent) {
        windowLoadStackPane("UserCheckIn.fxml");
    }

    public void UserChat(ActionEvent actionEvent) {
        windowLoad("CustomerInfo/UserChat.fxml");
    }

    public void GoCheckDetails(ActionEvent actionEvent) {
        windowLoad("UserCheckOutDetails.fxml");
    }
}
