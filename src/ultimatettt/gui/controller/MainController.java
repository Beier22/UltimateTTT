/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatettt.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author mads_
 */



public class MainController {
    int distinguisher = 0;
    private Label label;
    @FXML
    private Label mainLabel;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    public void setDistinguisher(int i){
        this.distinguisher=i;
        setLabel();
    }
    
    private void setLabel(){
        if(distinguisher==1){
                mainLabel.setText("Human vs Human");
            }
            else if(distinguisher==2){
                mainLabel.setText("Human vs Bot");
            }
            else{
                mainLabel.setText("Human vs Bot");
            }
        }    
    }
    

