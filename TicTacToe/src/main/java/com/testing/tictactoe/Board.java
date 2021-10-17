package com.testing.tictactoe;

import java.util.Scanner;

public class Board {
    private final char[][] board = new char[3][3];
    private boolean playerOne = true;
    private final Scanner input = new Scanner(System.in);
    private boolean gameOver;
    private char winner = ' ';

    public Board(){
        for(int i =0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = ' ';
            }
        }

    }

    public void start() {
        for(int i =0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = ' ';
            }
        }
        gameOver = false;
        run();
    }

    private void run() {
        while(!gameOver){
            displayBoard();
            displayMoveBoard();
            boolean goodMove = false;
            while(!goodMove){
                int move = getMove();
                int row;
                int col;
                if(move > 9){
                    System.out.println("That move is invalid");
                    continue;
                }
                if(move%3 == 0){
                    col = 2;
                } else {
                    col = move%3 -1;
                }
                if (move <= 3){
                    row = 0;
                } else if(move <= 6) {
                    row = 1;
                } else {
                    row = 2;
                }
                if(board[row][col] != ' '){
                    System.out.println("That move is invalid");
                } else {
                    if(playerOne){
                        board[row][col] = 'X';
                    } else {
                        board[row][col] = 'O';
                    }
                    goodMove = true;
                }
                checkWinState();
            }
            playerOne = !playerOne;

        }
        displayBoard();
    }
    private void displayBoard() {
        System.out.println(" " + board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println(" _________");
        System.out.println(" " + board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println(" _________");
        System.out.println(" " + board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
        for(int i = 0; i < 4; i++){
            System.out.println();
        }
    }

    private void checkWinState(){
        for(int i = 0; i < 3; i++){
            boolean win = true;
            for(int j = 1; j < 3; j++){
                char prevVal = board[i][j-1];
                winner = board[i][j-1];
                if(prevVal == ' ' || prevVal != board[i][j]){
                    win = false;
                    winner = ' ';
                    break;
                }
            }
            if(win) gameOver = true;
        }
        for(int i = 0; i < 3; i++){
            boolean win = true;
            for(int j = 1; j < 3; j++){
                char prevVal = board[j-1][i];
                winner = board[i][j-1];
                if(prevVal == ' ' || prevVal != board[j][i]){
                    win = false;
                    winner = ' ';
                    break;
                }
            }
            if(win) gameOver = true;
        }
        if(board[0][0] != ' ' && board[1][1] == board[0][0] && board[2][2] == board[0][0]){ gameOver = true; winner = board[0][0];}
        if(board[0][2] != ' ' && board[1][1] == board[0][2] && board[0][2] == board[2][0]){ gameOver = true; winner = board[0][2];}
    }

    private void displayMoveBoard(){
        System.out.println(" " + 1 + " | " + 2 + " | " + 3);
        System.out.println(" _________");
        System.out.println(" " + 4 + " | " + 5 + " | " + 6);
        System.out.println(" _________");
        System.out.println(" " + 7 + " | " + 8 + " | " + 9);
        System.out.println("__________________________________\n");

    }
    private int getMove() {
        if(playerOne){
            System.out.println("Player One (X) Enter a move(0-9): ");
        } else {
            System.out.println("Player Two (O) Enter a move(0-9): ");
        }
        return input.nextInt();
    }
    public char getWinner(){
        return winner;
    }

}
