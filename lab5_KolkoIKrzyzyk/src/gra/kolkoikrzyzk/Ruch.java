package gra.kolkoikrzyzk;

import java.util.Comparator;

/**
 * Ruch
 */
public class Ruch implements Comparable<Ruch>{
    private int xPos;
    private int yPos;
    private Gracz player;
    public Ruch(Gracz player, int xPos, int yPos) {
        
        this.xPos = xPos;
        this.yPos = yPos;
        this.player = player; 
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public char getPlayerMark() {
        return player.getMark();
    }
    public String getPlayerName() {
        return player.getName();
    }

	@Override
	public int compareTo(Ruch o) {
		if (this.xPos == o.getxPos() &&  this.yPos == o.getyPos()) return 0;
		else return 1;
    }
    @Override
    public boolean equals(Object o) {
        Ruch temp = (Ruch) o;
        if (this.xPos == temp.getxPos() &&  this.yPos == temp.getyPos()) return true;
        else return false;
    }




    
}