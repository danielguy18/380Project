import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main 
{

    public static void main(String []args) throws Exception
    {
        LocalDate date = LocalDate.now();
        System.out.println(date.toString());
        String fn,ln,add,em,bday;
        Scanner scanner = new Scanner(System.in);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        formatter = formatter.withLocale(Locale.US);  
        
        
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
        Customer.loadCustomerData();
        Customer.printCustomerData();
        Customer.addCustomer(cust);
        Customer.saveCustomerData();
        Customer.printCustomerData();;


        

        return;
    }

}
