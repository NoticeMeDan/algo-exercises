package sample;

import java.io.*;
import java.util.*;

public class Model {
    private List<String> streets, cities, zipcode;

    public Model() throws FileNotFoundException {
        cities = addData("/Users/Vilfred/Desktop/ITU/2. semester/Algorithms/Algo Git/algo-exercises/Address Parser/src/resources/textfiles/cities.txt");
        streets = addData("/Users/Vilfred/Desktop/ITU/2. semester/Algorithms/Algo Git/algo-exercises/Address Parser/src/resources/textfiles/streets.txt");
        zipcode = addData("/Users/Vilfred/Desktop/ITU/2. semester/Algorithms/Algo Git/algo-exercises/Address Parser/src/resources/textfiles/zipcode.txt");
    }
    public List<String> getCities(){
        return cities;
    }
    public List<String> getStreets(){
        return streets;
    }
    public List<String> getZipcode(){
        return zipcode;
    }

    private ArrayList<String> addData(String file){
        File loadFile = new File(file);
        ArrayList<String> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(loadFile);
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            //TODO: Show popup
            System.out.println("Couldn't find file!");
            System.exit(1);
        }
        return list;
    }
}
