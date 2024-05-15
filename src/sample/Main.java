package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample._BackEnd.TableView.ResizeHelper;
import sample.manager.ManagerPages.ChatImplementation;
import sample.manager.ManagerPages.ChatRemote;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;


public class Main extends Application {

    public static Stage stage;
    public static double x, y;
    public static double xxx, yyy;
    public static int registryPort=9001;
    public static String Host="127.0.0.1";
    public static boolean instanceUsed=false;
    @Override
    public void start(Stage primaryStage) throws Exception{

        stage = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Ship Management System");
        primaryStage.setScene(new Scene(root, 600, 400));
//        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
//        primaryStage.setX((primScreenBounds.getWidth() - 600) / 2);
//        primaryStage.setY((primScreenBounds.getHeight() - 400) / 2);
        primaryStage.setX(140);
        primaryStage.setY(130);
        x = primaryStage.getX();
        y = primaryStage.getY();
        root.setOnMousePressed(event -> {
            xxx = event.getSceneX();
            yyy = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
//            if(event.getButton() == MouseButton.SECONDARY) {
                primaryStage.setX(event.getScreenX() - xxx);
                primaryStage.setY(event.getScreenY() - yyy);
                x = primaryStage.getX();
                y = primaryStage.getY();
//            }
        });
        primaryStage.show();

            if (isRMIRegistryRunning(registryPort)) {
                System.out.println("\nRMI server is already running!");
                System.out.println("RMI client will part be running only when the manager/customer logs in.\n");

            }else{
                instanceUsed=true;
                RMIserverCode();
            }

    }
    static void  RMIserverCode() throws RemoteException {
        System.out.println("Server is running.\n");
        String url="rmi://"+Host+":"+registryPort+"/chat";
        ChatImplementation chat=new ChatImplementation();
        LocateRegistry.createRegistry(9001);
        try {
            Naming.rebind(url,chat);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isRMIRegistryRunning(int registryPort) {
        try {
            // Attempt to locate the RMI registry
            Registry registry = LocateRegistry.getRegistry(Host, registryPort);
            registry.list(); // This will throw an exception if the registry is not already running
            return true;
        } catch (Exception e) {
            // RMI registry is not running
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
