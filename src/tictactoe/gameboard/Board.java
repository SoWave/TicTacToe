package tictactoe.gameboard;

import java.awt.*;

public class Board {
    private final char[][] board;

    public Board() {
        this.board = new char[3][3];
        initializeTable("_________");
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
                sb.append(board[i][j]).append(" ");
            }
            sb.append("|");
            System.out.println(sb);
        }
        System.out.println("---------");
    }

    public char at(int height, int width) {
        return board[height][width];
    }

    /**
     * Fill board with Marks.
     * _ means empty field.
     * If X or O is inserted change turn of Mark.
     * Order of insertion 123456789
     *    | 1 2 3 |
     *    | 4 5 6 |
     *    | 7 8 9 |
     *
     * @param line sequence of chars (X, O, _)
     */
    public void initializeTable(String line) {
        if (line.length() != 9) {
            System.out.println("Bad data");
            return;
        }

        int indexCounter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char val = line.charAt(indexCounter++);
                switch (val) {
                    case 'X': board[i][j] = 'X';
                        break;
                    case 'O': board[i][j] = 'O';
                        break;
                    default: board[i][j] = ' ';
                        break;
                }
            }
        }
    }

    /**
     * Check which one's turn is
     * then insert mark to field
     *
     * @param coordinates (vertical, horizontal)
     * @param sign sign to place
     */
    public void setField(Dimension coordinates, char sign) {
        board[coordinates.height][coordinates.width] = sign;
    }

    /**
     * @param coordinates coordinates of field to check
     * @return is field empty
     */
    public boolean isFieldEmpty(Dimension coordinates) {
        return board[coordinates.height][coordinates.width] == ' ';
    }
}
