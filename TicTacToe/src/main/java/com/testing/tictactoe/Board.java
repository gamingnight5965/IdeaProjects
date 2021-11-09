package com.testing.tictactoe;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Board {
    private final char[][] board = new char[3][3];
    private final Scanner input = new Scanner(System.in);
    private boolean playerOne = true;
    private boolean gameOver;
    private char winner = ' ';
    private int turns = 0;
    private CSVWriter writer;
    private CSVReader reader;
    private ArrayList<String[]> dataAll = new ArrayList<String[]>();

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
        turns = 0;
        run();
    }
    public void train() {
        getFile();
        for(int i =0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = ' ';
            }
        }
        gameOver = false;
        turns = 1;
        training();
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
            turns++;


        }
        displayBoard();
    }

    private void training() {
        while(!gameOver){
            boolean goodMove = false;
            while(!goodMove){
                int move = getRandomMove();
                int row;
                int col;
                if(move > 9){
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
                    continue;
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
            turns++;



        }
        store();
    }
    private void displayBoard() {
        System.out.println(" " + board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println(" _________");
        System.out.println(" " + board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println(" _________");
        System.out.println(" " + board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
        for(int i = 0; i < 3; i++){
            System.out.println();
        }
    }

    private void checkWinState() {
        if (turns > 4 ) {
            for (int i = 0; i < 3; i++) {
                boolean win = true;
                for (int j = 1; j < 3; j++) {
                    char prevVal = board[i][j - 1];
                    if(board[i][j-1] != ' ') winner = board[i][j-1];
                    if (prevVal == ' ' || prevVal != board[i][j]) {
                        win = false;
                        winner = ' ';
                        break;
                    }
                }
                if (win) {
                    gameOver = true;
                    break;
                }
            }
            if(gameOver) return;
            for (int i = 0; i < 3; i++) {
                boolean win = true;
                for (int j = 1; j < 3; j++) {
                    char prevVal = board[j - 1][i];
                    if(board[j-1][i] != ' ') winner = board[j-1][i];
                    if (prevVal == ' ' || prevVal != board[j][i]) {
                        win = false;
                        winner = ' ';
                        break;
                    }
                }
                if (win) {
                    gameOver = true;
                    break;
                }
            }
            if(gameOver) return;
            if (board[0][0] != ' ' && board[1][1] == board[0][0] && board[2][2] == board[0][0]) {
                gameOver = true;
                winner = board[0][0];
            }
            if (board[0][2] != ' ' && board[1][1] == board[0][2] && board[0][2] == board[2][0]) {
                gameOver = true;
                winner = board[0][2];
            }
        } if (turns >= 9){
            gameOver = true;
            winner = ' ';
        }
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

    private int getRandomMove() {
        return (int) (Math.random() * 9 + 1);
    }

    public char getWinner(){
        return winner;
    }

    public void store(){

                String[] data = new String[19];
                Arrays.fill(data, "0");

                for(int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board[i][j] == 'X') {
                            data[(i * 3) + j] = "1";
                        } else if (board[i][j] == 'Y') {
                            data[(i * 3) + j + 9] = "1";
                        }
                    }
                }
                if(winner == 'O'){
                    data[18] = "1";
                }
                dataAll.add(data);
        }

        public void export(){
            try {
                writer.writeAll(dataAll);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void getFile() {
            try {
                //Get or Create new file called TrainData.csv
                File file = new File("TrainData.csv");
                if (file.createNewFile()) {
                    System.out.println("New file created: " + file.getName() + " at " + file.getPath());
                }

                // create FileWriter object using file
                FileWriter fileOutput = new FileWriter(file);
                // create FileReader object using file
                FileReader fileInput = new FileReader(file);

                // create CSVWriter using FileWriter object
                writer = new CSVWriter(fileOutput);
                // create CSVReader using FileReader object
                reader = new CSVReader(fileInput);
                if(reader.readNext() == null) {
                    String[] header = {"X1", "X2", "X3", "X4", "X5", "X6", "X7", "X8", "X8", "Y1", "Y2", "Y3", "Y4", "Y5", "Y6", "Y7", "Y8", "Y9", "OWin"};
                    writer.writeNext(header);
                }
            }
        catch(IOException | CsvValidationException e){
                System.out.println("An error Occurred.");
                e.printStackTrace();
            }
        }
    }

