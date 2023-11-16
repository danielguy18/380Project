import java.io.FileReader;
import java.io.FileWriter;
import java.time.*;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

public class Reservation
{
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Customer customer;
    private Room.RoomType type;
    private int room_number;
    private int confirmation;
    private int occupants;

    public Reservation()
    {
        this.checkInDate = null;
        this.checkOutDate = null;
        this.customer = null;
        this.room_number = -1;
        this.type = null;
        this.confirmation = -1;
        this.occupants = 0;
    }

    public Reservation(LocalDate checkInDate, LocalDate checkOutDate, Customer customer, Room.RoomType type, int room_number, int confirmation, int occupants)
    {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.customer = customer;
        this.room_number = room_number;
        this.type = type;
        this.confirmation = confirmation;
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

    public int getConfirmationNumber()
    {
        return this.confirmation;
    }

    public void setConfirmationNumber(int confirmation)
    {
        this.confirmation = confirmation;
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
        if(rsvp.confirmation <= 0)
        {
            return false;
        }
        if(rsvp.confirmation > 0)
        {
            return false;
        }
        return true;
    }

    public int saveReservation(Reservation rsvp)
    {
        int confirmation = -1;
        if(!(isComplete(rsvp) == true))
        {
            return 0;
        }

        return confirmation;
        //save to file

    }

    public int getConfirmation()
    {
        return confirmation;
    }




    private static List<String[]> reservation_data = null;

    public static void addReservation(Reservation reservation)
    {
        if(isComplete(reservation) == true)
        {
            String[] rsvp = 
            {
                reservation.checkInDate.toString(), 
                reservation.checkOutDate.toString(), 
                reservation.type.toString(), 
                reservation.room_number + "", 
                reservation.confirmation + "", 
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
         //create instance of reader
         CSVReader reader = new CSVReaderBuilder(new FileReader("src\\reservationdata.csv")).build();

         //store all contents of file into a List<String[]>
         reservation_data = reader.readAll();
         
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


}