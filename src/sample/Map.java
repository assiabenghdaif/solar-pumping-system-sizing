package sample;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Map implements Initializable {

    @FXML
    Button reduire;
    @FXML Button close;
    @FXML SplitPane splitpane;

    @FXML
    private VBox vbox;
    @FXML
    private Pane panmap;
    @FXML
    private ComboBox comboBox;


    @FXML
    private Button next;
    @FXML
    private Pane pane;
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
    public void reduireAction(MouseEvent e){
        Stage stage=(Stage) reduire.getScene().getWindow();
        stage.setIconified(true);
    }
    @FXML
    public void handle() {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load(getClass().getResource("simple_map.html").toString());
        vbox.getChildren().addAll(webView);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        handle();
        Ville();
    }
     String address = " ";


    public void Ville() {


        comboBox = new ComboBox();
        comboBox.getItems().addAll(
                "Agadir",
                "Ain Harrouda",
                "Al Hoceima",
                "Aït Melloul",
                "Azrou",
                "Ben Guerir",
                "Beni Ansar",
                "Beni Mellal",
                "Benslimane",
                "Berkane",
                "Berrechid",
                "Bouskoura",
                "Casablanca",
                "Dar Bouazza",
                "Dcheira El Jihadia",
                "Drargua",
                "El Jadida",
                "El Kelaa Des Sraghna",
                "Errachidia",
                "Essaouira",
                "Fez",
                "Fnideq",
                "Fquih Ben Salah",
                "Guelmim",
                "Guercif",
                "Inezgane",
                "Kenitra",
                "Khemisset",
                "Khenifra",
                "Khouribga",
                "Ksar El Kebir",
                "Lahraouyine",
                "Larache",
                "Lqliaa ", "Marrakesh",
                "Martil",
                "M'diq",
                "Meknes", "Midelt",
                "Mohammedia",
                "Nador",
                "Ouarzazate", "Ouazzane",
                "Oued Zem",
                "Oujda",
                "Oulad Teima",
                "Rabat",
                "Safi",
                "Salé", "Sefrou",
                "Settat",
                "Sidi Bennour",
                "Sidi Kacem",
                "Sidi Slimane",
                "Skhirat",
                "Souk El Arbaa",
                "Suq as-Sabt", "Awlad an-Nama",
                "Tangier",
                "Tan-Tan",
                "Taourirt", "Taroudant",
                "Taza",
                "Temara",
                "Tetouan", "Tifelt",
                "Tiznit"
                ,"Youssoufia"
        );
        comboBox.setPromptText("City");
        comboBox.setEditable(true);
        comboBox.setLayoutX(310);
        comboBox.setLayoutY(25);
        //comboBox.setStyle("-fx-background-radius: 20;");
        comboBox.setPrefWidth(758);
        comboBox.setPrefHeight(31);

        comboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                address = t1;
            }
        });

        pane.getChildren().add(comboBox);
    }
    static String city;
    @FXML
    public void  nextAction(ActionEvent event) throws IOException {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        city= (String) comboBox.getValue();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("PompageInterface.fxml")));
        stage.setScene(scene);
        stage.show();
    }

}
