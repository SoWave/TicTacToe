package tictactoe.game;

import tictactoe.gameboard.Board;
import tictactoe.gameboard.BoardChecker;
import tictactoe.players.Player;
import tictactoe.players.PlayerFactory;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Game {
    public static final Scanner GAME_SCANNER = new Scanner(System.in);
    private final Board board;
    private final BoardChecker boardChecker;
    private Queue<Player> players;
    private GameState state;

    public Game() {
        this.board = new Board();
        boardChecker = new BoardChecker(board);
    }

    /**
     * Initialize the game,
     * Create AI enemy,
     * Set every field empty,
     * draw board and start a game.
     */
    public void startGame() {
        if (initializeCommand()) {
            board.drawTable();

            state = GameState.IN_GAME;
            playGame();
        }
    }

    private void playGame() {
        for (int i = 0; i < 9; i++) {
            playTurn();
            board.drawTable();

            checkStateOfGame();
            if (state != GameState.IN_GAME) {
                break;
            }
        }
    }

    private boolean initializeCommand() {
        String command;
        while (true) {
            System.out.println("Input command:");
            command = GAME_SCANNER.nextLine();

            if (command.equals("exit")) {
                return false;
            }
            if (isCommandValid(command)) {
                break;
            }
            System.out.println("Bad parameters!");
        }

        String type1 = command.split(" ")[1];
        String type2 = command.split(" ")[2];

        Player player1 = PlayerFactory.createPlayer(type1, board, 'X');
        Player player2 = PlayerFactory.createPlayer(type2, board, 'O');

        setPlayers(player1, player2);
        return true;
    }

    private boolean isCommandValid(String command) {
        String[] commandLine = command.split(" ");
        if (commandLine.length == 3) {
            return commandLine[0].equals("start")
                    && PlayerFactory.types.contains(commandLine[1])
                    && PlayerFactory.types.contains(commandLine[2]);
        }
        return false;
    }

    private void setPlayers(Player player1, Player player2) {
        this.players = new ArrayDeque<>();
        this.players.offer(player1);
        this.players.offer(player2);
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
