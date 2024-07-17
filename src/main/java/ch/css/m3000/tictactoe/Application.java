package ch.css.m3000.tictactoe;

import ch.css.m3000.tictactoe.application.Game;
import ch.css.m3000.tictactoe.ui.TicTacToeBoardPrinter;
import ch.css.m3000.tictactoe.ui.TicTacToeMoveReader;

public class Application {
    public static void main(String[] args) {
        int boardSize = 4;
        TicTacToeBoard board = TicTacToeBoard.of(boardSize);
        TicTacToeBoardPrinter boardPrinter = new TicTacToeBoardPrinter(System.out);
        TicTacToeMoveReader moveReader = new TicTacToeMoveReader();
        Game game = new Game(board, boardPrinter, moveReader);
        game.play();
    }
}

