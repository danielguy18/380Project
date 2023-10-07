package app;

import java.io.File;
import java.io.FileNotFoundException;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

   Alert alert = new Alert(Alert.AlertType.NONE);
   private File file;

   // File->Open
   public void onFileOpenButtonClicked(ActionEvent event) {
      try {
         FileChooser fileChooser = new FileChooser();
         fileChooser.setTitle("Open Resource File");
         file = fileChooser.showOpenDialog(null);
         // File verification
         if (!file.exists() || !file.isFile() || !getExtension(file.getName()).equals(".csv")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid file. Please select a valid .csv file.");
            alert.show();
            file = null;
         }
      } catch (SecurityException e) {
         alert.setAlertType(Alert.AlertType.ERROR);
         alert.setContentText("Security Exception");
         alert.show();
      }


   }

   public String getExtension(String filename) {
      Pattern pattern = Pattern.compile("\\.[0-9a-z]{1,5}$", Pattern.CASE_INSENSITIVE);
      Matcher matcher = pattern.matcher(filename);
      if (matcher.find()) {
         return matcher.group();
      } else {
         return "";
      }
   }

}
