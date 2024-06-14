package ch.css.m3000.tictactoe.ui;

import ch.css.m3000.tictactoe.service.Board;

import java.io.PrintStream;

public interface BoardPrinter {
    void print(PrintStream printStream, Board board);

    void print(PrintStream printStream, String text);
}