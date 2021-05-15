package tictactoe.gameBoard;

import org.junit.Assert;
import org.junit.Test;
import tictactoe.gameboard.Board;

import java.awt.*;

public class BoardTest {
    @Test
    public void initializeBoardTest() {
        Board board = new Board();
        board.initializeTable("-XX-OO-X-");
        Assert.assertEquals(' ', board.at(0, 0));
        Assert.assertEquals('X', board.at(0, 1));
        Assert.assertEquals('X', board.at(0, 2));
        Assert.assertEquals(' ', board.at(1, 0));
        Assert.assertEquals('O', board.at(1, 1));
        Assert.assertEquals('O', board.at(1, 2));
        Assert.assertEquals(' ', board.at(2, 0));
        Assert.assertEquals('X', board.at(2, 1));
        Assert.assertEquals(' ', board.at(2, 2));
    }

    @Test
    public void initializeBoardWithDifferentCharsTest() {
        Board board = new Board();
        board.initializeTable("-XX-OOp.s");

        Assert.assertEquals(' ', board.at(0, 0));
        Assert.assertEquals('X', board.at(0, 1));
        Assert.assertEquals('X', board.at(0, 2));
        Assert.assertEquals(' ', board.at(1, 0));
        Assert.assertEquals('O', board.at(1, 1));
        Assert.assertEquals('O', board.at(1, 2));
        Assert.assertEquals(' ', board.at(2, 0));
        Assert.assertEquals(' ', board.at(2, 1));
        Assert.assertEquals(' ', board.at(2, 2));
    }

    @Test
    public void createEmptyBoardTest() {
        Board board = new Board();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Assert.assertEquals(' ', board.at(i, j));
            }
        }
    }

    @Test
    public void putMarkOnBoardTest() {
        Board board = new Board();
        board.setField(new Dimension(2, 0), 'X');
        board.setField(new Dimension(1, 2), 'O');

        Assert.assertEquals('X', board.at(0, 2));
        Assert.assertEquals('O', board.at(2, 1));
    }

    @Test
    public void isFieldEmptyTest() {
        Board board = new Board();
        board.setField(new Dimension(1, 0), 'X');

        Assert.assertTrue(board.isFieldEmpty(new Dimension(0, 0)));
        Assert.assertFalse(board.isFieldEmpty(new Dimension(1, 0)));
    }
}
