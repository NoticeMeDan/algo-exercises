package sample;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Model {
    private Set<String> streets, cities, zipcode;

    public Model(){
        streets = new TreeSet<>();
        cities = new TreeSet<>();
        zipcode = new TreeSet<>();
        createData("cities.txt", cities);
        createData("streets.txt", streets);
        createData("zipcode.txt", zipcode);
    }
    public Set<String> getStreets(){
        return streets;
    }
    public Set<String> getCities(){
        return cities;
    }
    public Set<String> getZipcode(){
        return zipcode;
    }
    private void createData(String file, Set<String> listName) {
        Scanner sc;
        try {
            sc = new Scanner(new File(file), "UTF-8");

            while (sc.hasNext()) {
                listName.add(sc.nextLine());
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
