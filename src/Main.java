import java.io.FileReader;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.opencsv.bean.CsvToBeanBuilder;

import javafx.util.converter.LocalDateStringConverter;

public class Main 
{

    public static void main(String []args) throws Exception
    {
        LocalDate date = LocalDate.now();
        System.out.println(date.toString());
        String fn,ln,add,em,bday;
        Scanner scanner = new Scanner(System.in);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        formatter = formatter.withLocale(Locale.US);  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
        
        
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
        scanner.close();



        LocalDate formatted_date = LocalDate.parse(bday, formatter);

        Customer cust = new Customer(fn, ln, em, add, formatted_date);
        System.out.println(cust.toString());
        CSVUtil.loadCustomerData();
        CSVUtil.printConsoleCSV();
        CSVUtil.printList(); 


        

        return;
    }

}
