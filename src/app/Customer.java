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

public class Customer
{

    //variable which stores customer data which is read from file
    private static List<String[]> customer_data = null;

    //list which holds all customers
    private static List<Customer> customers = new ArrayList<Customer>();

    private String UID;
    private String first_name;
    private String last_name;
    private String email;
    private String address;
    private LocalDate birthday;

    public Customer()
    {
        this.UID = generateUID();
        this.first_name = null;
        this.last_name = null;
        this.email = null;
        this.address = null;
        this.birthday = null;
    }

    public Customer(String fn, String ln, String em, String add, LocalDate bday)
    {
        this.UID = generateUID();
        this.first_name = fn;
        this.last_name = ln;
        this.email = em;
        this.address = add;
        this.birthday = bday;
    }

    
    /** 
     * @return String
     */
    public String getFirstName()
    {
        return this.first_name;
    }

    
    /** 
     * @param fname
     */
    public void setFirstName(String fname)
    {
        this.first_name = fname;
    }

    
    /** 
     * @return String
     */
    public String getLastName()
    {
        return this.last_name;
    }

    
    /** 
     * @param lname
     */
    public void setLastName(String lname)
    {
        this.last_name = lname;
    }

    
    /** 
     * @return String
     */
    public String getEmail()
    {
        return this.email;
    }

    
    /** 
     * @param email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    
    /** 
     * @return String
     */
    public String getAddress()
    {
        return this.address;
    }

    
    /** 
     * @param address
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    
    /** 
     * @return LocalDate birthday of customer
     */
    public LocalDate getBirthday()
    {
        return this.birthday;
    }

    
    /** 
     * @param LocalDate birthday of customer
     */
    public void setBirthday(LocalDate bday)
    {
        this.birthday = bday;
    }

    
    /** 
     * @return customer data in string format.
     */
    public String toString()
    {
        return this.UID + "\n" + this.first_name + " " + this.last_name + "\n" + this.email + "\n" + this.address + "\n" + this.birthday.toString();
    }

    
    /** 
     * @param Customer -customer data and returnd if the customer has a complete profile.
     * @return if the customer object is complete.
     */
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


    
    /** 
     * @param adds the customer to the list of customers
     */
    public static void addCustomer(Customer customer)
    {
        customers.add(customer);
    }

    
    /** 
     * @param enter ID of the desired customer
     * @return Customer of the desired ID
     */
    public static Customer getCustomer(String id)
    {
        for(Customer temp : customers)
        {
            if(temp.UID == id)
            {
                return temp;
            }
        }
        return null;
    }

     
    public void setCustomer(String id)
    {
        this.UID = id;
    }

    public String getCustomerID()
    {
        return this.UID;
    }

   //loads customer data from file and stores all info into List<String[]>
   //this prevents reading/writing to file consistently.
   public static void loadCustomerData()
   {
        try 
        {
            //create instance of reader
            CSVReader reader = new CSVReaderBuilder(new FileReader("lib\\csv\\customerdata.csv")).withSkipLines(1).build();

            //store all contents of file into a List<String[]>
            customer_data = reader.readAll();

            //the following adds the contents of customer_data into a list of customers for easy manipulation later
            //loop through all String[] entries within List<String[]>

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            formatter = formatter.withLocale(Locale.US);
            for(String[] array : customer_data) 
            {
                Customer cust = new Customer();
                cust.UID = array[0];
                cust.setFirstName(array[1]);
                cust.setLastName(array[2]);
                cust.setAddress(array[3]);
                cust.setEmail(array[4]);
                LocalDate formatted_date = LocalDate.parse(array[5], formatter);

                cust.setBirthday(formatted_date);

                customers.add(cust);
            }
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
        for(Customer cust : customers)
        {
            System.out.println(cust.toString());
        }
   }

   public static void printListString()
   {
        for(String[] s : customer_data)
        {
            
            for(String c : s)
            {
                System.out.println(c);
            }
        }
   }


    //saves customer data from List<String[]> array and writes it to file.
    
    public static void saveCustomerData()
    {
        String[] header = {"Customer ID", "First Name", "Last Name", "Address", "Email", "Birthday"};
        String[] temp = {"", "", "", "", "", ""};
        try
        {
            //path which should work across systems
            String path = System.getProperty("user.dir") + "\\lib\\csv\\customerdata.csv";

            //CSVWriter which overwrites file instead of appending to the end
            CSVWriter writer = new CSVWriter(new FileWriter(path, false));
            writer.writeNext(header);

            // for loop to iterate through List<Customer> and write to file
            for(Customer c : customers)
            {
                temp[0] = c.getCustomerID();
                temp[1] = c.getFirstName();
                temp[2] = c.getLastName();
                temp[3] = c.getAddress();
                temp[4] = c.getEmail();
                temp[5] = c.getBirthday().toString();
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

    
    /** 
     * @return String of the customers UID
     */
    public String generateUID() 
    {
        String characters = "0123456789"; // Excludes confusing characters
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 7; i++) 
        {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
    }
}