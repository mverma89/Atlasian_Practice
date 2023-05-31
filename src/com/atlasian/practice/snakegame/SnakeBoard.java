package com.atlasian.practice.snakegame;

import java.util.List;

public class SnakeBoard {

    private Cell[][] board;
    private Integer boardSize;

    public SnakeBoard(Integer boardSize) {
        this.boardSize = boardSize;
        this.board = new Cell[boardSize][boardSize];
        init();
    }

    private void init(){
        for(int r = 0; r<boardSize; r++){
            for(int c=0; c<boardSize; c++){
                board[r][c] = new Cell(r,c, CellType.EMPTY);
            }
        }
    }

    public Cell[][] getBoard() {
        return board;
    }

    public Integer getBoardSize() {
        return boardSize;
    }

    public void generateFood(){
        int row = 0, col = 0;
        while(true){
            row = (int)Math.random()*boardSize;
            col = (int)Math.random()*boardSize;

            if(board[row][col].cellType != CellType.SNAKE || board[row][col].cellType != CellType.FOOD)
                break;
        }

        board[row][col].setCellType(CellType.FOOD);
    }


}
