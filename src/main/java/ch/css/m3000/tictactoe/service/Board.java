package ch.css.m3000.tictactoe.service;

public interface Board {
    void play(int x, int y);

    boolean isEndGame();
}