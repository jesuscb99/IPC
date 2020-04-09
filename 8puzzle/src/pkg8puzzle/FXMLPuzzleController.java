/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8puzzle;

import static java.lang.Math.abs;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import static utiles8puzzle.Utils.generarVectorAleatorio;
/**
 *
 * @author 2jesu
 */


public class FXMLPuzzleController implements Initializable {
    
    private Label label;
    
    private Button[] botons;
    @FXML
    private GridPane grid;
    @FXML
    private Button boton1;
    @FXML
    private Button boton2;
    @FXML
    private Button boton3;
    @FXML
    private Button boton4;
    @FXML
    private Button boton5;
    @FXML
    private Button boton6;
    @FXML
    private Button boton7;

    @FXML
    private Button boton0;
    
    Button activo;
    
    int posAct[];
    
    double[] iniPos;
    double[] finPos;
    
    private int[] fichas;
    @FXML
    private Label etiqueta;
    
    private void handleButtonAction(ActionEvent event) {
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       inicializarBotones();
       inicializarCasillas();
        
    }    

    private void inicializarBotones() {
        
        
         botons = new Button[8];
         fichas = new int[9];
         
         botons[0] = boton0;
         botons[1] = boton1;
         botons[2] = boton2;
         botons[3] = boton3;
         botons[4] = boton4;   
         botons[5] = boton5; 
         botons[6] = boton6; 
         botons[7] = boton7; 
        
       
         int[] num =   generarVectorAleatorio(8);
         
         for(int i = 0; i < botons.length; i++) {
             String s = Integer.toString(num[i]);
             fichas[i] = num[i];
             botons[i].setText(s);
             botons[i].requestFocus();
         }
         
         fichas[8] = -1;
         iniPos = new double[2];
         finPos = new double[2];
         
         posAct = new int[2];
         posAct[0] = 2;
         posAct[1] = 2;
    } 

     private void inicializarCasillas() {
         int k = 0;
         for(int i = 0; i < 3; i++) {
             for(int j = 0; j < 3; j++) {
                 if(k < 8) {
                     grid.setRowIndex(botons[k], i);
                     grid.setColumnIndex(botons[k], j);
                 }
                 k++;
             }
         }
        
         
         
     }

    @FXML
    private void moviendo(MouseEvent event) {
        /*if(activo != null) {
            //activo.setLayoutX(event.getSceneX());
            activo.setText(Double.toString(event.getSceneX()));
        }*/
        
        
    }

    @FXML
    private void moverFicha(MouseEvent event) {
       /* Object source = event.getSource();
        
        if(source instanceof Button) {
            activo = (Button) source;
            activo.setText("hola");
           
            
        }*/
        
    }
    private void desplazarFicha() {
        double deltaX = finPos[0] - iniPos[0];
        double deltaY = finPos[1] - iniPos[1];
        
       // try {
            
            if(esAdyacente()) { 
              int posActIn = posAct[0] + 3*posAct[1];
              int posFichaIn = 3*grid.getRowIndex(activo) + grid.getColumnIndex(activo);
              
                if(abs(deltaX) > abs(deltaY)) {
                    if(grid.getRowIndex(activo) == posAct[1]) {
                        if(deltaX > 0) {                                 //RIGHT
                                if(grid.getColumnIndex(activo) < 2) {
                                    grid.setColumnIndex(activo, posAct[0]);
                                    posAct[0]--;
                                    
                                }
                                //activo.setText(Integer.toString(posAct[0]));
                        } else {                                        //LEFT
                               if(grid.getColumnIndex(activo) > 0) {
                                grid.setColumnIndex(activo, posAct[0]);
                                posAct[0]++;
                               
                               //activo.setText("debugging");
                               //activo.setText(Integer.toString(posAct[0]));
                               }
                        }
                        cambiarFichas(posActIn, posFichaIn);
                    }
                } else if(abs(deltaY) > abs(deltaX)) {
                    if(grid.getColumnIndex(activo) == posAct[0]) {
                        if(deltaY > 0) {                                //DOWN
                              if(grid.getRowIndex(activo) < 2) {
                                  grid.setRowIndex(activo, posAct[1]);
                                  posAct[1]--;
                              }            
                        } else {                                        //UP
                            if(grid.getRowIndex(activo) > 0) {
                                  grid.setRowIndex(activo, posAct[1]);
                                  posAct[1]++;
                            }
                        }
                        cambiarFichas(posActIn, posFichaIn);
                    }
                }
            }
            comprobarVictoria();
        //} catch(NullPointerException e) {
            
        //}
    }
    
    
    private boolean esAdyacente(){
        int fila = posAct[1];
        int columna = posAct[0];
        
       
        if(activo == null) {return false; }
        
        
           // activo.setText("1");
        
       int difFila = Math.abs(fila - grid.getRowIndex(activo));
        
       int difCol = Math.abs(columna - grid.getColumnIndex(activo));
               
       // activo.setText("2");
        String s = Integer.toString(difCol);
       
        
        if(difCol > 1 || difFila > 1) {
            return false;
        } else {
            if(difCol == 1 && difFila == 1) {
                return false;
            }
        }
        
        return true;
        
       
    }
    
    @FXML
    private void setFinal(MouseEvent event) {
        finPos[0] = event.getX();
        finPos[1] = event.getY();
        
        
        desplazarFicha();
    }

    @FXML
    private void setInicio(MouseEvent event) {
        Object source = event.getSource();
        
        if(source instanceof Button) {
            activo = (Button) source;
   

            
        }
        iniPos[0] = event.getX();
        iniPos[1] = event.getY();
    }

    @FXML
    private void reset(ActionEvent event) {
        inicializarBotones();
        inicializarCasillas();
        etiqueta.setText("Jugando");
    }
    
    private boolean comprobarVictoria() {
       boolean res = true;
       
       for(int i = 0; i < 7; i++) {
           res = res && (fichas[i] < fichas[i+1]);
       }
       if(res) {
           etiqueta.setText("Victoria");
       }
        return res;
    }
    
    private void cambiarFichas(int pos1, int pos2) {
        int aux = fichas[pos2];
        fichas[pos2] = fichas[pos1];
        fichas[pos1] = aux;
        
        String s = "";
      
    }
    
}
