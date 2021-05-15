package tictactoe.game;

import tictactoe.gameboard.Board;
import tictactoe.gameboard.BoardChecker;
import tictactoe.players.Player;
import tictactoe.ui.CommandLineUI;

import java.util.Queue;

public class Game {
    private final BoardChecker boardChecker;
    private final CommandLineUI ui;
    private Queue<Player> players;
    private GameState state;

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
            System.out.println("X wins");
            state = GameState.XWIN;
        } else if (boardChecker.isWinning('O')) {
            System.out.println("O wins");
            state = GameState.OWIN;
        } else if (boardChecker.getEmptySpots().size() == 0) {
            System.out.println("Draw");
        }
    }
}
