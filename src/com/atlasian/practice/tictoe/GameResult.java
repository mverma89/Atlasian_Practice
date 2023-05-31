package com.atlasian.practice.tictoe;

public class GameResult {

    private GameStatus gameStatus;
    private Player winner;

    public GameResult(GameStatus gameStatus, Player winner) {
        this.gameStatus = gameStatus;
        this.winner = winner;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Player getWinner() {
        return winner;
    }
}
