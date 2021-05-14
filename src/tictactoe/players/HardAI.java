package tictactoe.players;

import tictactoe.gameboard.Board;

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
            board.setField(spot);
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

    private int miniMaxAlgorithm(Board board, int depth, boolean isMaximizing) {
        ArrayList<Dimension> availableSpots = getEmptySpots(board);

        if (board.crawler().isWinning(mySign)) {
            return 10 - depth;
        } else if (board.crawler().isWinning(enemySign)) {
            return -10 + depth;
        } else if (availableSpots.size() == 0) {
            return 0;
        }

        int bestScore;
        if (isMaximizing) {
            bestScore = -1000;
            for (Dimension spot: availableSpots) {
                board.setField(spot);
                int score = miniMaxAlgorithm(board, depth + 1, !isMaximizing);

                board.setFieldEmpty(spot);
                bestScore = Math.max(bestScore, score);
            }
        } else {
            bestScore = 1000;
            for (Dimension spot: availableSpots) {
                board.setField(spot);
                int score = miniMaxAlgorithm(board, depth + 1, !isMaximizing);

                board.setFieldEmpty(spot);
                bestScore = Math.min(bestScore, score);
            }
        }
        return bestScore;
    }

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

    private void setMarks() {
        if (mySign == ' ') {
            mySign = board.getCurrentTurnMark();
            enemySign = board.getOppositeMark();
        }
    }
}
