package com.atlasian.practice.snakegame;

import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(JMockit.class)
public class SnakeGameTest {

    @Test
    public void testSnakeGame(){
        Cell head = new Cell(2,3, CellType.SNAKE);
        Snake snake = new Snake(head);
        SnakeBoard board = new SnakeBoard(5);
        board.generateFood();board.generateFood();
        SnakeGame game = new SnakeGame(snake, board);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                game.updateMove();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        new Verifications(){
            {
                Assert.assertEquals(false, game.isGameOver());

                game.updateMove(Direction.DIRECTION_DOWN);
                game.updateMove(Direction.DIRECTION_UP);
                game.updateMove(Direction.DIRECTION_RIGHT);
                game.updateMove(Direction.DIRECTION_LEFT);

                Assert.assertEquals(true, game.isGameOver());
            }
        };

    }
}
