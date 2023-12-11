package app;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;

public class RoomsSceneController{
    private Stage stage;
    private Scene scene;
    private Parent root;
    String datapath = "lib/csv/mainDatabase.csv";



    @FXML
    private AnchorPane InformationSceneAnchorPane;
    
    @FXML
    private ListView<String> ListView;
    

    //Text Box to test Reading from CSV File:
    @FXML
    private Text TextBox1;
    @FXML
    public void initialize(URL url, ResourceBundle resources) {
      filldata("testing333");
      System.out.println("Initializing...");
    }
    
    @FXML
    public void filldata(String item){
      ListView.getItems().add(item);
    }

    @FXML
    void TestButton(ActionEvent event) {
      ListView.getItems().add("testing");
    }

    @FXML
    void btnPrintToDatabase(ActionEvent event) {
      Main.editRecord(datapath, "3", "42", "QUEEN", "9000");
      TextBox1.setText("Changed Database successful!");
    }


    @FXML
    void btnBack(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
      stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }

    @FXML
    void checkBox1Clicked(ActionEvent event) {
      
    }

    @FXML
    void checkBox2Clicked(ActionEvent event) {

    }

    @FXML
    void checkBox3Clicked(ActionEvent event) {

    }


}
