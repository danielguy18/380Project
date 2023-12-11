package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class Controller {
   private Stage stage;
   private Scene scene;
   private Parent root;
   
   Alert alert = new Alert(Alert.AlertType.NONE);
   private File csvFile;

   
   /** 
    * This function corresponds with the File->Open MenuItem. When clicked, it will open a window prompting the user to choose a file.
      If the file extension is not .csv, it will output an error message. 
      If the file extension is .csv, it will output a success message and assign the file to the {@link #csvFile} variable.
      Uses {@link #getExtension(String)} to check the file extension.
    * @author Bao Ngo
    * @param event the event that triggers the button click
    */
   public void onFileOpenButtonClicked(ActionEvent event) {
      try {
         FileChooser fileChooser = new FileChooser();
         fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
         fileChooser.setTitle("Open Resource File");
         csvFile = fileChooser.showOpenDialog(null);
         // File verification
         if (csvFile == null || !csvFile.exists() || !csvFile.isFile() || !getExtension(csvFile.getName()).equals(".csv")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid file. Please select a valid .csv file.");
            alert.show();
            csvFile = null;
         } else {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setHeaderText("File Opened");
            alert.setContentText("File opened successfully at path: " + csvFile.getAbsolutePath());
            alert.show();
         }
         // handle csv file
         printConsoleCSV(); //debug function
      } catch (SecurityException e) {
         alert.setAlertType(Alert.AlertType.ERROR);
         alert.setContentText("Security Exception");
         alert.show();
      }

   }

   
   /** 
    * Checks the inputted fileName and output the extension of the file. 
    * The extension must be at the end of the string with no trailing whitespace. The extension character limit is maximum of 5.
    * @author Bao Ngo
    * @param fileName the file name to be examined in string
    * @return the extension of the file including the dot (e.g. ".csv") or empty if no extension was found
    */
   public String getExtension(String fileName) {
      Pattern pattern = Pattern.compile("\\.[0-9a-z]{1,6}$", Pattern.CASE_INSENSITIVE);
      Matcher matcher = pattern.matcher(fileName);
      if (matcher.find()) {
         return matcher.group();
      } else {
         return "";
      }
   }

   // Debug function to print out the csv file to console
   //first 3 columns only
   public void printConsoleCSV() {
      try {
         Reader in = new FileReader(csvFile.toString());
         Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
         for (CSVRecord record : records) {
            System.out.println(record.get(0));
            System.out.println(record.get(1));
            System.out.println(record.get(2));
         }
      } catch (Exception e) {
         System.err.println(e.getMessage());
      }

   }

   /** 
    * This Function moves the Screen to "Information's" Page.
    * @author Zelgehai Zahid
    * @param event the event that triggers the button click
    */
    @FXML
    private Label mainSceneTitle;
    @FXML
    void btnInformationClicked(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(getClass().getResource("InformationPageDraft.fxml"));
      stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
      //Stage mainWindow = (Stage) mainSceneTitle.getScene().getWindow();
      //mainSceneTitle.setText("testing Button");
    }
    @FXML
    void btnRoomsClicked(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(getClass().getResource("RoomsScene.fxml"));
      stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
   
    
}
