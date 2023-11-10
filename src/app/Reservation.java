package app;
import java.time.*;

public class Reservation
{
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Customer customer;
    private int room_number;



    public Reservation()
    {
        this.checkInDate = null;
        this.checkOutDate = null;
        this.customer = null;
        this.room_number = -1;
    }

    public Reservation(LocalDate checkInDate, LocalDate checkOutDate, Customer customer, int room_number)
    {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
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

    public LocalDate getCheckInDate()
    {
        return this.checkInDate;
    }

    public void setCheckInDate(LocalDate date)
    {
        this.checkInDate = date;
    }

    public LocalDate getCheckOutDate()
    {
        return this.checkOutDate;
    }

    public void setCheckOutDate(LocalDate date)
    {
        this.checkOutDate = date;
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
        if(rsvp.checkInDate == null)
        {
            return false;
        }
        if(rsvp.checkOutDate == null)
        {
            return false;
        }
        return true;
    }

    public int saveReservation(Reservation rsvp)
    {
        int confirmation = -1;
        if(!(isComplete(rsvp) == true))
        {
            return 0;
        }

        return confirmation;
        //save to file

    }

    public Reservation getRSVP(int confirmation)
    {
        Reservation r = new Reservation();
        return r;
    }


}