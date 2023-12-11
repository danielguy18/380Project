package app;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CheckoutKingPageController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private AnchorPane KingCheckoutAnchorPane;


    @FXML
    void btnConfirmClicked(ActionEvent event) throws IOException {
      EmailUtility.sendEmail("Zelgehai.zahid.567@my.csun.edu", "Email Confirmation for Z.A.D.B KING HOTEL ROOM", "Thank you for choosing Z.A.D.B for your upcoming stay! \n\nWe are delighted to confirm your reservation and are eagerly awaiting your arrival. Please find your reservation details below:\n\nReservation Confirmation Number: 10001\n\nArrival and Check-in:\r\n" +
          "Check-in time is from 3:00 PM to 11:00PM PST. If you expect to arrive earlier and wish for an early check-in, please let us know in advance and we will do our best to accommodate your request based on availability.\n\n Sincerely, \nZ.A.D.B Managment");
      //EmailUtility.sendEmail("Zelgehai.zahid.567@my.csun.edu", "Email Confirmation for Z.A.D.B KING HOTEL ROOM", "Thank you for choosing Z.A.D.B for your upcoming stay! \n\nWe are delighted to confirm your reservation and are eagerly awaiting your arrival. Please find your reservation details below:\n\nReservation Confirmation Number: 10001\n\nArrival and Check-in:\r\n" + //
        //  "Check-in time is from 3:00 PM to 11:00PM PST. If you expect to arrive earlier and wish for an early check-in, please let us know in advance and we will do our best to accommodate your request based on availability.\n\n Sincerely, \nZ.A.D.B Managment");
      //EmailUtility.sendEmail("Daniel.gilberto.252@my.csun.edu", "Email Confirmation for Z.A.D.B KING HOTEL ROOM", "Thank you for choosing Z.A.D.B for your upcoming stay! \n\nWe are delighted to confirm your reservation and are eagerly awaiting your arrival. Please find your reservation details below:\n\nReservation Confirmation Number: 10001\n\nArrival and Check-in:\r\n" + //
        //  "Check-in time is from 3:00 PM to 11:00PM PST. If you expect to arrive earlier and wish for an early check-in, please let us know in advance and we will do our best to accommodate your request based on availability.\n\n Sincerely, \nZ.A.D.B Managment");
      Parent root = FXMLLoader.load(getClass().getResource("ThankYouPage.fxml"));
      stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show(); 
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
