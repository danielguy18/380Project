import java.io.FileReader;
import java.io.FileWriter;
import java.time.*;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

public class Customer
{
    private String first_name;
    private String last_name;
    private String email;
    private String address;
    private LocalDate birthday;

    public Customer()
    {
        this.first_name = null;
        this.last_name = null;
        this.email = null;
        this.address = null;
        this.birthday = null;
    }

    public Customer(String fn, String ln, String em, String add, LocalDate bday)
    {
        this.first_name = fn;
        this.last_name = ln;
        this.email = em;
        this.address = add;
        this.birthday = bday;
    }

    public String getFirstName()
    {
        return this.first_name;
    }

    public void setFirstName(String fname)
    {
        this.first_name = fname;
    }

    public String getLastName()
    {
        return this.last_name;
    }

    public void setLastName(String lname)
    {
        this.last_name = lname;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getAddress()
    {
        return this.address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public LocalDate getBirthday()
    {
        return this.birthday;
    }

    public void setBirthday(LocalDate bday)
    {
        this.birthday = bday;
    }

    public String toString()
    {
        return this.first_name + " " + this.last_name + "\n" + this.email + "\n" + this.address + "\n" + this.birthday.toString();
    }
    public static boolean isComplete(Customer customer)
    {
        if(customer.first_name == null)
        {
            return false;
        }
        if(customer.last_name == null)
        {
            return false;
        }
        if(customer.email == null)
        {
            return false;
        }
        if(customer.address == null)
        {
            return false;
        }
        if(customer.birthday == null)
        {
            return false;
        }
        return true;
    }

    //variable which stores customer data which is read from file
    private static List<String[]> customer_data = null;

    public static void addCustomer(Customer customer)
    {
        if(isComplete(customer) == true)
        {
            String[] temp = {customer.first_name, customer.last_name, customer.address, customer.email, customer.birthday.toString()};
            customer_data.add(temp);
        }
    }

   //loads customer data from file and stores all info into List<String[]>
   //this prevents reading/writing to file consistently.
   public static void loadCustomerData()
   {
      try 
      {
         //create instance of reader
         CSVReader reader = new CSVReaderBuilder(new FileReader("src\\customerdata.csv")).build();

         //store all contents of file into a List<String[]>
         customer_data = reader.readAll();
         
      } 
      catch (Exception e) 
      {
         System.err.println(e.getMessage());
      }
   }


   //prints contents of the List<String[]> array
   //mainly for testing purposes
   public static void printCustomerData() 
   {
      //loop through all String[] entries within List<String[]>
      for (String[] array : customer_data) 
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


   //saves customer data from List<String[]> array and writes it to file.
   public static void saveCustomerData()
   {
      try
      {
         //path which should work across systems
         String path = System.getProperty("user.dir") + "\\src\\customerdata.csv";

         //CSVWriter which overwrites file instead of appending to the end
         CSVWriter writer = new CSVWriter(new FileWriter(path, false));

         // for loop to iterate through List<String[]> and writes all contents of String[]
         for (String[] array : customer_data) 
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