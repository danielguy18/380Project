package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;
import javax.swing.event.ChangeListener;

import javafx.beans.value.ObservableValue;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class RoomsSceneController implements Initializable{
    private Stage stage;
    private Scene scene;
    private Parent root;
    String datapath = "lib/csv/mainDatabase.csv";
    String[] smokedRooms = {"Room:\"2\"\"KING\" - SMOKING"+"\nRoom:\"4\"\"KING\" - SMOKING"+"\nRoom:\"6\"\"KING\" - SMOKING" +
    "\nRoom:\"13\"\"QUEEN\" - SMOKING" + "\nRoom:\"16\"\"QUEEN\" - SMOKING" + "\nRoom:\"17\"\"QUEEN\" - SMOKING"
    };
    String[] food = {"Room:\"1\"\"KING\""};
    String currentitem;

    @FXML
    private AnchorPane InformationSceneAnchorPane;
    
    @FXML
    private ListView<String> ListView;

    @FXML
    private Text TextBox1;

    @Override
    public void initialize(java.net.URL location, ResourceBundle resources) {
      // TODO Auto-generated method stub
      //ListView.getItems().addAll(food);
      ListView.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener<String>() {

        @Override
        public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
          currentitem = ListView.getSelectionModel().getSelectedItem();
          TextBox1.setText(currentitem);
      }
    });
    }
    //Text Box to test Reading from CSV File:
    @FXML
    void TestButton(ActionEvent event) throws IOException {
      currentitem = ListView.getSelectionModel().getSelectedItem();
      if (currentitem == "Room:\"1\"\"KING\""){
      Parent root = FXMLLoader.load(getClass().getResource("CheckoutKingPage.fxml"));
      stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
      }
    }
    
    /*@FXML
    void btnPrintToDatabase(ActionEvent event) {
      Main.editRecord(datapath, "3", "42", "QUEEN", "9000");
      TextBox1.setText("Changed Database successful!");
    }
     * 
     */


    @FXML
    void btnBack(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
      stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }

    @FXML
    void checkBox1Clicked(ActionEvent event) throws IOException {
      CheckBox checkBox = (CheckBox) event.getSource();
      if(checkBox.isSelected()){
        ListView.getItems().addAll(food);
        addKingRooms();
      } else{
        removeKingRooms();
      }
    }

    public void addKingRooms() throws IOException{
      String RoomType = "KING";
      try {
            BufferedReader br = new BufferedReader(new FileReader(datapath));
            String line = br.readLine(); // Read the header line
    
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String RT = values[1].replace("\"", "").trim();
    
                if (RT.equals(RoomType)) {
                   System.out.println("YES FOUND IT AT ROOM # " + values[0]);
                   ListView.getItems().add("Room:" + values[0] + "" + values[1]);
                }
            }
            br.close();
    }finally{ 
    }
    }

    public void removeKingRooms() throws IOException{
      ListView.getItems().clear();
      System.out.println("SUCCESSFULLY REMOVED King Objects");
    }

    @FXML
    void checkBox2Clicked(ActionEvent event) throws IOException {
    CheckBox checkBox = (CheckBox) event.getSource();
      if(checkBox.isSelected()){
        addQueenRooms();
      } else{
        removeQueenRooms();
      }
    }

    public void addQueenRooms() throws IOException{
      String RoomType = "QUEEN";
      try {
            BufferedReader br = new BufferedReader(new FileReader(datapath));
            String line = br.readLine(); // Read the header line
    
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String RT = values[1].replace("\"", "").trim();
    
                if (RT.equals(RoomType)) {
                   System.out.println("YES FOUND IT AT ROOM # " + values[0]);
                   ListView.getItems().add("Room:" + values[0] + " " + values[1]);
                }
            }
            br.close();
    }finally{ 
    }
    }

    public void removeQueenRooms() throws IOException{
      ListView.getItems().clear();
      System.out.println("SUCCESSFULLY REMOVED Queen Objects");
    }

    @FXML
    void checkBox3Clicked(ActionEvent event) throws IOException {
      CheckBox checkBox = (CheckBox) event.getSource();
      if(checkBox.isSelected()){
        addSmokingRooms();
      } else{
        removeSmokingRooms();
      }
    }

    public void addSmokingRooms() throws IOException{
      ListView.getItems().addAll(smokedRooms);
    }

    public void removeSmokingRooms() throws IOException{
      ListView.getItems().clear();
      System.out.println("SUCCESSFULLY REMOVED Smoking Rooms Objects");
    }
}
