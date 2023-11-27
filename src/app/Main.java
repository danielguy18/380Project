package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import app.Room.RoomType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml")); //Change name to launch different fxml gui.
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("380 Project Demo Scene");
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream( "icon.png" )));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) 
    {

        //Prompt user to launch GUI application or to remain in the console.
        System.out.println("Select an option: \n1. Launch GUI\n2. Remain in console");
        int selection = 0;
        Scanner scanner = new Scanner(System.in);
        selection = scanner.nextInt();
        switch(selection)
        {
            case 1:
            launch(args);
            scanner.close();
            break;

            case 2:
            console();
            scanner.close();
            break;
        }
    }


    public static void console()
    {
        LocalDate date = LocalDate.now();
        System.out.println(date.toString());
        Scanner scanner = new Scanner(System.in);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        formatter = formatter.withLocale(Locale.US);  
        
        String fn,ln,add,em,bday;

        System.out.println("Enter customer first name\n");
        fn = scanner.nextLine();

        System.out.println("Enter customer last name\n");
        ln = scanner.nextLine();

        System.out.println("Enter customer address name\n");
        add = scanner.nextLine();

        System.out.println("Enter customer email name\n");
        em = scanner.nextLine();

        System.out.println("Enter customer bday name (MM-DD-YYYY)\n");
        bday = scanner.nextLine();
        LocalDate formatted_date = LocalDate.parse(bday, formatter);

        Customer cust = new Customer(fn, ln, em, add, formatted_date);

        System.out.println(cust.toString());
        Customer.loadCustomerData();
        Customer.printCustomerData();
        Customer.addCustomer(cust);
        Customer.saveCustomerData();
        Customer.printCustomerData();

        Reservation rsvp = new Reservation();
        rsvp.setCustomer(cust);

        System.out.println("Please choose room type:");
        System.out.println("1. Dual Queen\n2.King\n3.Suite");

        int selection = 0;
        selection = scanner.nextInt();
        switch(selection)
        {
            case 1:
            rsvp.setRoomType(RoomType.DUALQUEEN);
            break;

            case 2:
            rsvp.setRoomType(RoomType.KING);
            break;

            case 3:
            rsvp.setRoomType(RoomType.SUITE);
            break;
        }
        selection = 0;

        System.out.println("Please enter number of occupants: (1-4)");
        selection = scanner.nextInt();
        rsvp.setNumOccupants(selection);

        //assign room number
        System.out.println("Please enter desired check-in date:");

        System.out.println("Please enter desired check-out date:");

        scanner.close();


        

        return;
    }
}