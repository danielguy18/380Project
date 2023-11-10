import com.opencsv.bean.CsvBindByPosition;

public class Hotel {
    @CsvBindByPosition(position = 0)
    private int hotelID;

    @CsvBindByPosition(position = 1)
    private String HotelName;

    @CsvBindByPosition(position = 2)
    private double Rating;

    @CsvBindByPosition(position = 3)
    private int Rooms;
}
