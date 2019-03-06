/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatettt.gui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import ultimatettt.bll.IField;
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
    @FXML
    private Label xScore;
    @FXML
    private Label oScore;
    @FXML
    private GridPane microBoard;

    public MainController() {
        this.gameState = new GameState();
    }

    public void setGameUp(int i) {
        setDistinguisher(i);
        setLabel();
        createManager();
        if(distinguisher==3)
           botVsBot();
        else{
        String[][] mB = manager.getGameState().getField().getMacroboard();
        setMacroBoardBorders(mB);}
    }

    public void setDistinguisher(int i) {
        this.distinguisher = i;
    }

    private void setLabel() {
        if (distinguisher == 1) {
            mainLabel.setText("Human vs Human");
        } else if (distinguisher == 2) {
            mainLabel.setText("Human vs Bot");
        } else {
            mainLabel.setText("Bot vs Bot");
        }
    }

    private void createManager() {

        if (distinguisher == 1) {
            manager = new GameManager(gameState);
        } else if (distinguisher == 2) {
            IBot bot1 = new Bot();
            manager = new GameManager(gameState, bot1);
        } else {
            IBot bot1 = new Bot();
            IBot bot2 = new Bot();
            manager = new GameManager(gameState, bot1, bot2);
        }
    }

    private void handleBtnAction(ActionEvent event){
        if(distinguisher != 3){
        Button btn = (Button) event.getSource();

        IMove move = createMove(btn);
        
        if (manager.updateGame(move)) {
               
            if(distinguisher==2){
                manager.updateGame();
            }
           }
        }
            String[][] mB = manager.getGameState().getField().getMacroboard();
            
            setMacroBoardBorders(mB);
            setBoard(manager.getGameState().getField().getBoard());
            setScore();
            }

    private IMove createMove(Button btn) {
        int col;
        int row;
        Integer int1 = GridPane.getColumnIndex(btn);
        Integer int2 = GridPane.getRowIndex(btn);
        if (int1 != null) {
            col = int1.intValue();
        } else {
            col = 0;
        }
        if (int2 != null) {
            row = int2.intValue();
        } else {
            row = 0;
        }
        return new Move(col, row);
    }

   public void setMacroBoardBorders(String[][] matrix) {
        int x=1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Node node = null;
                node = getNodeByRowColumnIndex(i, j, macroBoard);
                System.out.println(x+" matrix"+"["+i+"]"+"["+j+"]"+" "+matrix[i][j]);
                x++;
                if (matrix[i][j].equals("-1")) {
                    if (manager.getCurrentPlayer()==1) {
                        node.setStyle("-fx-background-color: #00ffff;"); // "blue"
                    } 
                    else {
                        node.setStyle("-fx-background-color: #50ff00;"); // green
                    }
                } else if (matrix[i][j].equals("0")) {
                    node.setStyle("-fx-background-color: #303030;");
                } else if (matrix[i][j].equals("WINo")) { 
                    node.setStyle("-fx-background-color: #303030;");
                    // TO DO WHEN o wins
                    
                } else if (matrix[i][j].equals("WINx")) { 
                  node.setStyle("-fx-background-color: #303030;");
                    // TO DO WHEN x wins
                     
                     
                }
               
            }
                
        }
        System.out.println("---------------------");
    }

    public Node getNodeByRowColumnIndex(final int row, final int column, GridPane board) {
        Node result = null;
        ObservableList<Node> childrens = board.getChildren();

        for (Node node : childrens) {
            int c;
            int r;
            Integer int1 = GridPane.getColumnIndex(node);
            Integer int2 = GridPane.getRowIndex(node);
            if (int1 != null) {
                c = int1;
            } else {
                c = 0;
            }
            if (int2 != null) {
                r = int2;
            } else {
                r = 0;
            }
            if (r == row && c == column) {
                result = node;
                break;
            }
        }

        return result;
    }
    public void setBoard(String[][] board){
        ObservableList<Node> buttonList = microBoard.getChildren();
        for (Node node : buttonList) {
            Button button = (Button) node;
            int c;
            int r;
            Integer int1 = GridPane.getColumnIndex(node);
            Integer int2 = GridPane.getRowIndex(node);
            if (int1 != null) {
                c = int1;
            } else {
                c = 0;
            }
            if (int2 != null) {
                r = int2;
            } else {
                r = 0;
            }
            if(board[r][c]=="x"){
                button.setText("x");
                button.getStyleClass().add("xbtn");
            }
            else if(board[r][c]=="o"){
                button.setText("o");
                button.getStyleClass().add("ybtn");
            }
        }
    
    
    }
    
    public void setMacroWin(){
        String[][] matrix = manager.getGameState().getField().getMacroboard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if("WINx".equals(matrix[i][j])){
                    
                }
                }
                }
            }
            
        
    
    
    public void setBoardStyle(int macroRow, int macroCol, String style){
        int microRow00 = macroRow * 3;
        int microCol00 = macroCol * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Node node = getNodeByRowColumnIndex(microRow00+i, microCol00+j, microBoard);
                node.setStyle(style);
            }
        }
    }
    public void setScore(){
        int x = 0;
        int o = 0;
        String[][] board = manager.getGameState().getField().getMacroboard();
        
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if("WINx".equals(board[i][j])){
                    System.out.println(board[i][j]);
                    x++;}
                else if("WINo".equals(board[i][j])){
                    System.out.println(board[i][j]);
                    o++;
                }
            }
        }
        System.out.println("ScoreO: "+o);
        System.out.println("ScoreX: "+x);
        xScore.setText(""+x);
        oScore.setText(""+o);

    }
    public void botVsBot(){
        while(true){
            if(manager.checkMacroWiner() == "x" || manager.checkMacroWiner() == "o")
                break;
            manager.updateGame();
            setBoard(manager.getGameState().getField().getBoard());
            setScore();
        }
    
    
    }
}
