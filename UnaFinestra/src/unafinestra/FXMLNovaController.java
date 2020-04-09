/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unafinestra;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 2jesu
 */
public class FXMLNovaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Scene sceneAnterior;
    Stage stageActual;
    
    public void init(Stage stage,Scene scene) {
        sceneAnterior = scene;
        stageActual = stage;
    }
    
    @FXML
    private void tancar(ActionEvent event) {
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
