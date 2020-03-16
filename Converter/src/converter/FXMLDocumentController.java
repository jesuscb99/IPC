/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

/**
 *
 * @author 2jesu
 */
public class FXMLDocumentController implements Initializable {
    
  
    @FXML
    private Slider slider;
    @FXML
    private TextField input;
    @FXML
    private TextField output;
    @FXML
    private Button convertBot;
    @FXML
    private CheckBox checkBox;
    @FXML
    private Label rate;
        
    private DoubleProperty out;
    private ChangeListener listener;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
   
        listener = new ChangeListener<Double>() {    
            @Override
            public void changed(ObservableValue<? extends Double> observable, Double oldValue, Double newValue) {
               Double in = Double.parseDouble(input.getText());
               Double outValue = in * newValue;
               out.setValue(outValue);
            }
        };
        out = new SimpleDoubleProperty();
        out.setValue(0.00);
        
        output.textProperty().bind(Bindings.format("%.2f", out));
        rate.textProperty().bind(Bindings.format("%.2f",slider.valueProperty()));
        
    }    

    @FXML
    private void convert(ActionEvent event) {
        if(!checkBox.isSelected()) {
            if(input.getText().length() > 0) {
                double in = Double.parseDouble(input.getText());
                double outValue = in * slider.getValue();

               out.setValue(outValue);

            }
        }
    }

    @FXML
    private void clear(ActionEvent event) {
        slider.setValue(0.00);
        input.setText("0.00");
        
        out.setValue(0.00);
    }

    @FXML
    private void autConvert(ActionEvent event) {
        if(checkBox.isSelected()) {
            convertBot.opacityProperty().setValue(0.2);
          
           slider.valueProperty().addListener(listener);
            double in = Double.parseDouble(input.getText());
            double outValue = in * slider.getValue();

            out.setValue(outValue);
           
            
        } else {
             convertBot.opacityProperty().setValue(1);
             slider.valueProperty().removeListener(listener);
             
        }
        
        
    }



    


    
}
