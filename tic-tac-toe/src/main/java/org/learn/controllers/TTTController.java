package org.learn.controllers;

import org.learn.models.Move;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class TTTController {
    private int randomNum;

    private static int score = 0;

    private static boolean gameOver = false;

    private static Move playerMove;

    private static Move randomPlayerMove;

    private static String board[][];

    private static final int BOARD_SIZE = 3;

    private static final String BLANK_CHARACTER = "_";

    private static final String BOARD_CHARACTER = "_ ";



    @PostMapping("/")
    public String startGame(){
        setup();

        return "Welcome to Tic-Tac-Toe.";
    }

    @PutMapping("/move")
    public String makeMove(@RequestBody Move move){

        if(checkMove(move)){
            board[move.getRow()][move.getColumn()] = "x";
            randomMove();
            return String.format("Player made a move at %s, %s", move.getRow(), move.getColumn());
        } else {
            return "Sorry. You cannot move there.";
        }

    }

    @GetMapping("/")
    public String printBoard(){
        String result = "";
        for(int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                if (board[i][j].equals(BLANK_CHARACTER)){
                    result = result + BOARD_CHARACTER;
                } else {
                    result = result + board[i][j] + " ";
                }
            }
            result = result + "\n";
        }
        return result;
    }

    @GetMapping("/status")
    public String gameOver(){

        if (checkWin(board, "x")){
            return "Player won!";
        } else if (checkWin(board, "o")){
            return "Computer won.";
        } else {
            return "Game is still undetermined.";
        }
    }

    private static void randomMove(){
        Random rand = new Random();
        int x = rand.nextInt(BOARD_SIZE);
        int y = rand.nextInt(BOARD_SIZE);
        randomPlayerMove = new Move(x, y);

        while(true) {
            if (checkMove(randomPlayerMove)) {
                board[randomPlayerMove.getRow()][randomPlayerMove.getColumn()] = "o";
                return;
            }
        }
    }

    private static void setup(){
        board = new String[BOARD_SIZE][BOARD_SIZE];
        for (int row = 0; row < BOARD_SIZE; row++) {
            for(int col = 0; col < BOARD_SIZE; col++){
                board[row][col] = BLANK_CHARACTER;
            }
        }
    }




    private static boolean checkWin(String[][] board, String player) {
        int count;
        // Check rows for win
        for (int row = 0; row < BOARD_SIZE; row++) {
            // reset counts for each row
            count = 0;
            for(int col = 0; col < BOARD_SIZE; col++){
                if(board[row][col].equalsIgnoreCase(player)){
                    count++;
                }
                if(count == BOARD_SIZE){
                    return true;
                }
            }
        }

        // Check columns for win
        //switch so the column increments after the row
        for (int col = 0; col < BOARD_SIZE; col++) {
            // reset counts for each row
            count = 0;
            for(int row = 0; row < BOARD_SIZE; row++){
                if(board[row][col].equalsIgnoreCase(player)){
                    count++;
                }
                if(count == BOARD_SIZE){
                    return true;
                }
            }
        }

        // Check diagonals
        for (int row = 0; row < BOARD_SIZE; row++) {
            // reset counts for each row
            count = 0;
            if(board[row][row].equalsIgnoreCase(player)){
                count++;
            }
            if(count == BOARD_SIZE){
                return true;
            }
        }

        for (int row = BOARD_SIZE - 1; row >= 0; row--) {
            // reset counts for each row
            count = 0;
            if(board[row][row].equalsIgnoreCase(player)){
                count++;
            }
            if(count == BOARD_SIZE){
                return true;
            }
        }
        return false;
    }

}

