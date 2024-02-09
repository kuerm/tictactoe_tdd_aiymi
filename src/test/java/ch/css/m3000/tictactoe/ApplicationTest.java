package ch.css.m3000.tictactoe;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest {

    private static final int DEFAULT_SIZE = 3;
    private static FieldState currentPlayer = FieldState.Y;

    private static FieldState nextFieldState() {
        if (currentPlayer == FieldState.X) {
            currentPlayer = FieldState.Y;
            return currentPlayer;
        }
        currentPlayer = FieldState.X;
        return currentPlayer;
    }

    @Test
    void startGameThenEmpty3x3BoardIsReturned() {
        Board sut = Board.of(DEFAULT_SIZE);

        int actualSize = sut.size();

        int expectedSize = 3;
        assertThat(actualSize).isEqualTo(expectedSize);
        assertThatAllFieldsMustBeEmpty(sut);
    }

    @Test
    void firstInput1x1ThenReturnBoardWithOneXAnd8Empty() {
        Board sut = Board.of(DEFAULT_SIZE);

        sut.play(1, 1);

        Field expectedField = new Field(1, 1, FieldState.X);
        assertThat(sut.fieldAt(1, 1)).isEqualTo(expectedField);
        int expectedEmptyFields = 8;
        int actualEmptyFields = countEmptyFields(sut);
        assertThat(actualEmptyFields).isEqualTo(expectedEmptyFields);
    }

    @Test
    void secondInputWhenOnEmptyFieldThenYisOnThisField() {
        Board sut = Board.of(DEFAULT_SIZE);

        sut.play(1, 1);
        sut.play(1, 2);

        Field expectedField1 = new Field(1, 1, FieldState.X);
        assertThat(sut.fieldAt(1, 1)).isEqualTo(expectedField1);
        Field expectedField2 = new Field(1, 2, FieldState.Y);
        assertThat(sut.fieldAt(1, 2)).isEqualTo(expectedField2);
        int expectedEmptyFields = 7;
        int actualEmptyFields = countEmptyFields(sut);
        assertThat(actualEmptyFields).isEqualTo(expectedEmptyFields);
    }

    @Test
    void secondInputWhenOnNoneEmptyFieldThenDoNothing() {
        Board sut = Board.of(DEFAULT_SIZE);

        sut.play(1, 1);
        sut.play(1, 1);

        Field expectedField = new Field(1, 1, FieldState.X);
        assertThat(sut.fieldAt(1, 1)).isEqualTo(expectedField);
        int expectedEmptyFields = 8;
        int actualEmptyFields = countEmptyFields(sut);
        assertThat(actualEmptyFields).isEqualTo(expectedEmptyFields);
    }

    @Test
    void fieldAtWhenEmptyBoardThenReturnFieldWithEmptyState() {
        Board sut = Board.of(DEFAULT_SIZE);

        Field actual = sut.fieldAt(1, 1);

        assertThat(actual.state()).isEqualTo(FieldState.EMPTY);
        assertThat(actual.x()).isEqualTo(1);
        assertThat(actual.y()).isEqualTo(1);
    }

    private int countEmptyFields(Board board) {
        int actualEmptyFields = 0;
        int x = 1;
        while (x <= board.size()) {
            int y = 1;
            while (y <= board.size()) {
                actualEmptyFields += board.fieldAt(x, y).state().equals(FieldState.EMPTY) ? 1 : 0;
                ++y;
            }
            ++x;
        }
        return actualEmptyFields;
    }

    private void assertThatAllFieldsMustBeEmpty(Board board) {
        int size = board.size();
        int minimumFieldIndex = 1;
        for (int x = minimumFieldIndex; x <= size; ++x) {
            for (int y = minimumFieldIndex; y <= size; ++y) {
                assertThat(board.fieldAt(x, y).state()).isEqualTo(FieldState.EMPTY);
            }
        }
    }

    private enum FieldState {
        X, Y, EMPTY
    }

    private static final class Board {
        private final FieldState[][] value;

        private Board(FieldState[][] value) {
            this.value = value;
        }

        private static Board of(int size) {
            FieldState[][] fieldStates = new FieldState[size][size];
            for (FieldState[] fieldState : fieldStates) {
                Arrays.fill(fieldState, FieldState.EMPTY);
            }
            return new Board(fieldStates);
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
            return ReflectionToStringBuilder.toString(this);
        }

        public void play(int x, int y) {
            value[x - 1][y - 1] = nextFieldState();
        }

        public Field fieldAt(int x, int y) {
            return new Field(x, y, value[x - 1][y - 1]);
        }
    }

    private record Field(int x, int y, FieldState state) {
    }
}
