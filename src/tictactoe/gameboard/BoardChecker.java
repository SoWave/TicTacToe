package tictactoe.gameboard;

import java.awt.*;
import java.util.ArrayList;

public class BoardChecker {
    private final Board board;

    public BoardChecker(Board board) {
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
            if (board.at(verticalIndex, i) == mark) {
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
            if (board.at(i, horizontalIndex) == mark) {
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
            if (board.at(i, i) == mark) {
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
            if (board.at(i, 2 - i) == mark) {
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
            if (horizontalCheck(i, mark) == 3
                    || verticalCheck(i, mark) == 3) {
                return true;
            }
        }

        return diagonalXCheck(mark) == 3
                || diagonalYCheck(mark) == 3;
    }

    public ArrayList<Dimension> getEmptySpots() {
        ArrayList<Dimension> emptySpots = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.at(i, j) == ' ') {
                    emptySpots.add(new Dimension(j, i));
                }
            }
        }
        return emptySpots;
    }
}
