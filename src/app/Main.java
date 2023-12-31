package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
            primaryStage.setTitle("ZADB Hotel");
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream( "icon.png" )));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static Scanner x;
    public static void main(String[] args) throws IOException 
    {
        //Inserting all Data from CSV to an Array--
        String datapath = "lib/csv/mainDatabase.csv";
        String editTerm = "20";
        String newID = "25";
        String newRoomType = "KING";
        String newPrice = "3000";
        
        //Format to edit mainDatabase.csv file 
        //editRecord(datapath,editTerm,newID,newRoomType,newPrice);
        //editRecord(datapath,"20", "4000", "QUEENNNN", "5$");  
        //readRecord(datapath, "KING");
        
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


    public static void editRecord(String datapath, String editTerm, String newID, String newRoomType, String newPrice) {
        String tempFile = "temp.csv";
        File oldFile = new File(datapath);
        File newFile = new File(tempFile);
    
        try {
            BufferedReader br = new BufferedReader(new FileReader(datapath));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
    
            String line = br.readLine(); // Read the header line
            bw.write(line + "\n"); // Write the header line to temp file
    
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String ID = values[0].replace("\"", "").trim();
    
                if (ID.equals(editTerm)) {
                    line = "\"" + newID + "\",\"" + newRoomType + "," + values[2] + ",\"" + newPrice + "\"," + values[4];
                }
                bw.write(line + "\n");
            }
            
            br.close();
            bw.close();
    
            if(oldFile.delete()) {
                newFile.renameTo(oldFile);
            } else {
                System.out.println("Could not delete old file");
            }
        } catch (Exception e) {
            System.out.println("Error in editRecord: " + e.getMessage());
        }
    }

    public static void readRecord(String datapath, String RoomType) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(datapath));
            String line = br.readLine(); // Read the header line
    
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String RT = values[1].replace("\"", "").trim();
    
                if (RT.equals(RoomType)) {
                   System.out.println("YESS FOUND IT AT ROOM # " + values[0]);
                }
            }
            br.close();
    }finally{ 
    }
}
    
    

    public static void console()
    {
        LocalDate date = LocalDate.now();
        System.out.println(date.toString());
        System.out.println("\n\nSelect an option\n1. New Customer\n2. New Reservation\n3. View All Rooms\n4. View All Reservations\n5. Send email\n6. Delete Reservation");
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

            case 4:
            viewAllReservations();
            break;

            case 5: 
            testEmail();
            break;

            case 6:
            deleteRSVP();

            default:
            selection = 0;
            break;
        }
        scanner.close();
        return;
    }

    private static void deleteRSVP()    
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPlease enter the confirmation code for the reservation to delete:\n");
        String s = null;
        s = scanner.nextLine();

        Reservation.loadReservationData();

        Reservation.printReservationData();
        Reservation.printListString();

        Reservation.deleteReservation(s);

        Reservation.printReservationData();
        Reservation.printListString();

        Reservation.saveReservationData();

        System.out.println("Successfully deleted.");
        scanner.close();
    }

    private static void viewAllReservations() 
    {
        
    }

    public static void testEmail()
    {
        String email = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter email:\n");
        email = scanner.nextLine();

        EmailUtility.sendEmail(email, "Subject", "Body");
        
        scanner.close();
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

        System.out.println("Enter email:\n");
        String email = scanner.nextLine();

        Reservation.loadReservationData();
        Reservation.addReservation(rsvp);
        Reservation.saveReservationData();
        Reservation.printReservationData();
        
        scanner.close();
    }
}