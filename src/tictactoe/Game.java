package tictactoe;

import tictactoe.players.Player;
import tictactoe.players.PlayerFactory;

import java.util.*;

public class Game {
    private final Board board;
    private Queue<Player> players;

    public Game() {
        this.board = new Board();
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

    /**
     * TicTacToe game
     * Play 9 rounds or until somebody won
     * In case of draw print message
     */
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

    /**
     * Asks user for command,
     * Type "exit" if he wants to quit,
     * If he wants to play command must start with "start",
     * second and third word decides who plays.
     * Finally set players to game.
     *
     * @return does user want to continue or exit
     */
    private boolean initializeCommand() {
        String command;
        while (true) {
            System.out.println("Input command:");
            command = InputScanner.getInstance().getScanner().nextLine();

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

    /**
     * Checks user input,does have 3 words, starts with "start",
     * second and third word have to be defined as type of player.
     *
     * @param command user input
     * @return is command valid
     */
    private boolean isCommandValid(String command) {
        Set<String> typesOfPlayers = Set.of("user", "easy", "medium", "hard");

        String[] commandLine = command.split(" ");
        if (commandLine.length == 3) {
            return commandLine[0].equals("start")
                    && typesOfPlayers.contains(commandLine[1])
                    && typesOfPlayers.contains(commandLine[2]);
        }
        return false;
    }

    /**
     * Set players
     * @param player1 player X
     * @param player2 player O
     */
    private void setPlayers(Player player1, Player player2) {
        this.players = new ArrayDeque<>();
        this.players.offer(player1);
        this.players.offer(player2);
    }

    /**
     * Get first player from queue, make move and then
     * move him at end of queue
     */
    private void playTurn() {
        Player currentPlayer = this.players.poll();
        currentPlayer.makeMove();
        this.players.offer(currentPlayer);
    }

    /**
     * Look for 3 same marks in every row
     * Print winner
     *
     * @return does game ended
     */
    private boolean checkStateOfGame() {
        boolean inGame = true;
        boolean xWins = board.crawler().isWinning('X');
        boolean oWins = board.crawler().isWinning('O');

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
