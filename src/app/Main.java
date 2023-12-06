package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import org.junit.Test;

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
            selection = -1;
            break;

            case 2:
            console();
            selection = -1;
            break;

            default:
            selection = -1;
            break;
        }
        scanner.close();
        return;
    }


    public static void console()
    {
        LocalDate date = LocalDate.now();
        System.out.println(date.toString());
        System.out.println("\n\nSelect an option\n1. New Customer\n2. New Reservation\n3. View All Rooms");
        Scanner scanner = new Scanner(System.in);
        int selection = 0;
        selection = scanner.nextInt();

        switch(selection)
        {

            case 1:
            newCustomer();
            selection = 0;
            break;

            case 2:
            newReservation();
            selection = 0;
            break;

            case 3:
            viewAllRooms();
            break;
            default:
            selection = 0;
            break;
        }
        scanner.close();
        return;
    }

    private static void viewAllRooms() 
    {
        Room.loadRoomData();
        Room.printRoomData();
    }

    public static void newCustomer()
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
        Customer.addCustomer(cust);
        Customer.saveCustomerData();
        Customer.printCustomerData();
        
        scanner.close();
        return;
    }

    public static void newReservation()
    {
        Scanner scanner = new Scanner(System.in);
        int selection = 0;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        formatter = formatter.withLocale(Locale.US);  

        Reservation rsvp = new Reservation();
        Customer cust = new Customer();
        rsvp.setCustomer(cust);

        System.out.println("Please choose room type:");
        System.out.println("1.Queen\n2.King");

        selection = scanner.nextInt();
        switch(selection)
        {
            case 1:
            rsvp.setRoomType(RoomType.QUEEN);
            break;

            case 2:
            rsvp.setRoomType(RoomType.KING);
            break;
        }
        selection = 0;

        String checkIn, checkOut;
        LocalDate formatted_date;

        System.out.println("Please enter number of occupants: (1-4)");
        selection = scanner.nextInt();
        rsvp.setNumOccupants(selection);
        scanner.nextLine();

        //assign room number

        System.out.println("Please enter desired check-in date: (MM-DD-YYYY)");
        checkIn = scanner.nextLine();
        formatted_date = LocalDate.parse(checkIn, formatter);
        rsvp.setCheckInDate(formatted_date);

        System.out.println("Please enter desired check-out date: (MM-DD-YYYY)");
        checkOut = scanner.nextLine();
        formatted_date = LocalDate.parse(checkOut, formatter);
        rsvp.setCheckOutDate(formatted_date);

        rsvp.setRoomNumber(5);

        Reservation.loadReservationData();
        Reservation.addReservation(rsvp);
        Reservation.saveReservationData();
        Reservation.printReservationData();
        scanner.close();
    }
}