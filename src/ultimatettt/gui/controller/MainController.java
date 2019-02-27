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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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

    private void getElement(MouseEvent event) {
        Node node1 = (Node) event.getSource();
        StackPane big = (StackPane) node1.getParent();
        Node node2 = (Node) event.getPickResult().getIntersectedNode();
        StackPane small = (StackPane) node2;
        String existingStyle = small.getStyle();
        small.setStyle(existingStyle+"-fx-background-color: red;");
        System.out.println("SmallColumn: "+GridPane.getColumnIndex(small));
        System.out.println("SmallRow: "+GridPane.getRowIndex(small));
        System.out.println("BigColumn: "+GridPane.getColumnIndex(big));
        System.out.println("BigRow: "+GridPane.getRowIndex(big));
    }

    
    boolean isX = true;
    
    @FXML
    private void handleBtnAction(ActionEvent event) {
        Button btn = (Button) event.getSource();
        if(isX)
        {
            btn.getStyleClass().add("xbtn");
            btn.setText("X");
            isX = false;
        } else {
            btn.getStyleClass().add("ybtn");
            btn.setText("O");
            isX = true;
        }
    }
    
}
    

