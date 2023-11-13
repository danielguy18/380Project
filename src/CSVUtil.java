
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;




public class CSVUtil 
{


   static List<String[]> cdata = null;

   public static void loadCustomerData()
   {
      //String path = "\\src\\customerdata.csv";
      try 
      {
         CSVReader reader = new CSVReaderBuilder(new FileReader("src\\customerdata.csv")).build();
         cdata = reader.readAll();
      } 
      catch (Exception e) 
      {
         System.err.println(e.getMessage());
      }
   }

   public static void printList()
   {
      System.out.println(cdata.toString());
   }

   public static void saveCustomerData()
   {
      try
      {
         String path = System.getProperty("user.dir") + "\\src\\customerdata.csv";

         CSVWriter writer = new CSVWriter(new FileWriter(path, true));

         // feed in your array (or convert your data to an array)
         
         String[] entries = (String[]) cdata.toArray(); 
         writer.writeNext(entries);
         writer.close();
      } 
      catch(Exception e)
      {
         System.err.println(e.toString());
      }
   }




   
}
