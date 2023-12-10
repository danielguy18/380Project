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
    private String customer_uid;
    private Room.RoomType type;
    private int room_number;
    private int occupants;

    public Reservation()
    {
        this.confirmation_code = generateConfirmationCode();
        this.checkInDate = null;
        this.checkOutDate = null;
        this.customer_uid = null;
        this.room_number = -1;
        this.type = null;
        this.occupants = 0;
    }

    public Reservation(LocalDate checkInDate, LocalDate checkOutDate, Customer customer, Room.RoomType type, int room_number, int occupants)
    {
        this.confirmation_code = generateConfirmationCode();
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.customer_uid = customer.getCustomerID();
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

    public String getCustomerID()
    {
        return this.customer_uid;
    }

    public void setCustomerID(String id)
    {
        this.customer_uid = id;
    }

    public void setCustomer(Customer customer)
    {
        this.customer_uid = customer.getCustomerID();
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

    public void setRoomType(String s)
    {
        if(s.equalsIgnoreCase("queen"))
        this.type = RoomType.QUEEN;
        if(s.equalsIgnoreCase("king"))
        this.type = RoomType.KING;
    }

    public int getNumOccupants()
    {
        return this.occupants;
    }

    public void setNumOccupants(int num)
    {
        this.occupants = num;
    }

    public String getConfirmationCode()
    {
        return this.confirmation_code;
    }

    public static boolean isComplete(Reservation rsvp)
    {
        if(rsvp.customer_uid == null)
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
        if(rsvp.confirmation_code == null)
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
        reservations.add(reservation);
    }

    public static void deleteReservation(String code)
    {
        for(Reservation temp : reservations)
        {
            if(temp.getConfirmationCode() == code)
            {
                reservations.remove(temp);
            }
        }
    }

    //loads reservation data from file and stores all info into List<String[]>
    //this prevents reading/writing to file consistently.
    public static void loadReservationData()
    {
        try 
        {
            //create instance of reader
            CSVReader reader = new CSVReaderBuilder(new FileReader("lib\\csv\\reservationdata.csv")).withSkipLines(1).build();

            //store all contents of file into a List<String[]>
            reservation_data = reader.readAll();

            //the following adds the contents of customer_data into a list of customers for easy manipulation later
            //loop through all String[] entries within List<String[]>
            for(String[] array : reservation_data) 
            {
                Reservation rsvp = new Reservation();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                formatter = formatter.withLocale(Locale.US);
                LocalDate formatted_date = LocalDate.parse(array[1], formatter);

                rsvp.confirmation_code = array[0];
                rsvp.setCheckInDate(formatted_date);
                formatted_date = LocalDate.parse(array[2], formatter);
                rsvp.setCheckOutDate(formatted_date);
                rsvp.setCustomerID(array[3]);
                rsvp.setRoomNumber(Integer.parseInt(array[4]));
                rsvp.setNumOccupants(Integer.parseInt(array[5]));
                rsvp.setRoomType(array[6]);

                reservations.add(rsvp);
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
        for (Reservation rsvp : reservations) 
        {
            System.out.println(rsvp.toString());
        }
    }

    public static void printListString()
   {
        for(String[] s : reservation_data)
        {
            
            for(String c : s)
            {
                System.out.println(c);
            }
        }
   }

    //saves reservation data from List<String[]> array and writes it to file.
    public static void saveReservationData()
    {
        String[] header = {"Conf. Code", "Check-In", "Check-Out", "Customer ID", "Room Number", "Occupants", "Room Type"};
        String[] temp = {"", "", "", "", "", "", ""};
        try
        {
            //path which should work across systems
            String path = System.getProperty("user.dir") + "\\lib\\csv\\reservationdata.csv";

            //CSVWriter which overwrites file instead of appending to the end
            CSVWriter writer = new CSVWriter(new FileWriter(path, false));
            writer.writeNext(header);

            // for loop to iterate through List<String[]> and writes all contents of String[]
            for(Reservation c : reservations)
            {
                temp[0] = c.getConfirmationCode();
                temp[1] = c.getCheckInDate().toString();
                temp[2] = c.getCheckOutDate().toString();
                temp[3] = c.getCustomerID();
                temp[4] = String.valueOf(c.getRoomNumber());
                temp[5] = String.valueOf(c.getNumOccupants());
                temp[6] = c.getRoomType().toString();
                writer.writeNext(temp);
            }

            //close writer
            writer.close();

        } 
        catch(Exception e)
        {
            System.err.println(e.toString());
        }
    }

    public String toString()
    {
        return  "\nRSVP code: " + this.confirmation_code +
                "\nCheck In: " + this.checkInDate.toString() +
                "\nCheck Out: " + this.checkOutDate.toString() +
                "\nCustomer ID: " + this.customer_uid +
                "\nRoom Number: " + this.room_number + 
                "\nOccupants: " + this.occupants + 
                "\nRoom Type: " + this.type.toString();
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