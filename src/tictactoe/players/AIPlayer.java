package tictactoe.players;

import tictactoe.gameboard.Board;

import java.awt.*;
import java.util.Random;

public abstract class AIPlayer implements Player {
    protected final Board board;
    protected final Random random;
    private final String type;

    public AIPlayer(Board board, String type) {
        this.board = board;
        this.random = new Random();
        this.type = type;
    }

    /**
     * Place mark,
     * Print board
     */
    @Override
    public void makeMove() {
        System.out.println("Making move level \""+ type +"\"");
        Dimension coordinates = findField();

        board.putMark(coordinates);
        board.drawTable();
    }

    /**
     * Get random non occupied coordinates
     *
     * @return random coordinates
     */
    protected Dimension getRandomCoordinates() {
        Dimension coordinates;
        do {
            coordinates = new Dimension(random.nextInt(3), random.nextInt(3));

        } while (!board.isFieldEmpty(coordinates));

        return coordinates;
    }

    /**
     * Find different ways to get coordinates according to difficulty
     *
     * @return best coordinates according to difficulty
     */
    @Override
    public abstract Dimension findField();
}
