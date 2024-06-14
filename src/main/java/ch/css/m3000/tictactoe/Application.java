package ch.css.m3000.tictactoe;

public class Application {
    public static void main(String[] args) {
        Game game = new Game(Board.of(3), new PrinterAdapter(), new BoardReader());
        game.startGame();
    }
}

