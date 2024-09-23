package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class champsPOMPEController implements Initializable {
    @FXML
    ComboBox<String> combobox;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        combobox.getItems().setAll("polyethylene ","steel");
    }
}
