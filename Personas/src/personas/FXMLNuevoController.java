/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vista.Persona;

/**
 * FXML Controller class
 *
 * @author 2jesu
 */
public class FXMLNuevoController implements Initializable {

    @FXML
    private TextField nombre;
    @FXML
    private TextField apellidos;
    @FXML
    private Button salvar;
    @FXML
    private Button cancelar;
    
    private Persona persona;
    private FXMLPrincipalController main;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        salvar.disableProperty().bind(Bindings.or(nombre.textProperty().isEmpty(), apellidos.textProperty().isEmpty()));
    }    
    
    public void initP(Persona p, FXMLPrincipalController f) {
        main = f;
        persona = p;
        nombre.setText(p.getNombre());
        apellidos.setText(p.getApellidos());
    }

    @FXML
    private void salvar(ActionEvent event) {
        persona.setNombre(nombre.getText());
        persona.setApellidos(apellidos.getText());
        
        main.setGuardado(true);
        Stage stage = (Stage) salvar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancelar(ActionEvent event) {
        main.setGuardado(false);
         Stage stage = (Stage) salvar.getScene().getWindow();
         stage.close();
    }
    
    
            
}
