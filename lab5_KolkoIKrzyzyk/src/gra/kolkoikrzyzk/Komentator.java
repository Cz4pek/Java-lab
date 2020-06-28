package gra.kolkoikrzyzk;

import java.util.ArrayList;
import java.util.Collection;

public class Komentator implements Drukowalny {

    @Override
    public void print(ArrayList<Ruch> moves) {
        Ruch move = moves.get(moves.size()-1);
        System.out.println("Gracz " + move.getPlayerName() + " postawił znak " + move.getPlayerMark() + " na polu " + move.getxPos() + " " + move.getyPos() );
    }

}