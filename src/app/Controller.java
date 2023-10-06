package app;

import java.io.File;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Parent;

public class Controller {
   @FXML
   private List<Integer> data;

   public void onFileOpenButtonClicked(ActionEvent event) {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Open Resource File");
      File file = fileChooser.showOpenDialog(null);
   }
}
