/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatettt.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
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
import ultimatettt.bll.IMove;
import ultimatettt.bll.Move;

/**
 *
 * @author mads_
 */



public class MainController {
    int distinguisher = 0;
    boolean isX = false;
    private Label label;
    private final IGameState gameState;
    GameManager manager;
    @FXML
    private Label mainLabel;
    @FXML
    private GridPane macroBoard;
    
    public MainController(){
        this.gameState = new GameState();
    }
    
    public void setGameUp(int i){
        setDistinguisher(i);
        setLabel();
        createManager();
        String[][] mB = manager.getGameState().getField().getMacroboard();
        setMacroBoardBorders(mB);
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

    
    
    @FXML
    private void handleBtnAction(ActionEvent event) {
        Button btn = (Button) event.getSource();
        
        IMove move = createMove(btn);


        if(manager.updateGame(move)){
            
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
        };
        
        String[][] mB = manager.getGameState().getField().getMacroboard();
        setMacroBoardBorders(mB);
 
        
    }
    
   private IMove createMove(Button btn){
        int col;
        int row;
        Integer int1 = GridPane.getColumnIndex(btn);
        Integer int2 = GridPane.getRowIndex(btn);
        if(int1!=null)
            col = int1.intValue();
        else
            col = 0;
        if(int2!=null)
            row = int2.intValue();
        else
            row = 0;
         return new Move(col, row);
    }
   
   public void setMacroBoardBorders(String[][] matrix){
       for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
               Node node = null;
               node = getNodeByRowColumnIndex(i, j);
               //StackPane pane = (StackPane) node;
               
               if(matrix[i][j].equals("-1")){
                node.setStyle("-fx-border-color: red;");}
               else
                node.setStyle("-fx-border-color: white;");
           }
       }
   }
   public Node getNodeByRowColumnIndex (final int row, final int column) {
    Node result = null;
    ObservableList<Node> childrens = macroBoard.getChildren();

    for (Node node : childrens) {
        int c;
        int r;
        Integer int1 = GridPane.getColumnIndex(node);
        Integer int2 = GridPane.getRowIndex(node);
        if(int1!=null)
            c = int1;
        else
            c = 0;
        if(int2!=null)
            r = int2;
        else
            r = 0;
        if(r == row && c == column) {
            result = node;
            break;
        }
    }

    return result;
}
}

