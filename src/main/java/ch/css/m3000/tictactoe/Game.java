package ch.css.m3000.tictactoe;

public class Game {
    private final GameBoard gameBoard;
    private final GameBoardPrinter gameBoardPrinter;
    private final GameBoardReader gameBoardReader;

    public Game(GameBoard gameBoard, GameBoardPrinter gameBoardPrinter, GameBoardReader gameBoardReader) {
        this.gameBoard = gameBoard;
        this.gameBoardPrinter = gameBoardPrinter;
        this.gameBoardReader = gameBoardReader;
    }

    public void startGame() {
        while (!gameBoard.isEndGame()) {
            gameBoardPrinter.print(System.out, gameBoard);

            System.out.println("Please enter your move 'x, y': ");
            String input = gameBoardReader.readMove();
            System.out.println(input);
            String[] coordinates = input.split(",");
            int x = Integer.parseInt(coordinates[0].trim());
            int y = Integer.parseInt(coordinates[1].trim());
            gameBoard.play(x, y);
        }

        if (gameBoard.isEndGame()) {
            gameBoardPrinter.print(System.out, gameBoard);
        }
    }
}
