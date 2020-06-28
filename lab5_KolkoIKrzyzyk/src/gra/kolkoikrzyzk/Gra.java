package gra.kolkoikrzyzk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gra {

    private Gracz player1;
    private Gracz player2;
    private ArrayList<Ruch> listOfMoves = new ArrayList<>();

    public Gra(Gracz player1, Gracz player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Ruch getMove(Gracz player) {
        int xpos = scan.nextInt(); 
        while(xpos != 0 && xpos != 2 && xpos != 4) xpos = scan.nextInt();
        int ypos = scan.nextInt(); 
        while(ypos != 0 && ypos != 2 && ypos != 4) ypos = scan.nextInt();
        return new Ruch(player, xpos, ypos);
    }
    
    public Gracz getPlayer1() {
        return player1;
    }

    public Gracz getPlayer2() {
        return player2;
    }

    public ArrayList<Ruch> getListOfMoves() {
        return listOfMoves;
    }

    public void setListOfMoves(Ruch move) {
        this.listOfMoves.add(move);
    }
    private static void printWinner(char c) {
        System.out.println("Gracz ze znakiem " + c + " wygrał");
    }
    public boolean getWinner(char[][] board ){
        for (int i = 0; i < board.length; i+=2) {
            if(board[i][0] == board[i][2] && board[i][2] == board[i][4] && board[i][0] !=  Character.MIN_VALUE) {
                printWinner(board[i][0]);
                return true;
            }
            else if(board[0][i] == board[2][i] && board[2][i] == board[4][i] && board[0][i] !=  Character.MIN_VALUE){
                printWinner(board[0][i]);
                return true;
            } 
        }
        if(board[0][0] == board[2][2] && board[2][2] == board[4][4] && board[0][0] !=  Character.MIN_VALUE){
            printWinner(board[0][0]);
            return true;
        } 
        else if(board[0][4] == board[2][2] && board[2][2] == board[4][0] && board[0][4] !=  Character.MIN_VALUE){
            printWinner(board[0][4]);
            return true;
        }
        else if (this.listOfMoves.size() == 9 ) System.out.println("Remis");
        return false;
     }
    
    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        Gra ticTacToe = new Gra(new Gracz("Mickiewicz", 'O'), new Gracz("Słowacki", 'X'));
        Plansza board = new Plansza();
        Komentator szpakowiki = new Komentator();
        for (int i = 0; i < 9; i++) {
            Ruch move; 
            do{
                move = ticTacToe.getMove((i%2 == 0)? ticTacToe.getPlayer1() : ticTacToe.getPlayer2()); 
            } while(ticTacToe.getListOfMoves().contains(move));
            ticTacToe.setListOfMoves(move);
            board.print(ticTacToe.getListOfMoves());
            szpakowiki.print(ticTacToe.getListOfMoves());
            if(ticTacToe.getWinner(board.getBoard())) break;
        }
       
    }

}