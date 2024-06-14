package ch.css.m3000.tictactoe.ui;

import ch.css.m3000.tictactoe.service.Board;

import java.io.PrintStream;

public class TicTacToeBoardPrinter implements BoardPrinter {

    private final PrintStream printStream;

    public TicTacToeBoardPrinter(PrintStream printStream) {

        this.printStream = printStream;
    }

    @Override
    public void print(Board board) {
        printStream.println(board);
    }

    @Override
    public void print(String text) {
        printStream.println(text);
    }
}
