/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unafinestra;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Stage;
/**
 *
 * @author 2jesu
 */
public class FXMLFinestraController implements Initializable {
    
  

  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
  
    
    @FXML
    private void obrir(ActionEvent event) {
        
       /* FXMLLoader miCargador = new FXMLLoader(getClass().getResource("FXMLNova.fxml"));
        Stage stageActual = (Stage) (((Node) (event.getSource())).getScene().getWindow());
        Parent root = null;
        try {
             root = FXMLLoader.load(getClass().getResource("FXMLNova.fxml"));
             
        } catch(IOException e) {System.out.print(e.getMessage());}
            FXMLNovaController controlador = miCargador.<FXMLNovaController>getController();
          
          
            controlador.init(null, null);
            
            Scene scene = new Scene(root, 400, 400);
           
            stageActual.setScene(scene);
            stageActual.setTitle("Ver datos persona");
            stageActual.initModality(Modality.APPLICATION_MODAL);
            
            stageActual.showAndWait();     */  
        
        Stage stage = (Stage) (((Node) (event.getSource())).getScene().getWindow());
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FXMLNova.fxml"));
        }catch(Exception e){System.err.println(e.getMessage());}
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
}
