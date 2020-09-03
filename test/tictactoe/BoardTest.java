package tictactoe;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class BoardTest {
    @Test
    public void initializeBoardTest() {
        Board board = new Board();
        board.initializeTable("_XX_OO_X_");
        Assert.assertEquals(' ', board.getBoard()[0][0]);
        Assert.assertEquals('X', board.getBoard()[0][1]);
        Assert.assertEquals('X', board.getBoard()[0][2]);
        Assert.assertEquals(' ', board.getBoard()[1][0]);
        Assert.assertEquals('O', board.getBoard()[1][1]);
        Assert.assertEquals('O', board.getBoard()[1][2]);
        Assert.assertEquals(' ', board.getBoard()[2][0]);
        Assert.assertEquals('X', board.getBoard()[2][1]);
        Assert.assertEquals(' ', board.getBoard()[2][2]);
    }

    @Test
    public void initializeBoardWithDifferentCharsTest() {
        Board board = new Board();
        board.initializeTable("_XX-OOp.s");

        Assert.assertEquals(' ', board.getBoard()[0][0]);
        Assert.assertEquals('X', board.getBoard()[0][1]);
        Assert.assertEquals('X', board.getBoard()[0][2]);
        Assert.assertEquals(' ', board.getBoard()[1][0]);
        Assert.assertEquals('O', board.getBoard()[1][1]);
        Assert.assertEquals('O', board.getBoard()[1][2]);
        Assert.assertEquals(' ', board.getBoard()[2][0]);
        Assert.assertEquals(' ', board.getBoard()[2][1]);
        Assert.assertEquals(' ', board.getBoard()[2][2]);
    }

    @Test
    public void initializeBoardStartingTurnMarkIsXTest() {
        Board board = new Board();
        Assert.assertEquals('X', board.getCurrentTurnMark());
    }

    @Test
    public void initializeBoardEvenTurnTest() {
        Board board = new Board();
        board.initializeTable("_XX_OO___");
        Assert.assertEquals('X', board.getCurrentTurnMark());
    }

    @Test
    public void initializeBoardOddTurnTest() {
        Board board = new Board();
        board.initializeTable("_XX_OO_X_");
        Assert.assertEquals('O', board.getCurrentTurnMark());
    }

    @Test
    public void createEmptyBoardTest() {
        Board board = new Board();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Assert.assertEquals(' ', board.getBoard()[i][j]);
            }
        }
    }

    @Test
    public void putMarkOnBoardTest() {
        Board board = new Board();
        board.putMark(new Dimension(2, 0));
        board.putMark(new Dimension(1, 2));

        Assert.assertEquals('X', board.getBoard()[0][2]);
        Assert.assertEquals('O', board.getBoard()[2][1]);
    }

    @Test
    public void setFieldEmptyTest() {
        Board board = new Board();
        board.putMark(new Dimension(2, 0));

        Assert.assertEquals('X', board.getBoard()[0][2]);
        Assert.assertEquals('O', board.getCurrentTurnMark());

        board.setFieldEmpty(new Dimension(2, 0));

        Assert.assertEquals(' ', board.getBoard()[0][2]);
        Assert.assertEquals('X', board.getCurrentTurnMark());
    }

    @Test
    public void isFieldEmptyTest() {
        Board board = new Board();
        board.putMark(new Dimension(1, 0));

        Assert.assertTrue(board.isFieldEmpty(new Dimension(0, 0)));
        Assert.assertFalse(board.isFieldEmpty(new Dimension(1, 0)));
    }

    @Test
    public void getOppositeSignTest() {
        Board board = new Board();
        Assert.assertEquals('X', board.getCurrentTurnMark());
        Assert.assertEquals('O', board.getOppositeMark());
    }
}
