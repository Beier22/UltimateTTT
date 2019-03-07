/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatettt.bll;

import static java.lang.Thread.sleep;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Revy
 */
public class Bot implements IBot{

    @Override
    public IMove doMove(IGameState state) {
        Random rand = new Random();
        int x = rand.nextInt(10);
        int y = rand.nextInt(10);
        Move move = new Move(x, y);
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return move;
        
    }
    
}
