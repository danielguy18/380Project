
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainSceneController {

    @FXML
    private TextField txTitle;

    @FXML
    void btnOKClicked(ActionEvent event) {
        
        Stage mainWindow = (Stage) txTitle.getScene().getWindow(); //Obtains the Main Window
        String title = txTitle.getText();   //reads the text of the text field
        mainWindow.setTitle(title);     //Modifys the title of the window.
    }

}

