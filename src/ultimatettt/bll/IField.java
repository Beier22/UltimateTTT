/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatettt.bll;

import java.util.List;
import ultimatettt.bll.IMove;

/**
 *
 * @author mads_
 */
public interface IField {

    String AVAILABLE_FIELD = "-1";
    String UNAVAILABLE_FIELD = "0";
    String EMPTY_FIELD = ".";

    /**
     * Clears the board
     */
    void clearBoard();

    /**
     * Generates a list of available moves, moves are limited to the 3x3 area 
     * indicated by the opponents last move, and limited by occupied spaces.
     * @return List of currently available moves
     */
    List<IMove> getAvailableMoves();
    
    /**
     * Returns the player id on given column and row
     * @param column Column
     * @param row Row
     * @return String
     */
    String getPlayerId(int column, int row);

    boolean isEmpty();

    /**
     * Checks whether the field is full
     * @return Returns true when field is full, otherwise returns false.
     */
    boolean isFull();

    /**
     * Checks whether a specific board position is available for input.
     * It checks whether the board position is available for play in the 
     * macroboard (3x3), where it is marked using the AVAILABLE_FIELD character.
     * @param x
     * @param y
     * @return Returns true if the board position at (x,y) is available for input, false otherwise.
     */
    Boolean isInActiveMicroboard(int x, int y);

    /**
     * @return the Board (the entire 9x9 board)
     */
    String[][] getBoard();

    /**
     * @return the Macroboard (the overarching 3x3 board)
     */
    String[][] getMacroboard();

    /**
     * @param board the Board to set (the entire 9x9 board)
     */
    void setBoard(String[][] board);

    /**
     * @param macroboard the Macroboard to set (the overarching 3x3 board)
     */
    void setMacroboard(String[][] macroboard);
    Boolean isMicroboardFull(int x, int y);
    void setEveryOtherMacroBoard(int x, int y);
    public void setActiveMacroBoard(int x, int y);
}
