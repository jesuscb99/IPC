/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Persona;
import tableviewex.FXMLDocumentController;


/**
 * FXML Controller class
 *
 * @author 2jesu
 */
public class FXMLVistaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Persona persona;
    private FXMLDocumentController main;
    @FXML
    private TextField nameLabel;
    @FXML
    private TextField lastLabel;
    @FXML
    private Button buttonSave;
    @FXML
    private Label title;
    @FXML
    private CheckBox check1;
    @FXML
    private CheckBox check2;
    @FXML
    private CheckBox check3;
    @FXML
    private TextField cityLabel;
    @FXML
    private TextField provLabel;
    
    private IntegerProperty checkbox;
    @FXML
    private TextField idLabel;
    @FXML
    private Label error;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // buttonSave.disableProperty().bind(Bindings.or(nameLabel.textProperty().isEmpty(), lastLabel.textProperty().isEmpty()));
       checkbox = new SimpleIntegerProperty(-1);
       CheckBox[] checkArray = {check1, check2, check3};
       
       checkbox.addListener(
               (object, oldV, newV) -> {
                   if(oldV.intValue() < 0) {
                       if(newV.intValue() > 0) {
                          checkArray[newV.intValue()].selectedProperty().set(true);
                       }
                   } else {
                       checkArray[oldV.intValue()].selectedProperty().set(false);
                       if(newV.intValue() > 0) {
                         checkArray[newV.intValue()].selectedProperty().set(true);
                       }
                   }
               }
       );
       
       buttonSave.disableProperty().bind(
               Bindings.or(idLabel.textProperty().isEmpty()
               ,(Bindings.or(Bindings.greaterThan(0, checkbox)
               ,Bindings.or(Bindings.or(cityLabel.textProperty().isEmpty(), provLabel.textProperty().isEmpty()),
               (Bindings.or(nameLabel.textProperty().isEmpty(), lastLabel.textProperty().isEmpty())))))));
    }    
    
    
    public void initP(Persona p, FXMLDocumentController c, int action) {
        persona = p;
        main = c;
        
        String full = p.getNombre();
        
        if(full.length() > 0) {
            int i = 0;
            while(full.charAt(i) != ',' && i < full.length()) {
             i++;   
            }
            String nombre = full.substring(0, i);
            String apellidos = full.substring(i + 2, full.length());
            
            nameLabel.setText(nombre);
            lastLabel.setText(apellidos);      
        }
        
         cityLabel.setText(p.getResidence().getCiudad());
         provLabel.setText(p.getResidence().getProvincia());
         
         if(p.getID() < 0) {
             idLabel.setText("");
         } else {
             idLabel.setText(Integer.toString(p.getID()));
         }
         
         
         String img = p.getPath();
         if(img.length() > 0) {
             if(img.contains("LLoroso")) {
                 checkbox.setValue(0);
                 check1.selectedProperty().setValue(true);
             } else if(img.contains("Pregunta")) {
                 checkbox.setValue(1);
                 check2.selectedProperty().set(true);
             } else if(img.contains("Sonriente")) {
                 checkbox.setValue(2);
                 check3.selectedProperty().setValue(true);
             }
         } else {
             checkbox.setValue(-1);
         }
        
        switch(action) {
            case 0:
                title.setText("Añadir nueva persona");
                break;
            case 1:
                title.setText("Modificar persona");
                break;
            default:
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        main.setGuardado(false);
         Stage stage = (Stage) title.getScene().getWindow();
         stage.close();
    }

    @FXML
    private void salvar(ActionEvent event) {
         main.setGuardado(true);
         String full = nameLabel.getText() + ", "+ lastLabel.getText();
        
        persona.setNombre(full);
        try {
             persona.setId(Integer.parseInt(idLabel.getText()));
        } catch(NumberFormatException e) {
            error.visibleProperty().setValue(true);
            main.setGuardado(false);
            return;
        } 
        error.visibleProperty().setValue(false);
        persona.getResidence().setCiudad(cityLabel.getText());
        persona.getResidence().setProvincia(provLabel.getText());
        
        String path = "/iconos/";
        
        switch(checkbox.intValue()) {
            case 0:
                path += "LLoroso.png";
                break;
            case 1:
                path += "Pregunta.png";
                break;
            case 2:
                path += "Sonriente.png";
                break;
            default:
        }
        
        persona.setPath(path);
        
         Stage stage = (Stage) title.getScene().getWindow();
         stage.close();
    }

    @FXML
    private void icono1(ActionEvent event) {
        
        if(check1.isSelected()) {
            
            checkbox.setValue(0);
        } else {
             checkbox.setValue(-1);
        }
    }

    @FXML
    private void icono2(ActionEvent event) {
          if(check2.isSelected()) {
           
            checkbox.setValue(1);
        } else {
             checkbox.setValue(-1);
        }
    }

    @FXML
    private void icono3(ActionEvent event) {
          if(check3.isSelected()) {
            ;
            checkbox.setValue(2);
        } else {
             checkbox.setValue(-1);
        }
    }
    
}
