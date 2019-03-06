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
    public void setActiveMacroBoard(int x, int y) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ("-1".equals(macroBoard[i][j]) && macroBoard[i][j] != "WINo" && macroBoard[i][j] != "WINx") {
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
                if (!isMicroboardFull(j, i) && "0".equals(checkMicroWinner(j, i))) {
                    macroBoard[i][j] = "-1";
                } else {
                    if (!macroBoard[i][j].equals("WINo") && !macroBoard[i][j].equals("WINx")) {
                        macroBoard[i][j] = "0";
                    }
                }
            }
        }
        if (!"WINo".equals(macroBoard[y][x]) && !"WINx".equals(macroBoard[y][x]) && "0".equals(checkMicroWinner(x, y))) {
            macroBoard[y][x] = "-1";

        }
    }
    
    @Override
       public String checkMicroWinner(int x, int y) {
           int row = y * 3;
           int column = x * 3;

           if (checkRowsColumnsCross("x", row, column, board)) {
               macroBoard[row / 3][column / 3] = "WINx";
               return "x";
           } else if (checkRowsColumnsCross("o", row, column, board)) {
               macroBoard[row / 3][column / 3] = "WINo";
               return "o";
           }
           return "0";
       }
       
       public String checkMacroWinner(){
           if (checkRowsColumnsCross("WINx", 0, 0, macroBoard)) {
               return "x";
           } 
           else if (checkRowsColumnsCross("WINo", 0, 0, macroBoard)) {
               return "o";
           }
           return "0";
       }
       
    public Boolean checkRowsColumnsCross(String str, int row, int column, String[][] matrix) {
        for (int i = 0; i < 3; i++) {
            if (matrix[row + i][column] == str && matrix[row + i][column + 1] == str && matrix[row + i][column + 2] == str) {
                return true;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (matrix[row][column + j] == str && matrix[row + 1][column + j] == str && matrix[row + 2][column + j] == str) {
                return true;
            }
        }

        if (matrix[row][column] == str && matrix[row + 1][column + 1] == str && matrix[row + 2][column + 2] == str) {
            return true;
        }

        if (matrix[row][column + 2] == str && matrix[row + 1][column + 1] == str && matrix[row + 2][column] == str) {
            return true;
        }
        return false;
    }

}
