package com.atlasian.practice.snakegame;

import java.util.LinkedList;
import java.util.List;

public class Snake {

    private List<Cell> snake;
    private Cell head;

    public Snake(Cell initHeadPosition) {
        this.snake = new LinkedList<>();
        head = initHeadPosition;
        this.snake.add(initHeadPosition);
    }

    public Cell getHead() {
        return head;
    }

    public void grow(Cell newHead){
        snake.add(newHead);
        head = newHead;
    }

    public void move(Cell nextCell){
        nextCell.setCellType(CellType.SNAKE);
        head = nextCell;
        snake.add(nextCell);
        Cell tail = snake.remove(0);
        tail.setCellType(CellType.EMPTY);
    }

    public boolean checkCrash(Cell nextCell){

        for(Cell cell : snake){
            if(cell == nextCell){
                return true;
            }
        }
        return false;
    }

}
