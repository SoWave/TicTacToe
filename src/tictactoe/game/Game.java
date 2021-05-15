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
     * Initialize the game,
     * Create AI enemy,
     * Set every field empty,
     * draw board and start a game.
     */
    public void startGame() {
        if (ui.setup()) {
            players = ui.getPlayers();

            ui.drawTable();

            state = GameState.IN_GAME;
            playGame();
        }
    }

    private void playGame() {
        for (int i = 0; i < 9; i++) {
            playTurn();

            ui.drawTable();

            checkStateOfGame();
            if (state != GameState.IN_GAME) {
                break;
            }
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
