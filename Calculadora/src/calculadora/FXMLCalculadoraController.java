/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

/**
 *
 * @author 2jesu
 */
public class FXMLCalculadoraController implements Initializable {

    private final int SUMAR = 0;
    private final int RESTAR = 1;
    private final int MULT = 2;
    private final int DIV = 3;
    
    //private int operacioPrevia = -1;
    private String numActualS;
    private Double numActualD;
    
    private double resultat;
    
    private boolean iniciat = false;
    
    private StringProperty pantalla;
    @FXML
    private Label textPantalla;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       numActualS = "";
      
       
       resultat = 0;
       
       pantalla = new SimpleStringProperty();
       pantalla.setValue("");
       textPantalla.textProperty().bind(pantalla);
    }    

    @FXML
    private void funcio(MouseEvent event) {
        Button numeroBoto = (Button) event.getSource();
   
        
        switch(numeroBoto.getText()) {
            case "AC" :
                 pantalla.setValue("");
                 iniciat = false;
                 numActualS = "#";
                 //operacioPrevia = -1;
                break;
            case "%" :
                pantalla.setValue("Error");
                break;
            case "±" :
                 pantalla.setValue("Error");
                break;
            default:
        }
    }

    
   
    @FXML
    private void operar(MouseEvent event) {
        Button numeroBoto = (Button) event.getSource();
      
       if(numActualS.equals("reset")) {
           
           numActualS = Double.toString(resultat);
       }
       
   
        if(numActualS.length() <= 0) {
            return;
        }
        
        numActualS = "";
     
        switch(numeroBoto.getText()) {
            
            case "-" :
                pantalla.setValue(pantalla.getValue() + "-");
                
                //operacioPrevia = RESTAR;
                break;
            case "+" :
                pantalla.setValue(pantalla.getValue() + "+");
               // operacioPrevia = SUMAR;
                break;
            case "x" :
                pantalla.setValue(pantalla.getValue() + "x");
              //  operacioPrevia = MULT;
                break;
            case "÷" :
                pantalla.setValue(pantalla.getValue() + "÷");
               // operacioPrevia = DIV;
                break;
            case "=" :
                //operacioPrevia = SUMAR;
                pantalla.setValue(Double.toString(resultat));
                break;
        }
        
  
        
    }

    @FXML
    private void nombre(MouseEvent event) {
        
        Button numeroBoto = (Button) event.getSource();
        
        if(numActualS.equals("reset")) {
            if(!numeroBoto.getText().equals(".")) {
                pantalla.setValue("");
                numActualS = "";
            } else {
                return;
            }
            
        }
    
        
        switch(numeroBoto.getText()) {
            case "0" :
                pantalla.setValue(pantalla.getValue() + "0");
                break;
            case "1" :
                pantalla.setValue(pantalla.getValue() + "1");
                break;
            case "2" :
                pantalla.setValue(pantalla.getValue() + "2");
                break;
            case "3" :
                pantalla.setValue(pantalla.getValue() + "3");
                break;
            case "4" :
                pantalla.setValue(pantalla.getValue() + "4");
                break;
             case "5" :
                pantalla.setValue(pantalla.getValue() + "5");
                break;
             case "6" :
                pantalla.setValue(pantalla.getValue() + "6");
                break;
             case "7" :
                pantalla.setValue(pantalla.getValue() + "7");
                break;
             case "8" :
                pantalla.setValue(pantalla.getValue() + "8");
                break;
             case "9" :
                pantalla.setValue(pantalla.getValue() + "9");
                break;
             case "." :
              
                if(numActualS.length() > 0 && !numActualS.contains(".")) {
                    pantalla.setValue(pantalla.getValue() + ".");
                } else {
                    return;
                }
                break;
        }
        
        numActualS += numeroBoto.getText();
        
        
    }

    private double calcular(int i, String s) {
        boolean trobat = false;
        String ops = "-+x÷";
        int ini = i;
        
        while(i < s.length() && !ops.contains(String.valueOf(s.charAt(i)))) {
            i++;
        }
        
        String numS = s.substring(ini, i);
        Double num = Double.parseDouble(numS);
        
        if(i == s.length()) {
            return num;
        } else {
            switch(s.charAt(i)) {
                case '+':
                    return num + calcular(i+1, s);
                 
                case '-':
                    return num - calcular(i+1, s);
                   
                case 'x':
                    return num * calcular(i+1, s);
                 
                case '÷':
                    return num / calcular(i+1, s);
                default:
                    return -1;
            }
        }
    }
    
    @FXML
    private void resultat(ActionEvent event) {
       
            
        String op = pantalla.getValue();
        resultat = calcular(0, op);
        if(Double.toString(resultat).length() > 9) {
            pantalla.setValue("Stack Error");
            return;
        }
       
        int resultatInt = (int) resultat;
        double partEntera = resultatInt * 1.0;
        
        
        if(resultat % partEntera == 0) {
           
            String res = Integer.toString(resultatInt);
            pantalla.setValue(res);
        } else {
            pantalla.setValue(Double.toString(resultat));
        }
        
        iniciat = false;
        numActualS = "reset";
       
    }



    
}
