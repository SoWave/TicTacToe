package tictactoe;

import tictactoe.gameboard.Board;
import tictactoe.gameboard.BoardChecker;
import tictactoe.players.Player;
import tictactoe.players.PlayerFactory;
import java.util.*;

public class Game {
    public static final Scanner GAME_SCANNER = new Scanner(System.in);
    private final Board board;
    private final BoardChecker boardChecker;
    private Queue<Player> players;

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

            playGame();
        }
    }

    private void playGame() {
        boolean inGame = true;
        for (int i = 0; i < 9; i++) {
            playTurn();

            inGame = checkStateOfGame();
            if (!inGame) {
                break;
            }
        }

        if (inGame) {
            System.out.println("Draw");
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

        Player player1 = PlayerFactory.createPlayer(type1, board);
        Player player2 = PlayerFactory.createPlayer(type2, board);

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
        currentPlayer.makeMove();
        this.players.offer(currentPlayer);
    }

    private boolean checkStateOfGame() {
        boolean inGame = true;
        boolean xWins = boardChecker.isWinning('X');
        boolean oWins = boardChecker.isWinning('O');

        if (xWins) {
            System.out.println("X wins");
            inGame = false;
        } else if (oWins) {
            System.out.println("O wins");
            inGame = false;
        }

        return inGame;
    }
}
