package ch.css.m3000.tictactoe;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest {
    @Test
    void startGameThenEmpty3x3BoardIsReturned() {
        int size = 3;
        Board board = Board.of(size);

        int actualSize = board.length();

        int expectedSize = 3;
        assertThat(actualSize).isEqualTo(expectedSize);
        Field expectedField = Field.EMPTY;
        for (int x = 1; x <= 3; ++x) {
            for (int y = 1; y <= 3; ++y) {
                assertThat(board.fieldAt(x, y)).isEqualTo(expectedField);
            }
        }
    }

    private enum Field {
        EMPTY
    }

    private static final class Board {
        private final Field[][] value;

        private Board(Field[][] value) {
            this.value = value;
        }

        private static Board of(int size) {
            return new Board(new Field[size][size]);
        }

        private int length() {
            return size();
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
            return Objects.hash(value);
        }

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this);
        }

        public Field fieldAt(int x, int y) {
            return Field.EMPTY;
        }
    }
}
