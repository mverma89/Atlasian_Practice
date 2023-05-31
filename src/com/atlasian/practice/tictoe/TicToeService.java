package com.atlasian.practice.tictoe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicToeService {

    private static int MAX_ALLOWED_PLAYER = 2;
    private Player player_A;
    private Player player_B;
    private TicToe board;
    private Player currentMovePlayer;
    private Player nextMovePlayer;
    private Map<Player, Integer> playerMoveValue;
    private Integer totalMoves;
    private GameStatus gameStatus;

    public TicToeService(int boardSize) {
        this.board = new TicToe(boardSize);
        this.gameStatus = GameStatus.NOT_STARTED;
    }

    public void setPlayer_A(Player player_A) {
        this.player_A = player_A;
    }

    public void setPlayer_B(Player player_B) {
        this.player_B = player_B;
    }

    public void startGame(){
        this.currentMovePlayer = this.player_A;
        this.nextMovePlayer = this.player_B;
        this.playerMoveValue = new HashMap<>();
        this.playerMoveValue.put(this.player_A, -1);
        this.playerMoveValue.put(this.player_B, 1);
        this.totalMoves = this.board.getBoardSize()*this.board.getBoardSize();
        this.gameStatus = GameStatus.STARTED;
    }

    public GameResult playMove(int row, int col){

        if(this.gameStatus == GameStatus.STARTED){
            if(row == col){
                board.setDiagonal(board.getDiagonal()+playerMoveValue.get(currentMovePlayer));
            }

            if(board.getBoardSize()-col-1 == row){
                board.setAntiDiagonal(board.getDiagonal()+playerMoveValue.get(currentMovePlayer));
            }

            board.getHorizontalBoard()[col] += playerMoveValue.get(currentMovePlayer);
            board.getVerticalBoard()[row] += playerMoveValue.get(currentMovePlayer);

            if(hasWon(row, col)){
                this.gameStatus = GameStatus.GAME_OVER;
                return new GameResult(GameStatus.GAME_OVER, currentMovePlayer);
            }

            totalMoves--;

            if(totalMoves == 0){
                return new GameResult(GameStatus.TIE, null);
            }

            Player tempNextPlayer = nextMovePlayer;
            nextMovePlayer = currentMovePlayer;
            currentMovePlayer = tempNextPlayer;
        }
        else{
            throw new RuntimeException("Invalid move!!");
        }
        return null;
    }

    private boolean hasWon(int row, int col){
        return Math.abs(board.getHorizontalBoard()[col]) == board.getBoardSize()
        || Math.abs(board.getVerticalBoard()[row]) == board.getBoardSize()
        || Math.abs(board.getDiagonal()) == board.getBoardSize()
        || Math.abs(board.getAntiDiagonal()) == board.getBoardSize();
    }

}
