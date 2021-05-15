package tictactoe.players;

import tictactoe.gameboard.Board;

import java.util.Set;

public class PlayerFactory {
    /**
     * Set of valid player types
     */
    public static Set<String> types = Set.of("user", "easy", "medium", "hard");

    /**
     * Create player with given type and sign. Link to specified board.
     * @param type type of player
     * @param board board that player operates on
     * @param sign sign that player puts on board
     * @return player, if type is mismatched return null
     */
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
