package sample;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class bingMap implements Initializable {

    @FXML
    private AnchorPane anch;

        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        handle();
    }
    @FXML
            public void handle() {
                try {
                    File file = new File("src/sample/simple_map.html");
                    URL url = file.toURI().toURL();
                    // file:/C:/test/a.html
                    System.out.println("Local URL: " + url.toString());
                    webEngine.load(url.toString());
                    anch.getChildren().addAll(browser);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            }






}
