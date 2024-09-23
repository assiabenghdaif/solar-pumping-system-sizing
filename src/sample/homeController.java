package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class homeController implements Initializable {
    @FXML
    private Button close;

    @FXML
    private Button reduire;

    @FXML
    private Button start;
    @FXML
    private Separator sep;
    @FXML
    private Label check;

    @FXML
    private void closeAction(MouseEvent e) {

        System.exit(0);
    }
    @FXML
    private void handleMeteo(MouseEvent e) throws IOException, URISyntaxException {
        if(e.getSource()==check) {
            new MeteoWeb();
        }
    }

    @FXML
    public void reduireAction(MouseEvent e) {
        if (e.getSource() == reduire) {
            Stage stage = (Stage) reduire.getScene().getWindow();
            stage.setIconified(true);
        }
    }

    @FXML
    public void handleClicks(javafx.event.ActionEvent event) throws IOException, URISyntaxException {
        if (event.getSource() == start) {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("mapLocation.fxml")));
            stage.setScene(scene);
            stage.show();


        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}