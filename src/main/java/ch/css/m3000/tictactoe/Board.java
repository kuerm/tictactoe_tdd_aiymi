package ch.css.m3000.tictactoe;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Arrays;
import java.util.Objects;

public final class Board {
    private static FieldState currentPlayer;
    private final FieldState[][] value;

    private Board(FieldState[][] value) {
        this.value = value;
    }

    public static Board of(int size) {
        currentPlayer = FieldState.Y;
        FieldState[][] fieldStates = new FieldState[size][size];
        for (FieldState[] fieldState : fieldStates) {
            Arrays.fill(fieldState, FieldState.EMPTY);
        }
        return new Board(fieldStates);
    }

    private static FieldState nextFieldState() {
        if (currentPlayer == FieldState.X) {
            currentPlayer = FieldState.Y;
            return currentPlayer;
        }
        currentPlayer = FieldState.X;
        return currentPlayer;
    }

    public int size() {
        return value.length;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (Board) obj;
        return Arrays.deepEquals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash((Object) value);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public void play(int x, int y) {
        if (fieldAt(x, y).state() != FieldState.EMPTY) {
            return;
        }
        setFieldState(x, y, nextFieldState());
    }

    private void setFieldState(int x, int y, FieldState fieldState) {
        value[x - 1][y - 1] = fieldState;
    }

    public Field fieldAt(int x, int y) {
        return new Field(x, y, fieldStateAt(x, y));
    }

    private FieldState fieldStateAt(int x, int y) {
        return value[x - 1][y - 1];
    }

}
