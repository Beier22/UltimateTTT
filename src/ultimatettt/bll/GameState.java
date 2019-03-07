/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatettt.bll;

/**
 *
 * @author Revy
 */
public final class GameState implements IGameState{

    private final IField field;
    private int moveNumber;
    private int roundNumber;
    
    public GameState(){
        String[][] board = new String[9][9];
        String[][] macroBoard = new String[3][3];
        this.field = new Field();
        field.setBoard(board);
        field.setMacroboard(macroBoard);
        field.clearBoard();
        setMoveNumber(1);
    }
    
    @Override
    public IField getField() {
        return field;
    }

    @Override
    public int getMoveNumber() {
        return moveNumber;
    }

    @Override
    public void setMoveNumber(int moveNumber) {
        this.moveNumber = moveNumber;
    }

    @Override
    public int getRoundNumber() {
        return roundNumber;
    }

    @Override
    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }
    
}
