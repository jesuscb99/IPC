/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableviewex;

import com.sun.javafx.binding.SelectBinding;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableFloatValue;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableNumberValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import modelo.Persona;
import vista.FXMLVistaController;
import modelo.Persona.Residence;
/**
 *
 * @author 2jesu
 */
public class FXMLDocumentController implements Initializable {
    

    @FXML
    private Button botonAdd;
    @FXML
    private Button botonMod;
    @FXML
    private Button botonRem;
    @FXML
    private TableView<Persona> tableView;
    @FXML
    private TableColumn<Persona, Integer> columna1;
    @FXML
    private TableColumn<Persona, String> columna2;
    
    private ObservableList<Persona> mydata;
    private boolean guardado;
    @FXML
    private TableColumn<Persona, Residence> columna3;
    @FXML
    private TableColumn<Persona, String> columna4;
    
    @Override  
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        botonRem.disableProperty().bind(Bindings.equal(-1, tableView.getSelectionModel().selectedIndexProperty()));
        botonMod.disableProperty().bind(Bindings.equal(-1, tableView.getSelectionModel().selectedIndexProperty()));
        
        List<Persona> data = new ArrayList<>();
        mydata = FXCollections.observableArrayList(data);
        
       
        columna1.setCellValueFactory(
                new PropertyValueFactory<Persona, Integer>("id")
        );
        
        columna2.setCellValueFactory(
                new PropertyValueFactory<Persona, String>("nombreCompleto")
        );
        
        columna3.setCellValueFactory(cellData3 -> cellData3.getValue().residenceProperty());
        columna3.setCellFactory(v -> {
                return new TableCell<Persona, Residence>(){
                    @Override
                    public void updateItem(Residence item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item == null || empty) {
                            setText(null);
                        } else {
                            setText("-->" + item.getCiudad());
                        }
                    }
                };
                
        });
                
        columna4.setCellValueFactory(cellData4 -> cellData4.getValue().pathImageProperty());
        columna4.setCellFactory(v -> {
                return new TableCell<Persona, String>(){
                    ImageView view = new ImageView();
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item == null || empty) {
                            setGraphic(null);
                        } else {
                            //view.setImage(new Image(MainWindowController.class.getResourceAsStream(item), 40, 40, true, true));
                            System.out.println(item);
                            File f = new File(item);
                            javafx.scene.image.Image image = new javafx.scene.image.Image(FXMLDocumentController.class.getResourceAsStream(item), 40, 40, true, true);
                            
                            view.setImage(image);
                            setGraphic(view);
                        }
                    }
                };
                
        });
                
        
        
         tableView.setItems(mydata);
         columna1.textProperty().set("Dni");
         columna2.textProperty().set("Nombre y Apellidos");
         columna3.textProperty().set("Ciudad");
         columna4.textProperty().set("Imagen");
                
    }    

    @FXML
    private void agregar(ActionEvent event) {
        Persona p = new Persona(-1, "", "", "", "");
        abrirVentana(p, 0);
        if(guardado) {
            mydata.add(p);
        }
        tableView.refresh();
    }

    @FXML
    private void modificar(ActionEvent event) {
        Persona p = tableView.getSelectionModel().getSelectedItem();
        abrirVentana(p, 1);
        if(guardado) {
            tableView.refresh();
        }
    }

    @FXML
    private void borrar(ActionEvent event) {
        Persona p = tableView.getSelectionModel().getSelectedItem();
        mydata.remove(p);
        tableView.refresh();
    }
    
    private void abrirVentana(Persona p, int action) {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/vista/FXMLVista.fxml"));
        
        try {
            AnchorPane root = (AnchorPane) miCargador.load();
            FXMLVistaController controlador = miCargador.<FXMLVistaController>getController();
          
          //aaaaaaa
            controlador.initP(p, this, action);
            
            Scene scene = new Scene(root, 600, 400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Ver datos persona");
            stage.initModality(Modality.APPLICATION_MODAL);
            
            stage.showAndWait();       
        } catch(IOException e) {System.out.print("eeeeeeeeeeeeeeee");}
    }
    
    public void setGuardado(boolean b) {
        guardado = b;
    }
}
