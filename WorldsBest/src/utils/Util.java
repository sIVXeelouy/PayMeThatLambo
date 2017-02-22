package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Util { //this class will load the world.txt files into the world object

    public static String loadFileAsString(String path) {
        StringBuilder builder = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line + "\n");
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return builder.toString();
    }

    public static int parseInt (String number) {
        try {
            return Integer.parseInt(number);
        }catch(NumberFormatException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
}
