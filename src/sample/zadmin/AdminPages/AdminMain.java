package sample.zadmin.AdminPages;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class AdminMain implements Initializable {


    public BorderPane borderpane;
    public FontAwesomeIconView closeWindow;
    public FontAwesomeIconView minimizeWindow;
    public FontAwesomeIconView maximizeWindow;
    public AnchorPane userMainPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        windowLoad("AdminHome.fxml");


        minimizeWindow.setOnMouseClicked(event -> {
            minimizeStageOfNode((Node) event.getSource());
        });

        AtomicInteger maxWindow = new AtomicInteger();

        maximizeWindow.setOnMouseClicked(event -> {
            Stage stage1 = (Stage) userMainPane.getScene().getWindow();
            stage1.setMaximized(!stage1.isMaximized());
        });

    }
    private void minimizeStageOfNode(Node node) {
        ((Stage) (node).getScene().getWindow()).setIconified(true);
    }

    public void windowLoad(String URL) {
        try {
            Pane window = FXMLLoader.load(getClass().getResource(URL));
            borderpane.setCenter(window);
        } catch (Exception err) {
            System.out.println("Problem : " + err);
        }
    }

    public void AdminHome(ActionEvent actionEvent) {
        windowLoad("AdminHome.fxml");
    }

    public void AddEmployee(ActionEvent actionEvent) {
        windowLoad("AdminAddEmployee.fxml");
    }

    public void AdminEmployeeInfo(ActionEvent actionEvent) {
        windowLoad("AdminEmployeeInfo.fxml");
    }

    public void AdminCustomerInfo(ActionEvent actionEvent) {
        windowLoad("AdminCustomerInfo.fxml");
    }

    public void AdminEarnLog(ActionEvent actionEvent) {
        windowLoad("AdminEarningLog.fxml");
    }

    public void AdminTotalEarnings(ActionEvent actionEvent) {
        windowLoad("AdminTotalEarnings.fxml");
    }


    public void closeApplication(MouseEvent mouseEvent) {
        System.exit(0);
    }
}
