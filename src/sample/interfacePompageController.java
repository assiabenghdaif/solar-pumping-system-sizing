package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class interfacePompageController implements Initializable
{

    @FXML
    ImageView reduire;
    @FXML
    ImageView close;
    @FXML
    AnchorPane pane;
    @FXML
    ImageView imageFirst;
    @FXML
    ComboBox<String> combobox;
    @FXML
    public void reduireAction(MouseEvent e){
        Stage stage=(Stage) reduire.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void besoin(MouseEvent e){
        imageFirst.setImage(new Image("sample/Images/besoin.png"));
    }

    @FXML
    public void pipe(MouseEvent e){
        imageFirst.setImage(new Image("sample/Images/pipe.png"));
    }

    @FXML
    public void normal(MouseEvent e){
        imageFirst.setImage(new Image("sample/Images/KIT-POMPE-POMPAGE-SOLAIRE.png"));
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
    public void ChampsPV(){
        try {
            Parent box=FXMLLoader.load(getClass().getResource("champsPV.fxml"));
            pane.getChildren().setAll(box);
        } catch (IOException e3) {}
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        combobox.getItems().setAll("polyethylene ","steel");
    }
}