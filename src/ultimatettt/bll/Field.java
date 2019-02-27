/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatettt.bll;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Revy
 */
public class Field implements IField{
    String[][] board;
    String[][] macroBoard;

    @Override
    public void clearBoard() {
       for (String[] strings : board) {
            for (String string : strings) {
                string = EMPTY_FIELD;
            }
            
        }
       for (String[] strings : macroBoard) {
            for (String string : strings) {
                string = AVAILABLE_FIELD;
            }
            
        }
    }

    @Override
    public List<IMove> getAvailableMoves() {
        List<IMove> movesList = new ArrayList<>();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == AVAILABLE_FIELD){
                    movesList.add(new Move(i, j));
                }
            }
        }
        return movesList;
    }
    
   

    @Override
    public String getPlayerId(int column, int row) {
       return board[row][column];
    }

    @Override
    public boolean isEmpty() {
        for (String[] strings : board) {
            for (String string : strings) {
                if(string!=EMPTY_FIELD)
                    return false;
            }
            
        }
        return true;
    }

    @Override
    public boolean isFull() {
        for (String[] strings : board) {
            for (String string : strings) {
                if(string==EMPTY_FIELD)
                    return false;
            } 
        }
        return true;
    }

    @Override
    public Boolean isInActiveMicroboard(int x, int y) {
        if(macroBoard[y/3][x/3] == AVAILABLE_FIELD)
            return true;
        else
            return false;
    }

    @Override
    public String[][] getBoard() {
        return board;
    }

    @Override
    public String[][] getMacroboard() {
        return macroBoard;
    }

    @Override
    public void setBoard(String[][] board) {
        this.board = board;
        
    }

    @Override
    public void setMacroboard(String[][] macroboard) {
        this.macroBoard = macroBoard;
    }
    
}
