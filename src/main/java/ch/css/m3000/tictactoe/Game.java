package ch.css.m3000.tictactoe;

import java.util.Scanner;

public class Game {
    private final GameBoard gameBoard;
    private final GameBoardPrinter gameBoardPrinter;

    public Game(GameBoard gameBoard, GameBoardPrinter gameBoardPrinter) {
        this.gameBoard = gameBoard;
        this.gameBoardPrinter = gameBoardPrinter;
    }

    public static void main(String[] args) {
        Game game = new Game(Board.of(3), new BoardPrinter());
        game.startGame();
    }

    public void startGame() {
        while (!gameBoard.isEndGame()) {
            gameBoardPrinter.print(System.out, gameBoard);

            System.out.println("Please enter your move 'x, y': ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] coordinates = input.split(",");
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);
            gameBoard.play(x, y);
        }

        if (gameBoard.isEndGame()) {
            gameBoardPrinter.print(System.out, gameBoard);
        }
    }
}
