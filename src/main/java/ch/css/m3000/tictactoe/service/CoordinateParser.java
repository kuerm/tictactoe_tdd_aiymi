package ch.css.m3000.tictactoe.service;

import ch.css.m3000.tictactoe.domain.Coordinates;

public class CoordinateParser {
    public static Coordinates parseCoordinates(String input) {
        String[] coordinates = input.split(",");
        int x = Integer.parseInt(coordinates[0].trim());
        int y = Integer.parseInt(coordinates[1].trim());
        return new Coordinates(x, y);
    }
}
