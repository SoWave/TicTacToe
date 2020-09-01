package tictactoe;

import tictactoe.Board;

public class BoardCrawler {
    private final Board board;

    public BoardCrawler(Board board) {
        this.board = board;
    }

    /**
     * Counts amount of selected mark in a selected row.
     *
     * @param verticalIndex height index of checked row
     * @param mark selected sign
     * @return amount of signs found in row
     */
    public int horizontalCheck(int verticalIndex, char mark) {
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            if (board.getBoard()[verticalIndex][i] == mark) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Counts amount of selected mark in a selected column.
     *
     * @param horizontalIndex width index of checked column
     * @param mark selected sign
     * @return amount of signs found in column
     */
    public int verticalCheck(int horizontalIndex, char mark) {
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            if (board.getBoard()[i][horizontalIndex] == mark) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Counts amount of selected mark on diagonal x.
     * Diagonal X:
     * x
     *   x
     *     x
     *
     * @param mark selected mark
     * @return amount of signs found on diagonal
     */
    public int diagonalXCheck(char mark) {
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            if (board.getBoard()[i][i] == mark) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Counts amount of selected mark on diagonal y.
     * Diagonal Y:
     *     y
     *   y
     * y
     *
     * @param mark selected mark
     * @return amount of signs found on diagonal
     */
    public int diagonalYCheck(char mark) {
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            if (board.getBoard()[i][2 - i] == mark) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Wrap every angle method.
     * Checks every row horizontally, vertically and diagonally,
     * if there are 3 marks in any row
     *
     * @param mark sign to look for
     * @return if there are 3 marks in any row
     */
    public boolean isWinning(char mark) {
        for (int i = 0; i < 3; i++) {
            if (board.crawler().horizontalCheck(i, mark) == 3
                    || board.crawler().verticalCheck(i, mark) == 3) {
                return true;
            }
        }

        return board.crawler().diagonalXCheck(mark) == 3
                || board.crawler().diagonalYCheck(mark) == 3;
    }
}
