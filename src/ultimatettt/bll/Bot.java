/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatettt.bll;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Revy
 */
public class Bot implements IBot{

    @Override
    public IMove doMove(IGameState state) {
        List<IMove> list = state.getField().getAvailableMoves();
        IMove move;
        
        while(true){
            Random rand = new Random();
            int x = rand.nextInt(9);
            int y = rand.nextInt(9);
            move = new Move(x, y);
            if(state.getField().isInActiveMicroboard(move.getX(), move.getY())&&isMoveOnList(list, move)){
                return move;
            }
            else
                continue;
        }
    }
    public Boolean isMoveOnList(List<IMove> list, IMove move){
        for (IMove iMove : list) {
            if(iMove.getX()==move.getX()&&iMove.getY()==move.getY())
                return true;
        }
        return false;
    }
}

