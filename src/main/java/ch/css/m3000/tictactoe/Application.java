package ch.css.m3000.tictactoe;

import ch.css.m3000.tictactoe.application.Game;
import ch.css.m3000.tictactoe.ui.TicTacToeBoardPrinter;
import ch.css.m3000.tictactoe.ui.TicTacToeMoveReader;

public class Application {
    public static void main(String[] args) {
        Game game = new Game(TicTacToeBoard.of(3), new TicTacToeBoardPrinter(System.out), new TicTacToeMoveReader());
        game.play();
    }
}

