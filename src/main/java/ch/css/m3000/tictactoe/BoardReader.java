package ch.css.m3000.tictactoe;

import java.util.Scanner;

public class BoardReader implements GameBoardReader {
    private final Scanner scanner = new Scanner(System.in);

    public BoardReader() {
    }

    @Override
    public String readMove() {
        return scanner.nextLine();
    }
}