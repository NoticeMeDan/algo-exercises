package sample;

import java.io.*;
import java.util.*;

public class Model {
    private List<String> streets, cities, zipcode;

    public Model() throws IOException {
        cities = addData("/textfiles/cities.txt");
        streets = addData("/textfiles/streets.txt");
        zipcode = addData("/textfiles/zipcode.txt");
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

    private List<String> addData(String path) throws IOException {
        List<String> list = new ArrayList<>();
        InputStream res =
                Main.class.getResourceAsStream(path);

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(res));
        String line;
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        return list;
    }
}
