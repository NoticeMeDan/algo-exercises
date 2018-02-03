package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    //File filename = new File("textfiles/cities.txt");
    Scanner sc;

    public FileReader(String file, List<String> list) {
        sc = new Scanner(file);

        try {
            while (sc.hasNextLine()) {
                list.add(sc.nextLine());
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
