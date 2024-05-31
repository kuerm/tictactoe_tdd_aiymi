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

    public boolean isEndGame() {
        return countEmptyFields() == 0 || isColumnStraight() || isRowStraight() || isDiagonalStraight() || isDiagonal2Straight();
    }

    private boolean isDiagonalStraight() {
        int size = size();
        FieldState firstFieldInDiagonal = fieldAt(1, 1).state();
        if (firstFieldInDiagonal != FieldState.EMPTY) {
            boolean isStraight = true;
            for (int i = 2; i <= size; ++i) {
                if (fieldAt(i, i).state() != firstFieldInDiagonal) {
                    isStraight = false;
                    break;
                }
            }
            if (isStraight) {
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonal2Straight() {
        int size = size();
        FieldState firstFieldInDiagonal = fieldAt(size, 1).state();
        if (firstFieldInDiagonal != FieldState.EMPTY) {
            boolean isStraight = true;
            for (int i = 2; i <= size; ++i) {
                if (fieldAt(size - i + 1, i).state() != firstFieldInDiagonal) {
                    isStraight = false;
                    break;
                }
            }
            if (isStraight) {
                return true;
            }
        }
        return false;
    }

    private boolean isRowStraight() {
        int size = size();
        for (int y = 1; y <= size; ++y) {
            FieldState firstFieldInRow = fieldAt(1, y).state();
            if (firstFieldInRow != FieldState.EMPTY) {
                boolean isStraight = true;
                for (int x = 2; x <= size; ++x) {
                    if (fieldAt(x, y).state() != firstFieldInRow) {
                        isStraight = false;
                        break;
                    }
                }
                if (isStraight) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isColumnStraight() {
        int size = size();
        for (int x = 1; x <= size; ++x) {
            FieldState firstFieldInColumn = fieldAt(x, 1).state();
            if (firstFieldInColumn != FieldState.EMPTY) {
                boolean isStraight = true;
                for (int y = 2; y <= size; ++y) {
                    if (fieldAt(x, y).state() != firstFieldInColumn) {
                        isStraight = false;
                        break;
                    }
                }
                if (isStraight) {
                    return true;
                }
            }
        }
        return false;
    }

    private FieldState nextFieldState() {
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

    public void play(int x, int y) {
        if (isOutOfBounds(x, y) || isNotEmpty(x, y)) {
            return;
        }
        fillPlayedFieldState(x, y);
    }

    private boolean isOutOfBounds(int x, int y) {
        int size = size();
        return x < 1 || x > size || y < 1 || y > size;
    }

    private boolean isNotEmpty(int x, int y) {
        return fieldAt(x, y).state() != FieldState.EMPTY;
    }

    private void fillPlayedFieldState(int x, int y) {
        value[x - 1][y - 1] = nextFieldState();
    }

    public Field fieldAt(int x, int y) {
        return new Field(x, y, fieldStateAt(x, y));
    }

    private FieldState fieldStateAt(int x, int y) {
        return value[x - 1][y - 1];
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

    int countEmptyFields() {
        int actualEmptyFields = 0;
        int x = 1;
        while (x <= size()) {
            int y = 1;
            while (y <= size()) {
                actualEmptyFields += fieldAt(x, y).state().equals(FieldState.EMPTY) ? 1 : 0;
                ++y;
            }
            ++x;
        }
        return actualEmptyFields;
    }
}
