package ch.css.m3000.tictactoe.ui;

import ch.css.m3000.tictactoe.service.Board;

import java.io.PrintStream;

public class TicTacToeBoardBrinter implements BoardPrinter {

    @Override
    public void print(PrintStream printStream, Board board) {
        printStream.println(board);
    }

    @Override
    public void print(PrintStream printStream, String text) {
        printStream.println(text);
    }
}
