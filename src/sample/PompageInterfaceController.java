package sample;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PompageInterfaceController implements Initializable
{

    @FXML TextField daily;
    @FXML TextField hours;
    @FXML TextField pumping_height;
    @FXML TextField pipe_diameter;
    @FXML TextField pipe_length;
    @FXML TextField code;
    @FXML TextField vane;
    @FXML TextField ks;
    @FXML TextField perte_number;
    @FXML Button reduire;
    @FXML Button close;
    @FXML AnchorPane pane;
    @FXML ImageView imageFirst;
    @FXML ComboBox<String> combobox;
    //@FXML ComboBox<String> comboCity;
    @FXML TextField pump_pwer;
    @FXML TextField voltage_ump;
    @FXML TextField current_isc;
    @FXML TextField peak_power;
    @FXML TextField max_v_in;
    @FXML TextField min_v_in;
    @FXML TextField max_c_in;
    @FXML TextField efficiency;
    @FXML TextField perte;
    @FXML TextField serie;
    @FXML Label LeftStatus;
    @FXML Label rightStatus;
    private int minute;
    private int hour;
    private int second;

    private static String FILE = "C:\\Users\\admin\\OneDrive\\Documents\\untitled.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 20,
            Font.BOLD, BaseColor.BLUE);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    @FXML public void reduireAction(MouseEvent e){
        Stage stage=(Stage) reduire.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML public void besoin(MouseEvent e){ imageFirst.setImage(new Image("sample/Images/besoin.png")); }

    @FXML public void pipe(MouseEvent e){
        imageFirst.setImage(new Image("sample/Images/pipe.png"));
    }

    @FXML public void normal(MouseEvent e){ imageFirst.setImage(new Image("sample/Images/KIT-POMPE-POMPAGE-SOLAIRE.png")); }

    @FXML public void power(MouseEvent e){ imageFirst.setImage(new Image("sample/Images/PVZoom.png")); }

    @FXML public void pump(MouseEvent e){
        imageFirst.setImage(new Image("sample/Images/pompeZoom.png"));
    }

    @FXML public void closeAction(MouseEvent e){
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        combobox.getItems().setAll("polyethylene","steel");
        LeftStatus.setText(DateFormat.getDateInstance().format(new java.util.Date()));
        //rightStatus.setText(DateFormat.getTimeInstance().format(new java.util.Date()));
        Timeline clock = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {
            second = LocalDateTime.now().getSecond();
            minute = LocalDateTime.now().getMinute();
            hour = LocalDateTime.now().getHour();
            rightStatus.setText(hour + ":" + (minute) + ":" + second);
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
    
    public float debit(){
        int var1=Integer.valueOf(daily.getText());
        int var2=Integer.valueOf(hours.getText());
        float var3=(float)var1/var2;
        return var3;
    }
    public float Diamètre_en_m(){
        float Diamètre_en_m= (float) (Float.valueOf(pipe_diameter.getText())*(Math.pow(10,-3)));
        return Diamètre_en_m;
    }
    public double vitesse(){
        double vitesse=(4*debit())/(Math.PI*(Math.pow(Diamètre_en_m(),2))*3600);
        return vitesse;
    }
    public double reynolds(){
        double reynolds=(1000*vitesse()*Diamètre_en_m())/(Math.pow(10,-3));
        return reynolds;
    }
    public String Régime_écoulement(){
        String Régime_écoulement;
        if(reynolds()<Math.pow(10,5)) return Régime_écoulement="turbulent lisse";
        else return Régime_écoulement="turbulent rugueux";
    }
    public float Rigosité_conduite(){
        float Rigosité_conduite;
        if(combobox.getValue().equals("polyethylene")==true) {//System.out.println("polyethylene");
            return Rigosité_conduite=0.03F;}
        else return Rigosité_conduite=0.09F;
    }
    public double Coefficient_blasius(){
        double Coefficient_blasius=0.3164*(Math.pow(reynolds(),-0.25));
        return Coefficient_blasius;
    }
    public double Coefficient_colebrook(){
        double Coefficient_colebrook=1/(Math.pow((-2)*Math.log((2.51*Math.sqrt(Coefficient_blasius())/reynolds())+( (Rigosité_conduite()*Math.pow(10,-3))/ Diamètre_en_m() *3.71)),2));
        return Coefficient_colebrook;
    }
    public double erreur(){
        double erreur =Math.abs(Coefficient_blasius()-Coefficient_colebrook());
        return erreur;
    }
    public double Coefficient_prendre(){
        double Coefficient_prendre;

        if(0.001<erreur())
            return Coefficient_prendre=1/Math.pow(((-2*Math.log(2.51/reynolds()*((Math.pow(Coefficient_colebrook(),0.5))+((Rigosité_conduite()*Math.pow(10,-3))/(Diamètre_en_m()*3.71)))))),2);
        else
            return Coefficient_prendre=Coefficient_colebrook();
    }
    public double pertes_regulieres(){
        double pertes_regulieres=(Coefficient_prendre()*Float.valueOf(pipe_length.getText())*(Math.pow(reynolds(),2)))/(2*Diamètre_en_m()*9.81);
        return pertes_regulieres;
    }
    public double pertes_singulieres(){
        double pertes_singulieres=((Float.valueOf(code.getText())*1.13+Float.valueOf(vane.getText())*1.7+Float.valueOf(perte_number.getText())*Float.valueOf(ks.getText()))*(Math.pow(reynolds(),2)))/(2*9.81);
        return pertes_singulieres;
    }
    public int HMT(){
        
        /*System.out.println("Diamètre_en_m"+Diamètre_en_m);
        System.out.println("vitesse"+vitesse);
        System.out.println("reynolds"+reynolds);
        System.out.println("Rigosité_conduite"+Rigosité_conduite);
        
        System.out.println("Coefficient_blasius"+Coefficient_blasius);
        //double Coefficient_colebrook=1/((Math.pow((-2*Math.log10((2.51/reynolds*(Math.pow(Coefficient_blasius,0.5))+((Rigosité_conduite*Math.pow(10,-3)))/Diamètre_en_m*3.71))),2)));
        System.out.println("Coefficient_colebrook"+Coefficient_colebrook);
        System.out.println("erreur"+erreur);
        double Coefficient_prendre;
        if(0.001<erreur)
            Coefficient_prendre=1/Math.pow(((-2*Math.log(2.51/reynolds*((Math.pow(Coefficient_colebrook,0.5))+((Rigosité_conduite*Math.pow(10,-3))/(Diamètre_en_m*3.71)))))),2);
        else
            Coefficient_prendre=Coefficient_colebrook;
        System.out.println("Coefficient_prendre"+Coefficient_prendre);
        double pertes_regulieres=(Coefficient_prendre*Float.valueOf(pipe_length.getText())*(Math.pow(reynolds,2)))/(2*Diamètre_en_m*9.81);
        System.out.println("pertes_regulieres"+pertes_regulieres);
        System.out.println("pertes_singulieres"+pertes_singulieres);*/
        int HMT= (int) (Float.valueOf(pumping_height.getText())+pertes_regulieres()+pertes_singulieres());
        return HMT;
    }

    public int nombreTotal(){
        int nombreTotal=(int)((Integer.valueOf(pump_pwer.getText())*1000)/(Integer.valueOf(peak_power.getText())*0.9*Integer.valueOf(efficiency.getText())*0.01*(1-0.01*Integer.valueOf(perte.getText()))));
    return nombreTotal;
    }
    public int totalSerie(){
        int totalSerie=nombreTotal()/Integer.valueOf(serie.getText());
        return totalSerie;
    }


    //@FXML public void validateAction(){ System.out.println(HMT()); }


    @FXML public void validateAction() {

        try {

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Report of a solar pumping system.pdf"));
            document.open();
            addMetaData(document);
            addTitlePage(document);
            //addContent(document);
            document.close();
            System.out.println("ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private  void addMetaData(Document document) {
        document.addTitle("Report of a solar pumping system");
        //document.addSubject("Using iText");
        //document.addKeywords("Java, PDF, iText");
        document.addAuthor("Benghdaif Assia");
        document.addCreator("Benghdaif Assia");
        //document.
    }

    private void addTitlePage(Document document)
            throws DocumentException, IOException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        //addEmptyLine(preface, 1);
        com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance("src/sample/Images/logoaTH.png");
        image.scaleToFit(129,77);
        document.add(image);
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("                     Report of a solar pumping system", catFont));
        addEmptyLine(preface, 3);
        preface.add(new Paragraph(
                "This document presents the values entered and the results of the calculations ",
                smallBold));
        document.add(preface);
        image = com.itextpdf.text.Image.getInstance("src/sample/Images/48ee1e8a0a8f50dce4f8cb9ab418e211_XL.jpg");
        image.scaleToFit(500,448);
        document.add(image);
        addEmptyLine(preface, 4);
        Paragraph assia=new Paragraph(
                "" ,
                smallBold);
        addEmptyLine(assia, 4);
        document.add(assia);
        assia=new Paragraph(
                "Author :\n" +
                        "   BOUALOUL Amina\n" +
                        "   BENGHDAIF Assia\n",
                smallBold);
        document.add(assia);
        // Start a new page
        //document.newPage();
        Anchor anchor = new Anchor("Choice of components", catFont);
        anchor.setName("Choice of components");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);
        Paragraph subPara = new Paragraph("Choice of pump", subFont);
        Section subCatPart = catPart.addSection(subPara);
        //subCatPart.add(new Paragraph("Hello"));
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);
        createTable(subCatPart);
        paragraph = new Paragraph("Results",smallBold);
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);
        createTableRes(subCatPart);
        subPara = new Paragraph("Photovoltaic fields", subFont);
        subCatPart = catPart.addSection(subPara);

        paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);
        // add a table
        createTablePV(subCatPart);
        paragraph = new Paragraph("Results",smallBold);
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);
        createTablePVRes(subCatPart);
        // now add all this to the document
        document.add(catPart);

    }

    private void addContent(Document document) throws DocumentException {
        Anchor anchor = new Anchor("Choice of components", catFont);
        anchor.setName("Choice of components");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Choice of pump", subFont);
        Section subCatPart = catPart.addSection(subPara);
        //subCatPart.add(new Paragraph("Hello"));
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);
        createTable(subCatPart);
        paragraph = new Paragraph("Results",smallBold);
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);
        createTableRes(subCatPart);
        subPara = new Paragraph("Photovoltaic fields", subFont);
        subCatPart = catPart.addSection(subPara);

        paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);

        // add a table
        createTablePV(subCatPart);

        paragraph = new Paragraph("Results",smallBold);
        addEmptyLine(paragraph, 1);
        subCatPart.add(paragraph);
        createTablePVRes(subCatPart);
        // now add all this to the document
        document.add(catPart);


    }

    private void createTable(Section subCatPart)
            throws BadElementException {
        PdfPTable table = new PdfPTable(2);

        PdfPCell c1 = new PdfPCell(new Phrase("DATA"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("VALUES"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        //table.setHeaderRows(1);

        table.addCell("Geographic location");
        table.addCell(Map.city);
        table.addCell("Daily flow required");
        table.addCell(daily.getText());
        table.addCell("Number of peak hours of sunshine");
        table.addCell(hours.getText());
        table.addCell("Pumping height");
        table.addCell(pumping_height.getText());
        table.addCell("Pipe diameter");
        table.addCell(pipe_diameter.getText());
        table.addCell("Type of pipes");
        table.addCell(combobox.getValue());
        table.addCell("Pipe length");
        table.addCell(pipe_length.getText());
        table.addCell("Number of elbows  90 °");
        table.addCell(code.getText());
        table.addCell("Number of valves");
        table.addCell(vane.getText());
        table.addCell("other sources of singular pressure drops: Number");
        table.addCell(perte_number.getText());
        table.addCell("other sources of singular pressure drops: ks");
        table.addCell(ks.getText());

        subCatPart.add(table);

    }
    private void createTableRes(Section subCatPart)
            throws BadElementException {
        PdfPTable table = new PdfPTable(2);

        PdfPCell c1 = new PdfPCell(new Phrase("DATA"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("VALUES"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        //table.setHeaderRows(1);

        table.addCell("Pipe diameter (m)");
        table.addCell(Diamètre_en_m()+"");
        table.addCell("Speed");
        table.addCell(vitesse()+"");
        table.addCell("Reynolds number");
        table.addCell(reynolds()+"");
        table.addCell("Flow regime");
        table.addCell(Régime_écoulement()+"");
        table.addCell("Driving rigidity");
        table.addCell(Rigosité_conduite()+"");
        table.addCell("Coefficient (blasius)");
        table.addCell(Coefficient_blasius()+"");
        table.addCell("Coefficient (Colebrook)");
        table.addCell(Coefficient_colebrook()+"");
        table.addCell("Error");
        table.addCell(erreur()+"");
        table.addCell("Coefficient (to be taken)");
        table.addCell(Coefficient_prendre()+"");
        table.addCell("Regular losses");
        table.addCell(pertes_regulieres()+"");
        table.addCell("Singular losses");
        table.addCell(pertes_singulieres()+"");
        table.addCell("Debit");
        table.addCell(debit()+"");
        table.addCell("HMT");
        table.addCell(HMT()+"");

        subCatPart.add(table);

    }
    private void createTablePV(Section subCatPart)
            throws BadElementException {
        PdfPTable table = new PdfPTable(2);

        PdfPCell c1 = new PdfPCell(new Phrase("DATA"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("VALUES"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        //table.setHeaderRows(1);

        table.addCell("The pump power");
        table.addCell(pump_pwer.getText());
        table.addCell("Voltage Ump");
        table.addCell(voltage_ump.getText());
        table.addCell("Current ISC");
        table.addCell(current_isc.getText());
        table.addCell("Peak power");
        table.addCell(peak_power.getText());
        table.addCell("Max voltage in");
        table.addCell(max_v_in.getText());
        table.addCell("Min voltage in");
        table.addCell(min_v_in.getText());
        table.addCell("Max current in");
        table.addCell(max_c_in.getText());
        table.addCell("Efficiency");
        table.addCell(efficiency.getText());
        table.addCell("Losses");
        table.addCell(perte.getText());
        table.addCell("Number of modules in series");
        table.addCell(serie.getText());

        subCatPart.add(table);

    }

    private void createTablePVRes(Section subCatPart)
            throws BadElementException {
        PdfPTable table = new PdfPTable(2);

        PdfPCell c1 = new PdfPCell(new Phrase("DATA"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("VALUES"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        //table.setHeaderRows(1);

        table.addCell("Total number of pv panels to install");
        table.addCell(nombreTotal()+"");
        table.addCell("Number of channels in parallel");
        table.addCell(totalSerie()+"");

        subCatPart.add(table);

    }
    private void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}