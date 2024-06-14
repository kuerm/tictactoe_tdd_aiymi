package ch.css.m3000.tictactoe.ui;

import ch.css.m3000.tictactoe.service.Board;

public interface BoardPrinter {
    void print(Board board);

    void print(String text);
}