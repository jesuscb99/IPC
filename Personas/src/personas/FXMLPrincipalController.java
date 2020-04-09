/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personas;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vista.Persona;

/**
 *
 * @author 2jesu
 */
public class FXMLPrincipalController implements Initializable {
    

    @FXML
    private ListView<Persona> listView;
  
    @FXML
    private Button add;
    @FXML
    private Button remove;
    @FXML
    private Button modify;
    
    private ObservableList<Persona> datos;
    private ListCell<Persona> listCell;
    private boolean guardado = true;
    private String[] nuevosDatos;
   
    
   class PersonaListCell extends ListCell<Persona>
   {
       @Override
       protected void updateItem(Persona item, boolean empty) {
           super.updateItem(item, empty);
           if(item == null || empty) {
               setText(null);
           } else {
               setText(item.getNombre() + ", " + item.getApellidos());
           }
       }
   }
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
       List<Persona> misdatos = new ArrayList<>();
      
       datos = FXCollections.observableArrayList(misdatos);
       listView.setItems(datos);
       
         
       listView.setCellFactory(c -> new PersonaListCell());
   
       remove.disableProperty().bind(Bindings.equal(-1, listView.getSelectionModel().selectedIndexProperty()));
       modify.disableProperty().bind(Bindings.equal(-1, listView.getSelectionModel().selectedIndexProperty()));
    }    

    @FXML
    private void add(ActionEvent event) {
        Persona p = new Persona("","");
        abrirVentana(p);
        if(guardado) {
          
            datos.add(p);
        }
        
        listView.refresh();
    }
    
    private void abrirVentana(Persona p) {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("FXMLNuevo.fxml"));
        Scene sceneActual = add.getScene();
        
        
        try {
            AnchorPane root = (AnchorPane) miCargador.load();
            FXMLNuevoController controlador = miCargador.<FXMLNuevoController>getController();
          
          
            controlador.initP(p, this);
            
            Scene scene = new Scene(root, 400, 400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Ver datos persona");
            stage.initModality(Modality.APPLICATION_MODAL);
            
            stage.showAndWait();       
        } catch(IOException e) {System.out.print("eeeeeeeeeeeeeeee");}
        
    }
    @FXML
    private void remove(ActionEvent event) {
        Persona p = listView.getSelectionModel().getSelectedItem();
        datos.remove(p);
        listView.getSelectionModel().clearSelection();
    }

    @FXML
    private void modify(ActionEvent event) {
        Persona p = listView.getSelectionModel().getSelectedItem();
            
        abrirVentana(p);
        
        listView.refresh();
      
       
    }
    
    public void setGuardado(boolean b) {
        guardado = b;
      
    }
}
