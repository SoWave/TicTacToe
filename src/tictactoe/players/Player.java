package tictactoe.players;

import java.awt.*;

public interface Player {
    void makeMove();

    Dimension findField();
}
