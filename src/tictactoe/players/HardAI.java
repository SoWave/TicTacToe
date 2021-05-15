package tictactoe.players;

import tictactoe.gameboard.Board;

import java.awt.*;
import java.util.ArrayList;

/**
 * HardAI class that uses MinMax Algorithm to never loose
 */
public class HardAI extends AIPlayer {

    public HardAI(Board board, char sign) {
        super(board, "hard", sign);
    }

    /**
     * Find best possible move using minimax algorithm.
     * Check every field, count score of every move then return
     * move with best score.
     *
     * @return best possible move coordinates
     */
    @Override
    public Dimension findField() {
        ArrayList<Dimension> availableSpots = boardChecker.getEmptySpots();
        // set marks to process in algorithm

        int bestScore = -1000;
        Dimension move = new Dimension();
        for (Dimension spot: availableSpots) {
            board.setField(spot, sign);
            int score = miniMaxAlgorithm(false);

            // undo
            board.setField(spot, ' ');
            if (score > bestScore) {
                bestScore = score;
                move = spot;
            }
        }

        return move;
    }

    private int miniMaxAlgorithm(boolean isMaximizing) {
        ArrayList<Dimension> availableSpots = boardChecker.getEmptySpots();

        if (boardChecker.isWinning(sign)) {
            return 10;
        } else if (boardChecker.isWinning(enemySign)) {
            return -10;
        } else if (availableSpots.size() == 0) {
            return 0;
        }

        int bestScore;
        if (isMaximizing) {
            bestScore = -1000;
            for (Dimension spot: availableSpots) {
                board.setField(spot, sign);
                int score = miniMaxAlgorithm(false);

                board.setField(spot, ' ');
                bestScore = Math.max(bestScore, score);
            }
        } else {
            bestScore = 1000;
            for (Dimension spot: availableSpots) {
                board.setField(spot, enemySign);
                int score = miniMaxAlgorithm(true);

                board.setField(spot, ' ');
                bestScore = Math.min(bestScore, score);
            }
        }
        return bestScore;
    }
}
