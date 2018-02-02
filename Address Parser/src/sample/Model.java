package sample;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Model {
    private List<String> streets, cities, zipcode;

    public Model(){
        streets = new ArrayList<>();
        cities = new ArrayList<>();
        zipcode = new ArrayList<>();
        createData("cities.txt");
        createData("streets.txt");
        createData("zipcode.txt");
    }
    public List<String> getStreets(){
        return streets;
    }
    public void getCities(){
        createData("cities.txt");
    }
    public List<String> getZipcode(){
        return zipcode;
    }
    private void createData(String file) {
        Scanner in;
        StringBuilder sb;

        try {
            in = new Scanner(new FileReader(file));
            sb = new StringBuilder();
            while (in.hasNext()) {
                sb.append(in.next());
            }
            in.close();
            System.out.println("hey");
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
