package ch.css.m3000.tictactoe;

public interface GameBoard {
    int size();

    void play(int x, int y);

    boolean isEndGame();
}