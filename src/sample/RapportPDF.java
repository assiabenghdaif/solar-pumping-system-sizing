package sample;

import com.pdfjet.Letter;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileOutputStream;

public class RapportPDF extends Application {
    private FileChooser fc=new FileChooser();
    @Override
    public void start(Stage stage) throws Exception {


        Button btn=new Button();
        btn.setText("save PDF");

        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF file","*.pdf"));
        fc.setTitle("save as PDF");
        fc.setInitialFileName("untitled.pdf");
        btn.setOnAction((ActionEvent event) -> {
        //System.out.println("hello world");
            File file=fc.showSaveDialog(stage);
            if(file!=null){
                String str=file.getAbsolutePath();
                try {
                    FileOutputStream fos=new FileOutputStream(str);
                    PDF pdf=new PDF(fos);
                    Page page=new Page(pdf, Letter.LANDSCAPE);
                    pdf.close();
                    fos.flush();
                }catch (Exception e){}
            }
        });
        StackPane root=new StackPane();

        root.getChildren().add(btn);
        //stage.setScene(new Scene(root, 300, 275));
        Scene scene=new Scene(root);
        // stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
