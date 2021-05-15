package tictactoe.game;

import tictactoe.gameboard.Board;
import tictactoe.gameboard.BoardChecker;
import tictactoe.players.Player;
import tictactoe.ui.CommandLineUI;

import java.util.Queue;

/**
 * Game creates board and initializes UI.
 * After invoking start function perform setup and starts the game of TicTacToe till someone wins
 * or it's a draw.
 */
public class Game {
    private final BoardChecker boardChecker;
    private final CommandLineUI ui;
    private Queue<Player> players;
    private GameState state;

    /**
     * Creates empty board.
     * Initializes helper BoardChecker to check board.
     * Initializes UI to setup players.
     */
    public Game() {
        Board board = new Board();
        boardChecker = new BoardChecker(board);
        ui = new CommandLineUI(board);
    }

    /**
     * Setup the game:
     * Setup players, if players equals null, that indicates user want to exit.
     * draw board, set state to IN_GAME  and start the game.
     * Continue until game state changes from IN_GAME.
     */
    public void startGame() {
        players = ui.setup();

        if (players == null) { return; }

        ui.displayBoard();

        state = GameState.IN_GAME;

        while (state == GameState.IN_GAME) {
            playTurn();

            ui.displayBoard();

            checkStateOfGame();
        }

        ui.endMessage(state);
    }

    private void playTurn() {
        Player currentPlayer = this.players.poll();
        if (currentPlayer != null) {
            currentPlayer.makeMove();
            this.players.offer(currentPlayer);
        }
    }

    private void checkStateOfGame() {
        if (boardChecker.isWinning('X')) {
            state = GameState.XWIN;
        } else if (boardChecker.isWinning('O')) {
            state = GameState.OWIN;
        } else if (boardChecker.getEmptySpots().size() == 0) {
            state = GameState.DRAW;
        }
    }
}
