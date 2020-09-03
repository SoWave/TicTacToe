package tictactoe.players;

import tictactoe.Board;

import java.util.Set;

public class PlayerFactory {
    public static Set<String> types = Set.of("user", "easy", "medium", "hard");

    public static Player createPlayer(String type, Board board) {
        switch (type) {
            case "user":
                return new HumanPlayer(board);
            case "easy":
                return new EasyAI(board);
            case "medium":
                return new MediumAI(board);
            case "hard":
                return new HardAI(board);
            default:
                return null;
        }
    }
}
