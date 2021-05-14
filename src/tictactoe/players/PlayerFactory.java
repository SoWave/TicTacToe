package tictactoe.players;

import tictactoe.gameboard.Board;

import java.util.Set;

public class PlayerFactory {
    public static Set<String> types = Set.of("user", "easy", "medium", "hard");

    public static Player createPlayer(String type, Board board, char sign) {
        switch (type) {
            case "user":
                return new HumanPlayer(board, sign);
            case "easy":
                return new EasyAI(board, sign);
            case "medium":
                return new MediumAI(board, sign);
            case "hard":
                return new HardAI(board, sign);
            default:
                return null;
        }
    }
}
