

public class Database_Control {

    public int Hotel_ID;
    public String Hotel_Name;
    public int Hotel_Rating;

    public Database_Control(int Hotel_ID, String Hotel_Name, int Hotel_Rating){
        this.Hotel_ID= Hotel_ID;
        this.Hotel_Name= Hotel_Name;
        this.Hotel_Rating= Hotel_Rating;
    }

    public int getHotel_ID(){
        return Hotel_ID;
    }


}
