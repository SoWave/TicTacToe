package tictactoe.players;

import tictactoe.gameboard.Board;

import java.awt.*;

public class MediumAI extends AIPlayer {

    public MediumAI(Board board) {
        super(board, "medium");
    }

    /**
     * Look for winning move.
     * If not found look for blocking move.
     * If both winning and blocking move was not found return random coordinates.
     *
     * @return best available coordinates
     */
    @Override
    public Dimension findField() {
        Dimension coordinates = findGoodCoordinates(board.getCurrentTurnMark());
        if (coordinates == null) {
            coordinates = findGoodCoordinates(board.getOppositeMark());
        }

        return coordinates == null ? getRandomCoordinates() : coordinates;
    }

    private Dimension findGoodCoordinates(char mark) {
        for (int i = 0; i < 3; i++) {
            if (board.crawler().horizontalCheck(i, mark) == 2) {
                return searchRowForFreeHorizontalSpot(i);
            }
            if (board.crawler().verticalCheck(i, mark) == 2) {
                return searchRowForFreeVerticalSpot(i);
            }
        }

        if (board.crawler().diagonalXCheck(mark) == 2) {
            return searchRowForFreeXDiagonalSpot();
        }
        if (board.crawler().diagonalYCheck(mark) == 2) {
            return searchRowForFreeYDiagonalSpot();
        }

        return null;
    }

    private Dimension searchRowForFreeHorizontalSpot(int verticalIndex) {
        for (int i = 0; i < 3; i++) {
            if (board.isFieldEmpty(new Dimension(i, verticalIndex))) {
                return new Dimension(i, verticalIndex);
            }
        }
        return null;
    }

    private Dimension searchRowForFreeVerticalSpot(int horizontalIndex) {
        for (int i = 0; i < 3; i++) {
            if (board.isFieldEmpty(new Dimension(horizontalIndex, i))) {
                return new Dimension(horizontalIndex, i);
            }
        }
        return null;
    }

    private Dimension searchRowForFreeXDiagonalSpot() {
        for (int i = 0; i < 3; i++) {
            if (board.isFieldEmpty(new Dimension(i, i))) {
                return new Dimension(i, i);
            }
        }
        return null;
    }

    private Dimension searchRowForFreeYDiagonalSpot() {
        for (int i = 0; i < 3; i++) {
            if (board.isFieldEmpty(new Dimension(2 - i, i))) {
                return new Dimension(2 - i, i);
            }
        }
        return null;
    }
}
