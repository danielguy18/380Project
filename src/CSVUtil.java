import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.opencsv.CSVIterator;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;



public class CSVUtil 
{

   public static void printConsoleCSV() 
   {
      try 
      {
         Reader in = new FileReader("C:\\Users\\danie\\OneDrive\\Documents\\GitHub\\380Test-1\\src\\customerdata.csv");
         Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
         for (CSVRecord record : records) 
         {
            System.out.println(record.get(0) + ", " + record.get(1) + ", "+ record.get(2) + ", " + record.get(3) + ", " + record.get(4));

         }
      } 
      catch (Exception e) 
      {
         System.err.println(e.getMessage());
      }

   }


   static List<String[]> data = null;

   public static void loadCustomerData()
   {
      String path = "C:\\Users\\danie\\OneDrive\\Documents\\GitHub\\380Test-1\\src\\customerdata.csv";
      try 
      {
         CSVReader reader = new CSVReaderBuilder(new FileReader(path)).build();
         data = reader.readAll();
      } 
      catch (Exception e) 
      {
         System.err.println(e.getMessage());
      }
   }

   public static void printList()
   {
      System.out.println(data.toString());
   }

   public void saveCustomerData(List<String[]> data)
   {

   }




   
}
