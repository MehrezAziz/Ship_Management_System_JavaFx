package sample.manager.ManagerPages;

import com.jfoenix.controls.JFXDialog;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import sample.Main;
import sample._BackEnd.CommonTask;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerHome{
    public StackPane rootPane;
    public AnchorPane rootAnchorPane;
    public static int welcomed = 0;
    public void ManagerInfo(ActionEvent actionEvent) throws IOException {
        CommonTask.pageNavigation("ManagerInfo/ManagerInfo.fxml", null, this.getClass(),"User Home", 550, 400);
//        CommonTask.pageNavigation("/sample/manager/ManagerPages/RoomInfoEdit/roomInfoEdit.fxml", null, this.getClass(),"Edit Info", 550, 400);

    }

    public void LogOut(ActionEvent actionEvent) throws IOException {

        CommonTask.pageNavigation("/sample/sample.fxml", Main.stage,this.getClass(),"User Home", 600, 400);

    }

}
