import java.io.FileReader;
import java.time.*;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

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

   

   



}