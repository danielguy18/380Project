import java.util.Date;

public class Reservation
{
    private Date date;
    private Customer customer;
    private int room_number;



    public Reservation()
    {
        this.date = null;
        this.customer = null;
        this.room_number = -1;
    }

    public Reservation(Date date, Customer customer, int room_number)
    {
        this.date = date;
        this.customer = customer;
        this.room_number = room_number;
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

    public Date getReservationDate()
    {
        return this.date;
    }

    public void setReservationDate(Date date)
    {
        this.date = date;
    }

    public boolean isComplete(Reservation rsvp)
    {
        if(rsvp.customer == null)
        {
            return false;
        }
        if(rsvp.room_number == -1)
        {
            return false;
        }
        if(rsvp.date == null)
        {
            return false;
        }
        return true;
    }

    public void saveReservation(Reservation rsvp)
    {
        if(!(isComplete(rsvp) == true))
        {
            return;
        }

        //save to file
        
    }


}