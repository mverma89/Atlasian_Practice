package com.atlasian.practice.tictoe;

public class TicToe implements Board{

    private int diagonal;
    private int antiDiagonal;
    private int[] horizontalBoard;
    private int[] verticalBoard;
    private int boardSize;

    public TicToe(int boardSize) {
        this.boardSize = boardSize;
        init();
    }

    @Override
    public void init() {
        this.diagonal = 0;
        this.antiDiagonal = 0;
        this.horizontalBoard = new int[boardSize];
        this.verticalBoard = new int[boardSize];
    }

    public int getDiagonal() {
        return diagonal;
    }

    public int getAntiDiagonal() {
        return antiDiagonal;
    }

    public int[] getHorizontalBoard() {
        return horizontalBoard;
    }

    public int[] getVerticalBoard() {
        return verticalBoard;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setDiagonal(int diagonal) {
        this.diagonal = diagonal;
    }

    public void setAntiDiagonal(int antiDiagonal) {
        this.antiDiagonal = antiDiagonal;
    }

    public void setHorizontalBoard(int[] horizontalBoard) {
        this.horizontalBoard = horizontalBoard;
    }

    public void setVerticalBoard(int[] verticalBoard) {
        this.verticalBoard = verticalBoard;
    }
}
