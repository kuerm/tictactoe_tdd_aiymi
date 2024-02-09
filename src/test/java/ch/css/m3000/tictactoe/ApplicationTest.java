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

        int actualWidth = board.length();

        int expectedWidth = 3;
        assertThat(actualWidth).isEqualTo(expectedWidth);
    }

    private static final class Board {
        private final int[][] value;

        private Board(int[][] value) {
            this.value = value;
        }

        private static Board of(int size) {
            return new Board(new int[size][size]);
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

    }
}
