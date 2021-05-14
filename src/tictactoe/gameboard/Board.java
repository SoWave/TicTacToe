package tictactoe.gameboard;

import java.awt.*;

public class Board {
    private final char[][] board;
    private boolean isXTurn;
    private final BoardCrawler boardChecker;

    public Board() {
        this.board = new char[3][3];
        initializeTable("_________");
        this.isXTurn = true;
        this.boardChecker = new BoardCrawler(this);
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
            System.out.println(sb.toString());
        }
        System.out.println("---------");
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
                        changeTurn();
                        break;
                    case 'O': board[i][j] = 'O';
                        changeTurn();
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
     * then change turn of Mark
     *
     * @param coordinates (vertical, horizontal)
     */
    public void setField(Dimension coordinates) {
        char mark = isXTurn ? 'X' : 'O';
        board[coordinates.height][coordinates.width] = mark;
        changeTurn();
    }

    /**
     * Undo field.
     * Set field at coordinates to empty field.
     *
     * @param coordinates - index of the field
     */
    public void setFieldEmpty(Dimension coordinates) {
        board[coordinates.height][coordinates.width] = ' ';
        changeTurn();
    }

    /**
     * @param coordinates coordinates of field to check
     * @return is field empty
     */
    public boolean isFieldEmpty(Dimension coordinates) {
        return board[coordinates.height][coordinates.width] == ' ';
    }

    /**
     * Change turn X is true O is false
     */
    private void changeTurn() {
        this.isXTurn = !isXTurn;
    }

    /**
     * Return current turn mark
     *
     * @return current turn mark
     */
    public char getCurrentTurnMark() {
        return isXTurn ? 'X' : 'O';
    }

    /**
     * Return opposite mark
     *
     * @return opposite sign
     */
    public char getOppositeMark() {
        return  isXTurn ? 'O' : 'X';
    }

    /**
     * Method for ai to find best possible fields
     *
     * @return board
     */
    public char[][] getBoard() {
        return board;
    }

    /**
     * Return helper class that iterates in every angle and
     * checks if there are selected marks.
     *
     * @return helper class BoardCrawler
     */
    public BoardCrawler crawler() {
        return boardChecker;
    }
}
