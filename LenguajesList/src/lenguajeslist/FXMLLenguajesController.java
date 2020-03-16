/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lenguajeslist;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 *
 * @author 2jesu
 */
public class FXMLLenguajesController implements Initializable {
    

    @FXML
    private TextField newValue;
    @FXML
    private Label selectedItem;
    @FXML
    private ListView<String> listView;
    @FXML
    private Button add;
    @FXML
    private Button remove;
    
    
    private ObservableList<String> data = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       ArrayList<String>  mydata = new ArrayList<String>();
        
       mydata.add("Java"); mydata.add("JavaFX"); mydata.add("C++"); 
       mydata.add("Python"); mydata.add("Javascript"); mydata.add("C#");
        

       data = FXCollections.observableArrayList(mydata);

        listView.setItems(data);
        
        listView.getSelectionModel().selectedItemProperty().addListener(
                (object, old, nuevo) -> {
                  if(!(nuevo == null)) {
                    selectedItem.setText(nuevo);
                  } else  {
                      selectedItem.setText("none");
                  }
                }
        );
        
        add.disableProperty().bind(newValue.textProperty().isEmpty());
        remove.disableProperty().bind(Bindings.equal(-1, listView.getSelectionModel().selectedIndexProperty()));
       
    }    

    @FXML
    private void addToList(ActionEvent event) {
        data.add(newValue.getText());
        
    }

    @FXML
    private void removeFromList(ActionEvent event) {
        int index = listView.getSelectionModel().selectedIndexProperty().intValue();
        listView.getSelectionModel().clearSelection();
        
        
        data.remove(index);
    }
    
}
