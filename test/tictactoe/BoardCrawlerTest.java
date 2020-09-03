package tictactoe;

import org.junit.Assert;
import org.junit.Test;

public class BoardCrawlerTest {
    @Test
    public void horizontalCheckTest() {
        Board board = new Board();
        board.initializeTable("_XX_OO_-_");
        BoardCrawler bc = new BoardCrawler(board);

        Assert.assertEquals(2, bc.horizontalCheck(0, 'X'));
        Assert.assertEquals(2, bc.horizontalCheck(1, 'O'));
        Assert.assertEquals(0, bc.horizontalCheck(2, 'X'));
    }

    @Test
    public void verticalCheckTest() {
        Board board = new Board();
        board.initializeTable("XO_-O_-X-");
        BoardCrawler bc = new BoardCrawler(board);

        Assert.assertEquals(1, bc.verticalCheck(0, 'X'));
        Assert.assertEquals(2, bc.verticalCheck(1, 'O'));
        Assert.assertEquals(0, bc.verticalCheck(2, 'X'));
    }

    @Test
    public void diagonalXCheckTest() {
        Board board = new Board();
        board.initializeTable("XOX_X_O_O");
        BoardCrawler bc = new BoardCrawler(board);

        Assert.assertEquals(2, bc.diagonalXCheck('X'));
        Assert.assertEquals(1, bc.diagonalXCheck('O'));
    }

    @Test
    public void diagonalYCheckTest() {
        Board board = new Board();
        board.initializeTable("_-OX_XO-_");
        BoardCrawler bc = new BoardCrawler(board);

        Assert.assertEquals(0, bc.diagonalYCheck('X'));
        Assert.assertEquals(2, bc.diagonalYCheck('O'));
    }

    @Test
    public void isXWinningTest() {
        Board board = new Board();
        board.initializeTable("XXXOO_-_-");
        BoardCrawler bc = new BoardCrawler(board);

        Assert.assertTrue(bc.isWinning('X'));
        Assert.assertFalse(bc.isWinning('O'));
    }

    @Test
    public void isOWinningTest() {
        Board board = new Board();
        board.initializeTable("XXOOOXOX-");
        BoardCrawler bc = new BoardCrawler(board);

        Assert.assertTrue(bc.isWinning('O'));
        Assert.assertFalse(bc.isWinning('X'));
    }
}
