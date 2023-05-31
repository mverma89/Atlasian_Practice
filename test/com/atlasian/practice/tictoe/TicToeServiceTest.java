package com.atlasian.practice.tictoe;

import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class TicToeServiceTest {

    @Test
    public void testTicToeGame(){
        Player player_A = new Player("Mohit");
        Player player_B = new Player("Verma");

        TicToeService ticToeService = new TicToeService(3);
        ticToeService.setPlayer_A(player_A);
        ticToeService.setPlayer_B(player_B);

        ticToeService.startGame();

        new Verifications(){
            {
                Assert.assertEquals(null, ticToeService.playMove(0,0));
                Assert.assertEquals(null, ticToeService.playMove(1,1));
                Assert.assertEquals(null, ticToeService.playMove(2,2));
                Assert.assertEquals(null, ticToeService.playMove(1,0));
                Assert.assertEquals(null, ticToeService.playMove(0,1));
                GameResult result = ticToeService.playMove(1,2);
                Assert.assertEquals(GameStatus.GAME_OVER, result.getGameStatus());
                Assert.assertEquals(player_B.getPlayerName(), result.getWinner().getPlayerName());
            }
        };
    }

    @Test
    public void testTicToeGameTie(){
        Player player_A = new Player("Mohit");
        Player player_B = new Player("Verma");

        TicToeService ticToeService = new TicToeService(3);
        ticToeService.setPlayer_A(player_A);
        ticToeService.setPlayer_B(player_B);

        ticToeService.startGame();

        new Verifications(){
            {
                Assert.assertEquals(null, ticToeService.playMove(0,0));
                Assert.assertEquals(null, ticToeService.playMove(1,1));
                Assert.assertEquals(null, ticToeService.playMove(2,2));
                Assert.assertEquals(null, ticToeService.playMove(1,0));
                Assert.assertEquals(null, ticToeService.playMove(1,2));
                Assert.assertEquals(null, ticToeService.playMove(0,2));
                Assert.assertEquals(null, ticToeService.playMove(2,0));
                Assert.assertEquals(null, ticToeService.playMove(2,1));
                GameResult result = ticToeService.playMove(0,1);
                Assert.assertEquals(GameStatus.TIE, result.getGameStatus());
                Assert.assertEquals(null, result.getWinner());
            }
        };
    }
}
