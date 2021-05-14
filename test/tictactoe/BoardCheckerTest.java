package tictactoe;

import org.junit.Assert;
import org.junit.Test;
import tictactoe.gameboard.Board;
import tictactoe.gameboard.BoardChecker;

public class BoardCheckerTest {
    @Test
    public void horizontalCheckTest() {
        Board board = new Board();
        board.initializeTable("-XX-OO---");
        BoardChecker bc = new BoardChecker(board);

        Assert.assertEquals(2, bc.horizontalCheck(0, 'X'));
        Assert.assertEquals(2, bc.horizontalCheck(1, 'O'));
        Assert.assertEquals(0, bc.horizontalCheck(2, 'X'));
    }

    @Test
    public void verticalCheckTest() {
        Board board = new Board();
        board.initializeTable("XO--O--X-");
        BoardChecker bc = new BoardChecker(board);

        Assert.assertEquals(1, bc.verticalCheck(0, 'X'));
        Assert.assertEquals(2, bc.verticalCheck(1, 'O'));
        Assert.assertEquals(0, bc.verticalCheck(2, 'X'));
    }

    @Test
    public void diagonalXCheckTest() {
        Board board = new Board();
        board.initializeTable("XOX-X-O-O");
        BoardChecker bc = new BoardChecker(board);

        Assert.assertEquals(2, bc.diagonalXCheck('X'));
        Assert.assertEquals(1, bc.diagonalXCheck('O'));
    }

    @Test
    public void diagonalYCheckTest() {
        Board board = new Board();
        board.initializeTable("--OX-XO--");
        BoardChecker bc = new BoardChecker(board);

        Assert.assertEquals(0, bc.diagonalYCheck('X'));
        Assert.assertEquals(2, bc.diagonalYCheck('O'));
    }

    @Test
    public void isXWinningTest() {
        Board board = new Board();
        board.initializeTable("XXXOO----");
        BoardChecker bc = new BoardChecker(board);

        Assert.assertTrue(bc.isWinning('X'));
        Assert.assertFalse(bc.isWinning('O'));
    }

    @Test
    public void isOWinningTest() {
        Board board = new Board();
        board.initializeTable("XXOOOXOX-");
        BoardChecker bc = new BoardChecker(board);

        Assert.assertTrue(bc.isWinning('O'));
        Assert.assertFalse(bc.isWinning('X'));
    }
}
