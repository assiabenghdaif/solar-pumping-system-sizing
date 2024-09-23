package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class champsPVController {
    @FXML
    ImageView reduire;
    @FXML
    ImageView imageFirst;
    @FXML
    public void returnAction(javafx.event.ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("PompageInterface.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void closeAction(MouseEvent e){
        Node node=(Node)e.getSource();
        Stage stage=(Stage)node.getScene().getWindow();
        stage.close();

        Scene scene;
        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("HomeInterface.fxml")));
            stage.setScene(scene);

            stage.show();

        }
        catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
    @FXML
    public void power(MouseEvent e){
        imageFirst.setImage(new Image("sample/Images/PVZoom.png"));
    }

    @FXML
    public void pump(MouseEvent e){
        imageFirst.setImage(new Image("sample/Images/pompeZoom.png"));
    }
    @FXML
    public void normal(MouseEvent e){
        imageFirst.setImage(new Image("sample/Images/KIT-POMPE-POMPAGE-SOLAIRE.png"));
    }
    @FXML
    public void reduireAction(MouseEvent e){
        Stage stage=(Stage) reduire.getScene().getWindow();
        stage.setIconified(true);
    }
}
