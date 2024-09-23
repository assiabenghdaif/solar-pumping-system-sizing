package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class vboxContoroller implements Initializable {

    @FXML VBox vbox;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load(getClass().getResource("simple_map.html").toString());
        vbox.getChildren().addAll(webView);
    }
}
