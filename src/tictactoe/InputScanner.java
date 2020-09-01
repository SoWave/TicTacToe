package tictactoe;

import java.util.Scanner;

public class InputScanner {
    public static InputScanner INSTANCE;
    private final Scanner scanner;

    private InputScanner() {
        scanner = new Scanner(System.in);
    }

    public static InputScanner getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InputScanner();
        }
        return INSTANCE;
    }

    public Scanner getScanner() {
        return scanner;
    }
}
