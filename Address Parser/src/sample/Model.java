package sample;

import java.io.*;
import java.io.FileReader;
import java.util.*;

public class Model {
    private String streets, cities, zipcode;
    FileReader reader;

    public Model() throws FileNotFoundException {
        try{
            //streets = readFile("streets.txt");
            cities = readFile("/Users/Vilfred/Desktop/ITU/2. semester/Algorithms/Algo Git/algo-exercises/Address Parser/src/resources/textfiles/cities.txt");
            //zipcode = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //createData();
    }
    public String getCities(){
        return cities;
    }
    /*public List<String> getStreets(){
        return streets;
    }
    public void getCities(){
        //createData("cities.txt");
    }
    public List<String> getZipcode(){
        return zipcode;
    } */
    /*private void createData(String file, List<String> list) {
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
    } */
    private String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader (file));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }
}
