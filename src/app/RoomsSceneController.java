package app;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ListView;

public class RoomsSceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    String datapath = "lib/csv/mainDatabase.csv";

    @FXML
    private AnchorPane InformationSceneAnchorPane;
    
    @FXML
    private ListView<?> ListView;

    //Text Box to test Reading from CSV File:
    @FXML
    private Text TextBox1;

    @FXML
    void TestButton(ActionEvent event) {

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
