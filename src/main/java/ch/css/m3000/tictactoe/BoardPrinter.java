package ch.css.m3000.tictactoe;

import java.io.PrintStream;

public class BoardPrinter implements GameBoardPrinter {

    @Override
    public void print(PrintStream printStream, GameBoard gameBoard) {
        printStream.println(gameBoard);
    }
}
