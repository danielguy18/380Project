package app;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import app.Room.RoomType;

public class Reservation
{
    private String confirmation_code;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Customer customer;
    private Room.RoomType type;
    private int room_number;
    private int occupants;

    public Reservation()
    {
        this.confirmation_code = generateConfirmationCode();
        this.checkInDate = null;
        this.checkOutDate = null;
        this.customer = null;
        this.room_number = -1;
        this.type = null;
        this.occupants = 0;
    }

    public Reservation(LocalDate checkInDate, LocalDate checkOutDate, Customer customer, Room.RoomType type, int room_number, int occupants)
    {
        this.confirmation_code = generateConfirmationCode();
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.customer = customer;
        this.room_number = room_number;
        this.type = type;
        this.occupants = occupants;
    }

    public int getRoomNumber()
    {
        return this.room_number;
    }

    public void setRoomNumber(int room_number)
    {
        this.room_number = room_number;
    }

    public Customer getCustomer()
    {
        return this.customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public LocalDate getCheckInDate()
    {
        return this.checkInDate;
    }

    public void setCheckInDate(LocalDate date)
    {
        this.checkInDate = date;
    }

    public LocalDate getCheckOutDate()
    {
        return this.checkOutDate;
    }

    public void setCheckOutDate(LocalDate date)
    {
        this.checkOutDate = date;
    }

    public Room.RoomType getRoomType()
    {
        return this.type;
    }

    public void setRoomType(Room.RoomType type)
    {
        this.type = type;
    }

    public String getConfirmationCode()
    {
        return this.confirmation_code;
    }

    public static boolean isComplete(Reservation rsvp)
    {
        if(rsvp.customer == null)
        {
            return false;
        }
        if(rsvp.room_number == -1)
        {
            return false;
        }
        if(rsvp.checkInDate == null)
        {
            return false;
        }
        if(rsvp.checkOutDate == null)
        {
            return false;
        }
        if(rsvp.type == null)
        {
            return false;
        }
        if(rsvp.confirmation_code != null)
        {
            return false;
        }
        return true;
    }

    public static Reservation getReservation(String code)
    {
        for(Reservation temp : reservations)
        {
            if(temp.confirmation_code == code)
            {
                return temp;
            }
        }
        return null;
    }




    private static List<String[]> reservation_data = null;

    private static List<Reservation> reservations = new ArrayList<Reservation>();

    public static void addReservation(Reservation reservation)
    {
        if(isComplete(reservation) == true)
        {
            String[] rsvp = 
            {
                reservation.confirmation_code,
                reservation.checkInDate.toString(), 
                reservation.checkOutDate.toString(), 
                reservation.type.toString(), 
                reservation.room_number + "", 
                reservation.occupants + ""
            };

            reservation_data.add(rsvp);
        }
    }

    //loads reservation data from file and stores all info into List<String[]>
    //this prevents reading/writing to file consistently.
    public static void loadReservationData()
    {
        try 
        {
            Reservation rsvp = new Reservation();
            //create instance of reader
            CSVReader reader = new CSVReaderBuilder(new FileReader("src\\reservationdata.csv")).build();

            //store all contents of file into a List<String[]>
            reservation_data = reader.readAll();

            //the following adds the contents of customer_data into a list of customers for easy manipulation later
            //loop through all String[] entries within List<String[]>
            for(String[] array : reservation_data) 
            {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                formatter = formatter.withLocale(Locale.US);
                LocalDate formatted_date = LocalDate.parse(array[1], formatter);

                rsvp.confirmation_code = array[0];
                rsvp.setCheckInDate(formatted_date);
                formatted_date = LocalDate.parse(array[2], formatter);
                rsvp.setCheckOutDate(formatted_date);
                rsvp.setCustomer(Customer.getCustomer(array[3]));
                rsvp.setRoomNumber(Integer.parseInt(array[4]));
                rsvp.setRoomType(Room.RoomType.valueOf(array[5]));
            }
        } 
        catch (Exception e) 
        {
            System.err.println(e.getMessage());
        }
    }


    //prints contents of the List<String[]> array
    //mainly for testing purposes
    public static void printReservationData() 
    {
        //loop through all String[] entries within List<String[]>
        for (String[] array : reservation_data) 
        {
            //print each element of an individual array of String[]
            for (String element : array) 
            {
                System.out.print(element + " ");
            }

            // Print a new line after each array
            System.out.println(); 
        }
    }

    //saves reservation data from List<String[]> array and writes it to file.
    public static void saveReservationData()
    {
        try
        {
            //path which should work across systems
            String path = System.getProperty("user.dir") + "\\src\\reservationdata.csv";

            //CSVWriter which overwrites file instead of appending to the end
            CSVWriter writer = new CSVWriter(new FileWriter(path, false));

            // for loop to iterate through List<String[]> and writes all contents of String[]
            for (String[] array : reservation_data) 
            {
                writer.writeNext(array);
            }

            //close writer
            writer.close();

        } 
        catch(Exception e)
        {
            System.err.println(e.toString());
        }
    }


    public static String generateConfirmationCode() 
    {
        String characters = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789"; // Excludes confusing characters
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 10; i++) 
        {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
    }
}