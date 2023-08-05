package org.learn.models;

public class Move {

    private int row;

    private int column;

    private String character;

    public int getRow() {
        return row;
    }

    public Move(int row, int column, String character) {
        this.row = row;
        this.column = column;
        this.character = character;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    private boolean isValidMove(String[][] board){
        Move move = this;
        if(move.getRow() >= board.length || move.getRow() < 0 ||
            move.getColumn() >= board.length || move.getColumn() < 0){
            return false;
        }

        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++){
                if (board[i][j] == null){
                    return true;
                }
            }
        }
        return false;
    }


}
