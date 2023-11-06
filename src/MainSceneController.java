
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainSceneController {

    @FXML
    private TextField txTitle;
    
    @FXML
    private Label headerid;
    
    @FXML
    private ImageView img_id_1;

    @FXML
    void btnOKClicked(ActionEvent event) {
        
        Stage mainWindow = (Stage) txTitle.getScene().getWindow(); //Obtains the Main Window
        String title = txTitle.getText();   //reads the text of the text field
        mainWindow.setTitle(title);     //Modifys the title of the window.
    }

    @FXML
    void img_id_1_clicked(MouseEvent event) {
        //Stage mainWindow = (Stage) txTitle.getScene().getWindow(); //Obtains the Main Window
        //String title = txTitle.getText();   //reads the text of the text field
        //mainWindow.setTitle(title);     //Modifys the title of the window.
    }

}

