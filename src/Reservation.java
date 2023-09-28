public class Reservation
{
    private int time;
    private Customer customer;
    private int room_number;



    public Reservation()
    {
        this.time = 0;
        this.customer = null;
        this.room_number = 0;
    }

    public Reservation(int time, Customer customer, int room_number)
    {
        this.time = time;
        this.customer = customer;
        this.room_number = room_number;
    }

    public int getRoomNumber()
    {
        return this.room_number;
    }

    public Customer getCustomer()
    {
        return this.customer;
    }

    public int getReservation()
    {
        return 0;
    }


}