/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagrams;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author 2jesu
 */
public class FXMLDiagramsController implements Initializable {
    
   
    @FXML
    private PieChart pieChart;
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private LineChart<String, Number> lineChart;
    
    //Valor de cada uno de los datos eje x
    private IntegerProperty suspendido;
    private IntegerProperty aprobado;
    private IntegerProperty notable;
    private IntegerProperty sobresaliente;
    private IntegerProperty mh;
    
    //0 --> Claro, 1 --> Oscuro
    private int viewMode;
    
    @FXML
    private Button barButton;
    @FXML
    private Button lineButton;
    @FXML
    private BorderPane borderPane;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        viewMode = 0;
        suspendido = new SimpleIntegerProperty(0);
        aprobado = new SimpleIntegerProperty(0);
        notable = new SimpleIntegerProperty(0);
        sobresaliente = new SimpleIntegerProperty(0);
        mh = new SimpleIntegerProperty(0);
        
        XYChart.Series<String, Number> serie = new XYChart.Series();
        barChart.getData().addAll(serie);
        
        XYChart.Series<String, Number> serieLine = new XYChart.Series();
        lineChart.getData().addAll(serieLine);
        
        iniciaCharts();
    }    

    private void iniciaPie(int i) {
        String[] categories = {"Suspendido", "Aprobado", "Notable", "Sobresaliente", "M.H."};
        IntegerProperty[] valors = {suspendido, aprobado, notable, sobresaliente, mh};
        
        ObservableList<PieChart.Data> listpie = pieChart.getData();
     
        PieChart.Data data = new PieChart.Data(categories[i], 0);
        listpie.add(data);
        data.pieValueProperty().bind(valors[i]);
        
      
        
        
    }
    private void iniciaCharts() {
       
       String[] categories = {"Suspendido", "Aprobado", "Notable", "Sobresaliente", "M.H."};
        IntegerProperty[] valors = {suspendido, aprobado, notable, sobresaliente, mh};
        /**
         * BARCHART
         */
        XYChart.Series<String, Number> serie = barChart.getData().get(0);
        ObservableList<Data<String, Number>> listbar = serie.getData();
        serie.setName("Alumnos con cada nota");
        for(int i = 0; i < 5; i++) {
            XYChart.Data<String, Number> databar = new XYChart.Data(categories[i], 0);

            listbar.add(databar);

            databar.YValueProperty().bind(valors[i]);
        }
        /**
         * LINECHART
         */
        
        XYChart.Series serieLine = lineChart.getData().get(0);
        ObservableList<Data<String, Number>> listline = serieLine.getData();
        serieLine.setName("Alumnos con cada nota");
        for(int i = 0; i < 5; i++) {
            XYChart.Data<String, Number> dataline = new XYChart.Data(categories[i], 0);
            listline.add(dataline);
            dataline.YValueProperty().bind(valors[i]);
        }
        
    }
    @FXML
    private void doBar(ActionEvent event) {
     barButton.disableProperty().setValue(Boolean.TRUE);
      lineButton.disableProperty().setValue(Boolean.FALSE);
      lineChart.visibleProperty().setValue(false);
      barChart.visibleProperty().setValue(true);
      
      
    }

    @FXML
    private void doLines(ActionEvent event) {
        barButton.disableProperty().setValue(Boolean.FALSE);
      lineButton.disableProperty().setValue(Boolean.TRUE);
      
      lineChart.visibleProperty().setValue(true);
      barChart.visibleProperty().setValue(false);
      
    }
    
    @FXML
    private void doView(ActionEvent event) {
        if(viewMode == 0) {
          
            borderPane.getStylesheets().set(0, ("/resources/styleDark.css"));
            viewMode = 1;
        } else  {
            borderPane.getStylesheets().set(0, ("/resources/styleLight.css"));
            viewMode = 0;
        }
    }

    @FXML
    private void doSumar(ActionEvent event) {
        addDataValue(((Button) event.getSource()).getText());
    }
    
    private void addDataValue(String categoria) {
       
       switch(categoria) {
           case "Suspendido":
              if(suspendido.getValue() == 0) {
                  iniciaPie(0);
              }
               suspendido.setValue(suspendido.getValue() + 1);
               break;
           case "Aprobado":
               if(aprobado.getValue() == 0) {
                  iniciaPie(1);
              }
               aprobado.setValue(aprobado.getValue() + 1);
               break;
           case "Notable":
               if(notable.getValue() == 0) {
                  iniciaPie(2);
              }
               notable.setValue(notable.getValue() + 1);
               break;
           case "Sobresaliente":
               if(sobresaliente.getValue() == 0) {
                  iniciaPie(3);
              }
               sobresaliente.setValue(sobresaliente.getValue() + 1);
               break;
           case "M.H.":
               if(mh.getValue() == 0) {
                  iniciaPie(4);
              }
               mh.setValue(mh.getValue() + 1);
               break;
       }
       
       
       
    }

   
    
}
