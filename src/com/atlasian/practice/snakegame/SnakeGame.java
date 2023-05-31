package com.atlasian.practice.snakegame;

public class SnakeGame {

    private Snake snake;
    private SnakeBoard board;
    private Direction direction;
    private boolean gameOver;

    public SnakeGame(Snake snake, SnakeBoard board) {
        this.snake = snake;
        this.board = board;
        this.direction = Direction.DIRECTION_RIGHT;
        this.gameOver = false;
    }
    //https://www.geeksforgeeks.org/design-snake-game/


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void updateMove(){
        updateMove(this.direction);
    }

    public void updateMove(Direction newDirection){
        if(!gameOver){
            if(direction != Direction.DIRECTION_NONE){
                Cell nextCell = getNextCell(snake.getHead(), newDirection);

                if(nextCell == null || snake.checkCrash(nextCell)){
                    gameOver = true;
                    direction = Direction.DIRECTION_NONE;
                }
                else{
                    if(nextCell.getCellType() == CellType.FOOD){
                        snake.grow(nextCell);
                        board.generateFood();
                    }
                    else{
                        snake.move(nextCell);
                    }
                }
            }
        }
    }

    private Cell getNextCell(Cell currentPosition, Direction direction){
        int row = currentPosition.getRow();
        int col = currentPosition.getCol();

        if(direction == Direction.DIRECTION_DOWN){
            row++;
        }
        if(direction == Direction.DIRECTION_UP){
            row--;
        }
        if(direction == Direction.DIRECTION_LEFT){
            col--;
        }
        if(direction == Direction.DIRECTION_RIGHT){
            col++;
        }

        //Condition required when snake should come out from opposite boundary if it hits one side of boundary
        /*if(row < 0){
            row = board.getBoardSize()-1;
        }
        if(row >= board.getBoardSize()){
            row = 0;
        }
        if(col < 0){
            col = board.getBoardSize()-1;
        }
        if(col >= board.getBoardSize()){
            col = 0;
        }*/


        //Condition required when snake should be dead if it hits boundary -- going with this assumption in this example
        if(row >=0 && row < board.getBoardSize() && col>= 0 && col < board.getBoardSize()){
            return board.getBoard()[row][col];
        }
        return null;
    }
}
