/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatettt.bll;

import java.util.List;
import ultimatettt.bll.IBot;
import static ultimatettt.bll.IField.AVAILABLE_FIELD;
import static ultimatettt.bll.IField.UNAVAILABLE_FIELD;
import ultimatettt.bll.IMove;

/**
 *
 * @author mads_
 */
public class GameManager {

    /**
     * Three different game modes.
     */
    public enum GameMode {
        HumanVsHuman,
        HumanVsBot,
        BotVsBot
    }

    private final IGameState currentState;

    private int currentPlayer = 0; //player0 == 0 && player1 == 1
    private GameMode mode = GameMode.HumanVsHuman;
    private IBot bot = null;
    private IBot bot2 = null;
    private IField ifield = null;

    /**
     * Set's the currentState so the game can begin. Game expected to be played
     * Human vs Human
     *
     * @param currentState Current game state, usually an empty board, but could
     * load a saved game.
     */
    public GameManager(IGameState currentState) {
        this.currentState = currentState;
        mode = GameMode.HumanVsHuman;
    }

    /**
     * Set's the currentState so the game can begin. Game expected to be played
     * Human vs Bot
     *
     * @param currentState Current game state, usually an empty board, but could
     * load a saved game.
     * @param bot The bot to play against in vsBot mode.
     */
    public GameManager(IGameState currentState, IBot bot) {
        this.currentState = currentState;
        mode = GameMode.HumanVsBot;
        this.bot = bot;
    }

    /**
     * Set's the currentState so the game can begin. Game expected to be played
     * Bot vs Bot
     *
     * @param currentState Current game state, usually an empty board, but could
     * load a saved game.
     * @param bot The first bot to play.
     * @param bot2 The second bot to play.
     */
    public GameManager(IGameState currentState, IBot bot, IBot bot2) {
        this.currentState = currentState;
        mode = GameMode.BotVsBot;
        this.bot = bot;
        this.bot2 = bot2;
    }

    /**
     * User input driven Update
     *
     * @param move The next user move
     * @return Returns true if the update was successful, false otherwise.
     */
    public Boolean updateGame(IMove move) {
        //Verify the new move

        if (verifyMoveLegality(move)) {
            updateBoard(move);
            updateMacroboard(move);

            currentPlayer = (currentPlayer + 1) % 2;
            System.out.println(currentPlayer);
            return true;
        }
        //Update the currentState

        //Update currentPlayer
        return false;
    }

    /**
     * Non-User driven input, e.g. an update for playing a bot move.
     *
     * @return Returns true if the update was successful, false otherwise.
     */
    public Boolean updateGame(){
        

        //Check if player is bot, if so, get bot input and update the state based on that.
        if (mode == GameMode.HumanVsBot && currentPlayer == 1) {
            //Check bot is not equal to null, and throw an exception if it is.
            assert (bot != null);

            IMove botMove = bot.doMove(currentState);

            //Be aware that your bots might perform illegal moves.
            while(true){
            if (updateGame(botMove)==false)
                botMove = bot.doMove(currentState);
            else
                break;
            }
            return updateGame(botMove);
        }

        else if(mode == GameMode.BotVsBot){
            
            while(true){
            IMove botMove = bot.doMove(currentState);
            if (updateGame(botMove)==false)
                continue;
            else
                updateGame(botMove);
                break;
            }
            
            while(true){
            IMove bot2Move = bot2.doMove(currentState);
            if (updateGame(bot2Move)==false)
                continue;
            else
                updateGame(bot2Move);
                break;
            }
        
        
            return true;
    }
            return false;
    }

    private Boolean verifyMoveLegality(IMove move) {
        //  System.out.println("Odpala verify - GameManager");
        //Test if the move is legal   
        //NOTE: should also check whether the move is placed on an occupied spot.
        if (checkIfButtonIsInTheList(move)) {
            return currentState.getField().isInActiveMicroboard(move.getX(), move.getY());
        } else {
            return false;
        }
    }

    private void updateBoard(IMove move) {
        String[][] board = currentState.getField().getBoard();

        if (currentPlayer == 1) {
            board[move.getY()][move.getX()] = "x";
        } else {
            board[move.getY()][move.getX()] = "o";
        }
        currentState.getField().setBoard(board);
        checkMicroWiner(move);
    }

    private void updateMacroboard(IMove move) {
        int y = move.getY() % 3;
        int x = move.getX() % 3;
        if (!currentState.getField().isMicroboardFull(x, y) && currentState.getField().checkMicroWinner(x, y) == "0") {
            currentState.getField().setActiveMacroBoard(x, y);
        } else {
            currentState.getField().setEveryOtherMacroBoard(x, y);
        }
    }

    private String[][] getMacroBoard() {
        return currentState.getField().getMacroboard();
    }

    public IGameState getGameState() {
        return currentState;
    }

    private Boolean checkIfButtonIsInTheList(IMove move) {
        List<IMove> list = currentState.getField().getAvailableMoves();
        for (IMove iMove : list) {
            if (iMove.getX() == move.getX() && iMove.getY() == move.getY()) {

                return true;
            }
        }
        return false;
    }

    public String checkMicroWiner(IMove move) {
       return currentState.getField().checkMicroWinner(move.getX() / 3, move.getY() / 3);
    }
    public String checkMacroWiner() {
       return currentState.getField().checkMacroWinner();
    }
    public int getCurrentPlayer(){
        return currentPlayer;
    }

}
