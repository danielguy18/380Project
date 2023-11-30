package app;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
//Java Imports

public class RoomsSceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane InformationSceneAnchorPane;
    
    //Text Box to test Reading from CSV File:
    @FXML
    private Text TextBox1;

    @FXML
    void btnPrintToDatabase(ActionEvent event) {
      
    }


    @FXML
    void btnBack(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
      stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }

}
