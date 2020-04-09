
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moverbola;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;


/**
 *
 * @author maresgon
 */

public class FXMLMBController implements Initializable {
    
    private Label label;
    @FXML
    private Circle bola;
    
    private int bola_X = 2;
    private int bola_Y = 2;
    private static final int SIZE = 5;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bola.requestFocus();
    }    

    @FXML
    private void moverBola(KeyEvent event) {
        KeyCode tecla = event.getCode();
        switch (tecla) {
            case UP:
                bola_Y= (bola_Y - 1 + SIZE) %SIZE;
                GridPane.setRowIndex(bola, bola_Y);
                break;
            case DOWN:
                bola_Y= (bola_Y + 1 + SIZE) %SIZE;
                GridPane.setRowIndex(bola, bola_Y);
                break;
            case RIGHT:
                bola_X= (bola_X - 1 + SIZE) %SIZE;
                GridPane.setColumnIndex(bola, bola_X);
                break;
            case LEFT:
                bola_X= (bola_X + 1 + SIZE) %SIZE;
                GridPane.setColumnIndex(bola, bola_X);
                break;
        }
        
        event.consume();
    }
    
}
