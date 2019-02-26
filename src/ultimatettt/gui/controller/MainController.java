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
import ultimatettt.bll.Bot;
import ultimatettt.bll.GameManager;
import ultimatettt.bll.GameState;
import ultimatettt.bll.IBot;
import ultimatettt.bll.IGameState;

/**
 *
 * @author mads_
 */



public class MainController {
    int distinguisher = 0;
    private Label label;
    private final IGameState gameState;
    GameManager manager;
    @FXML
    private Label mainLabel;
    
    public MainController(){
        this.gameState = new GameState();
    }
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    public void setGameUp(int i){
        setDistinguisher(i);
        setLabel();
        createManager();
    }
    public void setDistinguisher(int i){
        this.distinguisher=i;
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
    private void createManager(){
        
        if(distinguisher==1){
            manager = new GameManager(gameState);
        }
        else if(distinguisher==2){
            IBot bot1 = new Bot();
            manager = new GameManager(gameState, bot1);
        }
        else{
            IBot bot1 = new Bot();
            IBot bot2 = new Bot();
            manager = new GameManager(gameState, bot1, bot2);
        }
   }
    
}
    

