package ch.css.m3000.tictactoe;

import ch.css.m3000.tictactoe.domain.FieldState;
import ch.css.m3000.tictactoe.service.Board;

import java.util.Arrays;
import java.util.Objects;

public final class TicTacToeBoard implements Board {
    private static FieldState currentPlayer;
    private final FieldState[][] fieldStates;

    private TicTacToeBoard(FieldState[][] fieldStates) {
        this.fieldStates = fieldStates;
    }

    public static TicTacToeBoard of(int size) {
        currentPlayer = FieldState.O;
        FieldState[][] fieldStates = new FieldState[size][size];
        initializeEmptyBoard(fieldStates);
        return new TicTacToeBoard(fieldStates);
    }

    private static void initializeEmptyBoard(FieldState[][] fieldStates) {
        Arrays.stream(fieldStates).forEach(fieldState -> Arrays.fill(fieldState, FieldState.EMPTY));
    }

    public boolean isEndGame() {
        return countEmptyFields() == 0 || isColumnStraight() || isRowStraight() || isDiagonalStraight() || isDiagonal2Straight();
    }

    private boolean isDiagonal2Straight() {
        int size = size();
        FieldState firstFieldInDiagonal = fieldStateAt(size, 1);
        if (firstFieldInDiagonal != FieldState.EMPTY) {
            boolean isStraight = true;
            for (int i = 2; i <= size; ++i) {
                if (fieldStateAt(size - i + 1, i) != firstFieldInDiagonal) {
                    isStraight = false;
                    break;
                }
            }
            return isStraight;
        }
        return false;
    }

    private boolean isRowStraight() {
        int size = size();
        for (int y = 1; y <= size; ++y) {
            FieldState firstFieldInRow = fieldStateAt(1, y);
            if (firstFieldInRow == FieldState.EMPTY) {
                continue;
            }
            boolean isStraight = true;
            for (int x = 2; x <= size; ++x) {
                if (fieldStateAt(x, y) != firstFieldInRow) {
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

    private boolean isDiagonalStraight() {
        int size = size();
        FieldState firstFieldInDiagonal = fieldStateAt(1, 1);
        if (firstFieldInDiagonal != FieldState.EMPTY) {
            boolean isStraight = true;
            for (int i = 2; i <= size; ++i) {
                if (fieldStateAt(i, i) != firstFieldInDiagonal) {
                    isStraight = false;
                    break;
                }
            }
            return isStraight;
        }
        return false;
    }

    private boolean isColumnStraight() {
        int size = size();
        for (int x = 1; x <= size; ++x) {
            FieldState firstFieldInColumn = fieldStateAt(x, 1);
            if (firstFieldInColumn != FieldState.EMPTY) {
                boolean isStraight = true;
                for (int y = 2; y <= size; ++y) {
                    if (fieldStateAt(x, y) != firstFieldInColumn) {
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
            currentPlayer = FieldState.O;
            return currentPlayer;
        }
        currentPlayer = FieldState.X;
        return currentPlayer;
    }

    private int size() {
        return fieldStates.length;
    }

    @Override
    public void play(int x, int y) {
        if (isEndGame()) {
            throw new IllegalStateException("Game has already ended");
        }
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
        return fieldStateAt(x, y) != FieldState.EMPTY;
    }

    private void fillPlayedFieldState(int x, int y) {
        fieldStates[x - 1][y - 1] = nextFieldState();
    }

    private FieldState fieldStateAt(int x, int y) {
        return fieldStates[x - 1][y - 1];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (TicTacToeBoard) obj;
        return Arrays.deepEquals(this.fieldStates, that.fieldStates);
    }

    private int countEmptyFields() {
        int actualEmptyFields = 0;
        int x = 1;
        while (x <= size()) {
            int y = 1;
            while (y <= size()) {
                actualEmptyFields += fieldStateAt(x, y).equals(FieldState.EMPTY) ? 1 : 0;
                ++y;
            }
            ++x;
        }
        return actualEmptyFields;
    }

    @Override
    public int hashCode() {
        return Objects.hash((Object) fieldStates);
    }

    @Override
    public String toString() {
        return getString(currentPlayer, size(), isEndGame());
    }

    private String getString(FieldState currentFieldState, int boardSize, boolean isEndGame) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-------------\n");
        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                stringBuilder.append("| %s ".formatted(fieldStateAt(x + 1, y + 1)));
            }
            stringBuilder.append("|\n");
            stringBuilder.append("-------------\n");
        }
        if (isEndGame) {
            String winner = currentFieldState.equals(FieldState.X) ? "X" : "O";
            stringBuilder.append("Player %s wins\n".formatted(winner));
        }
        return stringBuilder.toString();
    }
}
