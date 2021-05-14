package tictactoe.players;

import tictactoe.gameboard.Board;

import java.awt.*;
import java.util.ArrayList;

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
        ArrayList<Dimension> availableSpots = getEmptySpots(board);
        // set marks to process in algorithm

        int bestScore = -1000;
        Dimension move = new Dimension();
        for (Dimension spot: availableSpots) {
            board.setField(spot, sign);
            int score = miniMaxAlgorithm(board, 0, false);

            // undo
            board.setField(spot, ' ');
            if (score > bestScore) {
                bestScore = score;
                move = spot;
            }
        }

        return move;
    }

    private int miniMaxAlgorithm(Board board, int depth, boolean isMaximizing) {
        ArrayList<Dimension> availableSpots = getEmptySpots(board);

        if (boardChecker.isWinning(sign)) {
            return 10 - depth;
        } else if (boardChecker.isWinning(enemySign)) {
            return -10 + depth;
        } else if (availableSpots.size() == 0) {
            return 0;
        }

        int bestScore;
        if (isMaximizing) {
            bestScore = -1000;
            for (Dimension spot: availableSpots) {
                board.setField(spot, sign);
                int score = miniMaxAlgorithm(board, depth + 1, false);

                board.setField(spot, ' ');
                bestScore = Math.max(bestScore, score);
            }
        } else {
            bestScore = 1000;
            for (Dimension spot: availableSpots) {
                board.setField(spot, enemySign);
                int score = miniMaxAlgorithm(board, depth + 1, true);

                board.setField(spot, ' ');
                bestScore = Math.min(bestScore, score);
            }
        }
        return bestScore;
    }

    private ArrayList<Dimension> getEmptySpots(Board board) {
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
