package tictactoe.ui;

import tictactoe.gameboard.Board;
import tictactoe.players.Player;
import tictactoe.players.PlayerFactory;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class CommandLineUI {
    public static final Scanner INPUT_SCANNER = new Scanner(System.in);
    private String command;
    private final Board board;

    public CommandLineUI(Board board) {
        this.board = board;
    }

    public Queue<Player> setup() {
        while (true) {
            System.out.println("Input command:");
            command = INPUT_SCANNER.nextLine();

            if (command.equals("exit")) {
                return null;
            }
            if (isCommandValid()) {
                break;
            }
            System.out.println("Bad parameters!");
        }
        return getPlayers();
    }

    private Queue<Player> getPlayers() {
        if (command == null) { throw new IllegalStateException("Command not set"); }

        String type1 = command.split(" ")[1];
        String type2 = command.split(" ")[2];

        Queue<Player> players = new ArrayDeque<>();
        players.offer(PlayerFactory.createPlayer(type1, board, 'X'));
        players.offer(PlayerFactory.createPlayer(type2, board, 'O'));

        return players;
    }

    /**
     * Draw current state of table
     */
    public void drawTable() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("| ");
            for (int j = 0; j < 3; j++) {
                sb.append(board.at(i, j)).append(" ");
            }
            sb.append("|");
            System.out.println(sb);
        }
        System.out.println("---------");
    }

    private boolean isCommandValid() {
        String[] commandLine = command.split(" ");
        if (commandLine.length == 3) {
            return commandLine[0].equals("start")
                    && PlayerFactory.types.contains(commandLine[1])
                    && PlayerFactory.types.contains(commandLine[2]);
        }
        return false;
    }
}
