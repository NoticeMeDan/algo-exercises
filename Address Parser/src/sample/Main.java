package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../resources/sample.fxml"));
        primaryStage.setTitle("Address Parser");
        primaryStage.setScene(new Scene(root, 960, 540));
        primaryStage.show();

    }


    public static void main(String[] args) throws IOException {
        Model model = new Model();
      /*  System.out.println(model.getCities());
        System.out.println(model.getStreets());
        System.out.println(model.getZipcode());
*/
        launch(args);
    }
}
