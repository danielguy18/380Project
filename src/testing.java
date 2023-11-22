//@author Zelgehai Zahid

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class testing {
    public static void main(String[] args) {
    //Database_Control myself = new Database_Control(6,"zelsHotel", 10);
    //System.out.println("This is my Hotel_ID: " + myself.getHotel_ID());    
    
    String file = "Hotel_Database.csv";
    BufferedReader reader = null;
    String line = "";

    try {
        reader = new BufferedReader(new FileReader(file));
        while((line = reader.readLine()) != null){
            //Continue reading the next line. If there is no Next line,
            //We will break out of the While Loop
            String[] row = line.split(";"); //specifices a character that is a string. splits all line as semi-colon
            //assigns to array row
                for(String index : row){
                    System.out.printf("%-15s", index);
                }
                System.out.println();

        }//end of While
    } catch (Exception e) {
        e.printStackTrace();    //Displays what went wrong if it does Catch Something.
    }
    finally{
        try {
			reader.close();     //Must Close the reader after opening one.
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    }
}
