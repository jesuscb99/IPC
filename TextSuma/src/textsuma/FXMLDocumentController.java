/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textsuma;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 *
 * @author 2jesu
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    private TextField valorVariable;
    
    IntegerProperty valor;
    
    @FXML
    private Label textValor;
    @FXML
    private HBox boxSumar;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        valor = new SimpleIntegerProperty(0);
        textValor.textProperty().bind(valor.asString());
    
    }
    @FXML
    private void sumar1(ActionEvent event) {
        int act = valor.get();
        valor.set(act + 1);
    }

    @FXML
    private void sumar5(ActionEvent event) {
         int act = valor.get();
        valor.set(act + 5);
    }

    @FXML
    private void sumar20(ActionEvent event) {
         int act = valor.get();
        valor.set(act + 20);
    }

    @FXML
    private void checkRestar(ActionEvent event) {
        Object box_ = event.getSource();
        
        if(box_ instanceof CheckBox) {
            CheckBox box = (CheckBox) box_;
            
            if(box.isSelected()) {
                boxSumar.setVisible(false);
            } else {
                boxSumar.setVisible(true);
            }
        }
    }

    @FXML
    private void sumarVariable(ActionEvent event) {
        int numero = Integer.parseInt(valorVariable.getText());
        
        int act = valor.get();
        valor.set(act + numero);
    }
    
}
