package sample.manager.ManagerPages;

import com.sun.net.httpserver.Authenticator;
import javafx.application.Application;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Main;
import sample._BackEnd.CommonTask;
import sample._BackEnd.DBConnection;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import static sample.manager.Login.ManagerLogin.currentEmployeeNID;


public class ManagerMain  implements Initializable{
    public BorderPane borderpane;
    public FontAwesomeIconView closeWindow;
    public FontAwesomeIconView minimizeWindow;
    public FontAwesomeIconView maximizeWindow;
    public AnchorPane userMainPane;
    public static Dialog aff;
    public static String NameOfManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        windowLoad("ManagerHome.fxml");

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
                System.out.println("(manager) RMI client part is running now..\n");
                RMIclientCode();
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        Connection connection = DBConnection.getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM EMPLOYEEINFO WHERE NID = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, currentEmployeeNID);
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    String customerName = resultSet.getString("NAME");
                    ManagerMain.NameOfManager=customerName;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnection.closeConnections();
        }


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
        aff=new Dialog(r);
    }

    private void minimizeStageOfNode(Node node) {
        ((Stage) (node).getScene().getWindow()).setIconified(true);
    }

    public void windowLoad(String URL)
    {
        try
        {
            Pane window = FXMLLoader.load(getClass().getResource(URL));
            borderpane.setCenter(window);
        }
        catch(Exception err)
        {
            System.out.println("Problem : " + err);
            Alert alert =new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setContentText("You are the server!\nyou can\'t open Messages");
            alert.showAndWait();
        }

    }

    public void ManageRooms(ActionEvent actionEvent) {
        windowLoad("ManagerManageRooms.fxml");
    }

    public void ManagerHome(ActionEvent actionEvent) {
        windowLoad("ManagerHome.fxml");
    }

    public void ManagerCheckIn(ActionEvent actionEvent) {
        windowLoad("ManagerCheckIn.fxml");
    }

    public void ManagerCheckOut(ActionEvent actionEvent) {
        windowLoad("ManagerCheckOut.fxml");
    }

    public void CheckOutDetails(ActionEvent actionEvent) {
        windowLoad("ManagerCheckOutDetails.fxml");
    }

    public void MangerChat(ActionEvent actionEvent) {
        windowLoad("ManagerChat.fxml");
    }

    public void closeApplication(MouseEvent mouseEvent) {
        System.exit(0);
    }


}
