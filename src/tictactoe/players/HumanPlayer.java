package tictactoe.players;

import tictactoe.Board;
import tictactoe.InputScanner;

import java.awt.*;

public class HumanPlayer implements Player {
    private final Board board;

    public HumanPlayer(Board board) {
        this.board = board;
    }

    /**
     * Put mark on valid field
     */
    @Override
    public void makeMove() {
        Dimension coordinates = findField();
        board.putMark(coordinates);
        board.drawTable();
    }

    /**
     * Ask user for coordinates,
     * Check is input valid - have two digits with space between them,
     * digits are in range 1 - 3 and field is not occupied
     *
     * @return correct coordinates
     */
    @Override
    public Dimension findField() {
        boolean fieldFound = false;
        Dimension coordinates = new Dimension();

        while (!fieldFound) {
            System.out.println("Enter the coordinates:");
            String input = InputScanner.getInstance().getScanner().nextLine();

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
