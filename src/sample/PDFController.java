package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class PDFController implements Initializable {
    @FXML TableView tablePUMP;
    @FXML TableView tablePV;
    @FXML TableColumn dataPV;
    @FXML TableColumn valuePV;
    @FXML TableColumn dataPUMP;
    @FXML TableColumn valuePUMP;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
