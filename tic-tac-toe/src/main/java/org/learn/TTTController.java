package org.learn;

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



    @PostMapping("/")
    public String startGame(){
        setup();

        return "Welcome to the Tic-Tac-Toe.";
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
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (board[i][j] == null){
                    result = result + "_ ";
                } else {
                    result = result + board[i][j] + " ";
                }
            }
            result = result + "\n";
        }
        return result;
    }

    private static void randomMove(){
        Random rand = new Random();
        int x = rand.nextInt(3);
        int y = rand.nextInt(3);
        randomPlayerMove = new Move(x, y);

        while(true) {
            if (checkMove(randomPlayerMove)) {
                board[randomPlayerMove.getRow()][randomPlayerMove.getColumn()] = "o";
                return;
            }
        }
    }

    private static void setup(){
        board = new String[3][3];
    }

    private static boolean checkMove(Move move){
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (board[i][j] == null){
                    return true;
                }
            }
        }
        return false;
    }

//    public boolean checkWin(){
//        int countX = 0;
//        int countO = 0;
//
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//
//                if(board[i][j] == "x") countX++;
//                else if(board[i][j] == "o")countO++;
//            }            if(countX == 3 || countO == 3) return true;
//            break;
//        }
//        countO = 0;
//        countX = 0;
//
//        //check diagonal right
//        for (int i = 0, j= 0; i <3 ; i++, j++) {
//            if (board[i][j] == "x") countX++;
//            else if(board[i][j] == "o") countO++;
//        }        if(countO == 3 || countX == 3) return true;
//
//        //check diagonal left        for (int i = size-1, j= size-1; i >= 0 ; i--, j--) {
//        if (board[i][j] == 0) countX++;
//        else if(board[i][j] == 1) countO++;
//    }
//        if(countX == 3 || countO == 3) return true;
//
//        return false;


}

