package ch.css.m3000.tictactoe;

public enum FieldState {
    // better naming? FieldColor, Color, Type, PlayerColor, PlayerType?
    X, O, EMPTY;

    @Override
    public String toString() {
        return name().equals("EMPTY") ? " " : name().substring(0, 1);
    }
}
