package tictactoe.players;

import tictactoe.Board;

import java.awt.*;
import java.util.ArrayList;

public class HardAI extends AIPlayer {
    private char mySign;
    private char enemySign;

    public HardAI(Board board) {
        super(board, "hard");
        this.mySign = ' ';
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
        setMarks();

        int bestScore = -1000;
        Dimension move = new Dimension();
        for (Dimension spot: availableSpots) {
            board.putMark(spot);
            int score = miniMaxAlgorithm(board, 0, false);

            // undo
            board.setFieldEmpty(spot);
            if (score > bestScore) {
                bestScore = score;
                move = spot;
            }
        }

        return move;
    }

    /**
     * MiniMax algorithm.
     * Check every possible move and count score for them.
     * Score is counted when terminal state is up - maximizing player
     * wins, minimizing player wins or there are no empty fields left and round
     * ends with a tie.
     * Score is sum of terminal state and depth - higher depth -> lower score.
     *
     * If is maximizing player count max score else count min score.
     *
     * @param board - board to use algorithm on
     * @param depth - current depth of recursion
     * @param isMaximizing - is maximizing player round
     * @return - score of field
     */
    private int miniMaxAlgorithm(Board board, int depth, boolean isMaximizing) {
        // get every available fields
        ArrayList<Dimension> availableSpots = getEmptySpots(board);

        // terminal states
        if (board.crawler().isWinning(mySign)) {
            return 10 - depth;
        } else if (board.crawler().isWinning(enemySign)) {
            return -10 + depth;
        } else if (availableSpots.size() == 0) {
            return 0;
        }

        // is maximizing one round count max else count min
        // place sign in available spot count score then undo move
        int bestScore;
        if (isMaximizing) {
            bestScore = -1000;
            for (Dimension spot: availableSpots) {
                board.putMark(spot);
                int score = miniMaxAlgorithm(board, depth + 1, !isMaximizing);

                board.setFieldEmpty(spot);
                bestScore = Math.max(bestScore, score);
            }
        } else {
            bestScore = 1000;
            for (Dimension spot: availableSpots) {
                board.putMark(spot);
                int score = miniMaxAlgorithm(board, depth + 1, !isMaximizing);

                board.setFieldEmpty(spot);
                bestScore = Math.min(bestScore, score);
            }
        }
        return bestScore;
    }

    /**
     * Get every available field
     *
     * @param board - board to search in
     * @return - list of empty fields
     */
    private ArrayList<Dimension> getEmptySpots(Board board) {
        ArrayList<Dimension> emptySpots = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getBoard()[i][j] == ' ') {
                    emptySpots.add(new Dimension(j, i));
                }
            }
        }
        return emptySpots;
    }

    /**
     * Set marks if not yet saved
     */
    private void setMarks() {
        if (mySign == ' ') {
            mySign = board.getCurrentTurnMark();
            enemySign = board.getOppositeMark();
        }
    }
}
