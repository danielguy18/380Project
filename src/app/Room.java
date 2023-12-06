package app;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

public class Room 
{
    public static int MAX_ROOMS = 20;

    enum RoomType
    {
        QUEEN, KING
    }


    private int room_number;
    private RoomType type;
    private boolean available;
    private double price;
    private String confirmation_code;

    public Room()
    {
        this.room_number = -1;
        this.type = null;
        this.available = false;
        this.price = 0.0;
        this.confirmation_code = "";
    }

    public boolean isAvailable()
    {
        return this.available;
    }

    public int getRoomNumber()
    {
        return this.room_number;
    }

    public RoomType getRoomType()
    {
        return this.type;
    }

    public double getPrice()
    {
        return this.price;
    }
    
    public String getRSVPCode()
    {
        return this.confirmation_code;
    }

    private static List<String[]> room_data = new ArrayList<String[]>();
    private static List<Room> room_list = new ArrayList<Room>();

    public static void loadRoomData()
    {
        try 
        {
            //create instance of reader
            CSVReader reader = new CSVReaderBuilder(new FileReader("lib\\csv\\mainDatabase.csv")).withSkipLines(1).build();
            
            //store all contents of file into a List<String[]>
            room_data = reader.readAll();

            //the following adds the contents of customer_data into a list of customers for easy manipulation later
            //loop through all String[] entries within List<String[]>
            for(String[] array : room_data) 
            {
                Room room = new Room();
                room.room_number = Integer.parseInt(array[0]);
                room.type = RoomType.valueOf(array[1]);
                room.available = Boolean.parseBoolean(array[2]);
                room.price = Double.parseDouble(array[3]);
                room.confirmation_code = array[4];

                room_list.add(room); 
            }
        } 
        catch (Exception e) 
        {
            System.err.println(e.getMessage());
        }
    }

    public static void printRoomData() 
    {
        for(Room room : room_list)
        {
           System.out.println(room.toString());
        }
    }

    public String toString()
    {
        String info = 
            "\nRoom #: " + this.room_number + 
            "\nRoom Type: " + this.type.toString() + 
            "\nAvailable?: " + String.valueOf(this.available) + 
            "\nPrice " + this.price + 
            "\nRSVP Code: " + this.confirmation_code;
        return info;
    }

    public static void saveRoomData()
    {

        String[] header = {"Room #", "Type", "Available?", "Price", "RSVP Code"};
        
        try
        {
            //path which should work across systems
            String path = System.getProperty("user.dir") + "\\lib\\csv\\mainDatabase.csv";

            //CSVWriter which overwrites file instead of appending to the end
            CSVWriter writer = new CSVWriter(new FileWriter(path, false));
            writer.writeNext(header);

            // for loop to iterate through List<Customer> and write to file
            for(Room r : room_list)
            {
                String[] temp = {"", "", "", "", ""};
                temp[0] = String.valueOf(r.getRoomNumber());
                temp[1] = r.getRoomType().toString();
                temp[2] = Boolean.toString(r.isAvailable());
                temp[3] = String.valueOf(r.getPrice());
                temp[4] = r.getRSVPCode();
                
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
}
