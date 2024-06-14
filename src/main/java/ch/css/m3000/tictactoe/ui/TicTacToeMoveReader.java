package ch.css.m3000.tictactoe.ui;

import java.util.Scanner;

public class TicTacToeMoveReader implements MoveReader {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String readMove() {
        return scanner.nextLine();
    }
}