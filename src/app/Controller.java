package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class Controller {

   Alert alert = new Alert(Alert.AlertType.NONE);
   private File file;

   // File->Open
   public void onFileOpenButtonClicked(ActionEvent event) {
      try {
         FileChooser fileChooser = new FileChooser();
         fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
         fileChooser.setTitle("Open Resource File");
         file = fileChooser.showOpenDialog(null);
         // File verification
         if (file == null || !file.exists() || !file.isFile() || !getExtension(file.getName()).equals(".csv")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid file. Please select a valid .csv file.");
            alert.show();
            file = null;
         }
         // handle csv file
         printConsoleCSV(); //debug function
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

   public void printConsoleCSV() {
      try {
         Reader in = new FileReader(file.toString());
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

}
