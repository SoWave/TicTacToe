package tictactoe.players;

import org.junit.Assert;
import org.junit.Test;
import tictactoe.gameboard.Board;

public class EasyAITest {
    @Test
    public void nonBlockingBehaviourTest() {
        for (int i = 0; i < 3; i++) {
            Board board = new Board();
            board.initializeTable("X--OO#-X-");

            Player easyAI = new EasyAI(board);
            easyAI.makeMove();
            Assert.assertNotEquals('X', board.getBoard()[1][2]);
        }
    }

    @Test
    public void nonWinningBehaviourTest() {
        for (int i = 0; i < 3; i++) {
            Board board = new Board();
            board.initializeTable("XX#O-O---");

            Player easyAI = new EasyAI(board);
            easyAI.makeMove();
            Assert.assertNotEquals('X', board.getBoard()[0][2]);
        }
    }
}
