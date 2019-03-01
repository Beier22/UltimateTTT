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
public class Field implements IField {

    String[][] board;
    String[][] macroBoard;

    @Override
    public void clearBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = ".";
            }

        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                macroBoard[i][j] = "-1";
            }

        }
    }

    @Override
    public List<IMove> getAvailableMoves() {
        List<IMove> movesList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (".".equals(board[i][j])) {
                    movesList.add(new Move(j, i));
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
                if (!string.equals(".")) {
                    return false;
                }
            }

        }
        return true;
    }

    @Override
    public boolean isFull() {
        for (String[] strings : board) {
            for (String string : strings) {
                if (string.equals(".")) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Boolean isInActiveMicroboard(int x, int y) {
        //  System.out.println("macro value: "+macroBoard[y%3][x%3]);
        return macroBoard[y / 3][x / 3].equals("-1");
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
    public void setMacroboard(String[][] macroBoard) {
        this.macroBoard = macroBoard;
    }

    @Override
    public Boolean isMicroboardFull(int x, int y) {

        for (int i = y * 3; i <= (y * 3) + 2; i++) {
            for (int j = x * 3; j <= (x * 3) + 2; j++) {
                if (board[i][j].equals(".")) {
                    return false;
                }
            }
        }
        return true;
    }

   
    @Override
    public String checkWinner(int x, int y) {
        int row = y*3;
        int column = x*3;
        
        if(checkRowsColumnsCross("x",row,column))
            return "x";
        else
        if(checkRowsColumnsCross("o",row,column))
            return "o";
        return "0";
    }

    @Override
    public void setActiveMacroBoard(int x, int y) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ("-1".equals(macroBoard[i][j])) {
                    macroBoard[i][j] = "0";
                }
            }
        }
        macroBoard[y][x] = "-1";
    }

    @Override
    public void setEveryOtherMacroBoard(int x, int y) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!isMicroboardFull(j, i)) {
                    macroBoard[i][j] = "-1";
                } else {
                    macroBoard[i][j] = "0";
                }
            }
        }
        macroBoard[y][x] = "0";
    }

    public Boolean checkRowsColumnsCross(String str, int row, int column)
    {
        for(int i = 0; i < 3; i++){
            if(board[row+i][column]==str&&board[row+i][column+1]==str&&board[row+i][column+2]==str){
                return true;
            }}
            
        for(int j = 0; j < 3; j++){
            if(board[row][column+j]==str&&board[row+1][column+j]==str&&board[row+2][column+j]==str){
                return true;
            }}
        
            if(board[row][column]==str&&board[row+1][column+1]==str&&board[row+2][column+2]==str){
                return true;
            }
            
            if(board[row][column]==str&&board[row+1][column+1]==str&&board[row+2][column+2]==str){
                return true;
            }
            return false;
        }
        
            
    }        

    