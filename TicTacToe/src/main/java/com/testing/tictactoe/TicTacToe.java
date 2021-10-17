package com.testing.tictactoe;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args)
    {
        boolean playAgain = true;
        Board board = new Board();
        Scanner input = new Scanner(System.in);
        while(playAgain) {
            board.start();
            System.out.println("Game over, " + board.getWinner() + " won !");
            System.out.println("Play Again? (Y/N)");
            if(input.nextLine().equals("N")) playAgain = false;
        }
    }
}
