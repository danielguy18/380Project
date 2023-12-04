package app;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

public class CustomerTest {

    @Test
    public void testSaveCustomerData()
    {
        List<Customer> customers = new ArrayList<Customer>();
        String[] temp = {"", "", "", "", "", ""};
        try
        {
            //path which should work across systems
            String path = System.getProperty("user.dir") + "\\lib\\csv\\customerdata.csv";
            //CSVWriter which overwrites file instead of appending to the end
            CSVWriter writer = new CSVWriter(new FileWriter(path, false));
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
}
