package org.learn.models;

import java.util.List;

public class Board {
    private String[][] board;

    public Board(int length, int width) {
        this.board = new String[length][width];
    }

    List<Move> moves;
}
