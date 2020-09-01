package tictactoe.players;

import tictactoe.Board;

import java.awt.*;

public class EasyAI extends AIPlayer {

    public EasyAI(Board board) {
        super(board, "easy");
    }

    /**
     * Find random coordinates
     * If in current row are 2 X find different field.
     * Try it three times
     *
     * @return valid coordinates
     */
    @Override
    public Dimension findField() {
        Dimension coordinates = null;

        for (int i = 0; i < 3; i++) {
            coordinates = getRandomCoordinates();
            if (isItBadMove(coordinates)) {
                break;
            }
        }
        return coordinates;
    }

    /**
     * Check are coordinates are winning move or are blocking enemy,
     * return true if they are considered bad move
     *
     * @param coordinates current coordinates to check
     * @return if there are 2 marks in row
     */
    private boolean isItBadMove(Dimension coordinates) {
        boolean isWinningMove = checkCoordinatesFor2SameMarks(coordinates, board.getCurrentTurnMark());
        boolean isBlocking = checkCoordinatesFor2SameMarks(
                coordinates, board.getOppositeMark());

        return !(isWinningMove || isBlocking);
    }

    /**
     * Checks row and column in way of coordinates.
     * If coordinates are on diagonal check diagonals too.
     *
     * @param coordinates current coordinates
     * @param mark sign to look for
     * @return if there are two marks in row
     */
    private boolean checkCoordinatesFor2SameMarks(Dimension coordinates, char mark) {
        if (board.crawler().horizontalCheck(coordinates.height, mark) == 2
                || board.crawler().verticalCheck(coordinates.width, mark) == 2) {
            return true;
        }

        // checks are coordinates lays on diagonals
        if (coordinates.height + coordinates.width % 2 == 0) {
            return board.crawler().diagonalXCheck(mark) == 2
                    || board.crawler().diagonalYCheck(mark) == 2;
        }

        return false;
    }
}
