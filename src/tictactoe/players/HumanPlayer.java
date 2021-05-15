package tictactoe.players;

import tictactoe.gameboard.Board;
import tictactoe.ui.CommandLineUI;

import java.awt.*;

/**
 * Human player that uses CLI to enter coordinates
 */
public class HumanPlayer implements Player {
    private final Board board;
    private final char sign;

    public HumanPlayer(Board board, char sign)
    {
        this.board = board;
        this.sign = sign;
    }

    /**
     * Put mark on valid field
     */
    @Override
    public void makeMove() {
        Dimension coordinates = findField();
        board.setField(coordinates, sign);
    }

    /**
     * Ask user for coordinates,
     * Check is input valid - have two digits with space between them,
     * digits are in range 1 - 3 and field is not occupied
     *
     * Valid examples:
     * <ul>
     *     <li>1 3</li>
     *     <li>2 2</li>
     *     <li>3 2</li>
     * </ul>
     * Invalid examples:
     * <ul>
     *     <li>0 1</li>
     *     <li>4 2</li>
     *     <li>a 2</li>
     *     <li>11</li>
     *     <li>1||2</li>
     * </ul>
     *
     * @return correct coordinates
     */
    @Override
    public Dimension findField() {
        boolean fieldFound = false;
        Dimension coordinates = new Dimension();

        while (!fieldFound) {
            System.out.println("Enter the coordinates:");
            String input = CommandLineUI.INPUT_SCANNER.nextLine();

            if (input.matches("\\d\\s\\d")) {
                String[] index = input.split(" ");
                int yIndex = Integer.parseInt(index[0]) - 1; // Input translation from 1 to 0
                int xIndex = Integer.parseInt(index[1]) - 1;

                if (xIndex > 2 || yIndex > 2) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    coordinates = new Dimension(xIndex, yIndex);

                    if (board.isFieldEmpty(coordinates)) {
                        fieldFound = true;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                }
            } else {
                System.out.println("You should enter numbers!");
            }
        }

        return coordinates;
    }
}
