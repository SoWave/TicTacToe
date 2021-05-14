package tictactoe.players;

import org.junit.Assert;
import org.junit.Test;
import tictactoe.gameboard.Board;

public class MediumAITest {
    @Test
    public void blockingBehaviourTest() {
        Board board = new Board();
        board.initializeTable("X--OO#-X-");

        Player mediumAi = new MediumAI(board, 'X');
        mediumAi.makeMove();
        Assert.assertEquals('X', board.at(1, 2));
    }

    @Test
    public void winningBehaviourTest() {
        Board board = new Board();
        board.initializeTable("XX#O-O---");

        Player mediumAI = new MediumAI(board, 'X');
        mediumAI.makeMove();
        Assert.assertEquals('X', board.at(0, 2));
    }
}
