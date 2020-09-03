package tictactoe.players;

import org.junit.Assert;
import org.junit.Test;
import tictactoe.gameboard.Board;

public class HardAITest {
    @Test
    public void blockingBehaviourTest() {
        Board board = new Board();
        board.initializeTable("X--OO#-X-");

        Player hardAI = new HardAI(board);
        hardAI.makeMove();
        Assert.assertEquals('X', board.getBoard()[1][2]);
    }

    @Test
    public void winningBehaviourTest() {
        Board board = new Board();
        board.initializeTable("XX#O-O---");

        Player hardAI = new HardAI(board);
        hardAI.makeMove();
        Assert.assertEquals('X', board.getBoard()[0][2]);
    }

    @Test
    public void bestPossibleMoveBehaviourTest() {
        Board board = new Board();
        board.initializeTable("O-XX-X-OO");

        Player hardAI = new HardAI(board);
        hardAI.makeMove();
        Assert.assertEquals('X', board.getBoard()[1][1]);
    }

    @Test
    public void bestPossibleMove2BehaviourTest() {
        Board board = new Board();
        board.initializeTable("X--------");

        Player hardAI = new HardAI(board);
        hardAI.makeMove();
        Assert.assertEquals('O', board.getBoard()[1][1]);
    }

    @Test
    public void bestPossibleMove3BehaviourTest() {
        Board board = new Board();
        board.initializeTable("XXO-O----");

        Player hardAI = new HardAI(board);
        hardAI.makeMove();
        Assert.assertEquals('X', board.getBoard()[2][0]);
    }
}
