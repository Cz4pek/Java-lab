package gra.kolkoikrzyzk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class Plansza implements Drukowalny{
    private char[][] board = new char [5][5];

    public Plansza() {
        board[0][1] = '|';
        board[0][3] = '|';
        for (int i = 0; i < 5; i++) {
            board[1][i] = '-';
        }
        board[2][1] = '|';
        board[2][3] = '|';
        for (int i = 0; i < 5; i++) {
            board[3][i] = '-';
        }
        board[4][1] = '|';
        board[4][3] = '|';
    }

    @Override
    public void print(ArrayList<Ruch> moves) {
        
        for (Ruch ruch : moves) {
            board[ruch.getxPos()][ruch.getyPos()] = ruch.getPlayerMark();
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j]);  
            }
            System.out.println();
        }
    }

    public char[][] getBoard() {
        return board;
    }

}