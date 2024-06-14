package ch.css.m3000.tictactoe.application;

import ch.css.m3000.tictactoe.domain.Coordinates;
import ch.css.m3000.tictactoe.service.Board;
import ch.css.m3000.tictactoe.service.CoordinateParser;
import ch.css.m3000.tictactoe.ui.BoardPrinter;
import ch.css.m3000.tictactoe.ui.MoveReader;

public class Game {
    private final Board board;
    private final BoardPrinter boardPrinter;
    private final MoveReader moveReader;

    public Game(Board board, BoardPrinter boardPrinter, MoveReader moveReader) {
        this.board = board;
        this.boardPrinter = boardPrinter;
        this.moveReader = moveReader;
    }

    public void play() {
        while (!board.isEndGame()) {
            boardPrinter.print(System.out, board);
            boardPrinter.print(System.out, "Please enter your move 'x, y': ");
            String input = moveReader.readMove();
            boardPrinter.print(System.out, input);
            Coordinates coordinates = CoordinateParser.parseCoordinates(input);
            board.play(coordinates.x(), coordinates.y());
        }

        boardPrinter.print(System.out, board);
    }

}
