package tictactoe.players;

import tictactoe.gameboard.Board;

import java.awt.*;

public class EasyAI extends AIPlayer {

    public EasyAI(Board board) {
        super(board, "easy");
    }

    /**
     * Find random coordinates
     * If in current row are 2 X find different field.
     * Try it five times
     *
     * @return valid coordinates
     */
    @Override
    public Dimension findField() {
        Dimension coordinates = null;

        for (int i = 0; i < 5; i++) {
            coordinates = getRandomCoordinates();
            if (isItBadMove(coordinates)) {
                break;
            }
        }
        return coordinates;
    }

    private boolean isItBadMove(Dimension coordinates) {
        boolean isWinningMove = checkCoordinatesFor2SameMarks(coordinates, board.getCurrentTurnMark());
        boolean isBlocking = checkCoordinatesFor2SameMarks(
                coordinates, board.getOppositeMark());

        return !(isWinningMove || isBlocking);
    }

    private boolean checkCoordinatesFor2SameMarks(Dimension coordinates, char mark) {
        if (board.crawler().horizontalCheck(coordinates.height, mark) == 2
                || board.crawler().verticalCheck(coordinates.width, mark) == 2) {
            return true;
        }

        if (coordinates.height + coordinates.width % 2 == 0) {
            return board.crawler().diagonalXCheck(mark) == 2
                    || board.crawler().diagonalYCheck(mark) == 2;
        }

        return false;
    }
}
