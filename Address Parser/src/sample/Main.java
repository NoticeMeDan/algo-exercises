package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../resources/sample.fxml"));
        primaryStage.setTitle("Address Parser");
        primaryStage.setScene(new Scene(root, 1280, 780));
        primaryStage.show();

    }


    public static void main(String[] args) throws IOException {
        Model model = new Model();
        model.getCities();
        //FileReader reader = new FileReader("cities.txt",);
        //launch(args);
    }
}
