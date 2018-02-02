package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    private Model model = new Model();

    public TextField input;
    public Button enter;
    public AutoCompleteTextField streetInput;
    public AutoCompleteTextField zipcodeInput;
    public AutoCompleteTextField cityInput;


    public void enterCity(ActionEvent actionEvent) {
        
    }

    public void enterPostal(ActionEvent actionEvent) {
        
    }

    public void enterStreet(ActionEvent actionEvent) {
    }

    public void setAddress(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        streetInput.getEntries().addAll(model.getStreets());
        cityInput.getEntries().addAll(model.getCities());
        zipcodeInput.getEntries().addAll(model.getZipcode());
    }
}
