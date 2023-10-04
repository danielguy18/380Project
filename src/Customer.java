import java.util.Date;

public class Customer
{
    private String first_name;
    private String last_name;
    private String email;
    private String address;
    private Date birthday;

    public Customer()
    {
        this.first_name = null;
        this.last_name = null;
        this.email = null;
        this.address = null;
        this.birthday = null;
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

    public Date getBirthday()
    {
        return this.birthday;
    }

    public void setBirthday(Date bday)
    {
        this.birthday = bday;
    }


}