package ch.css.m3000.tictactoe;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest {
    @Test
    void startGameThenEmpty3x3BoardIsReturned() {
        int size = 3;
        int[][] board = Board.of(size);

        int actualWidth = new Board(board).length();

        int expectedWidth = 3;
        assertThat(actualWidth).isEqualTo(expectedWidth);
    }

    private record Board(int[][] board) {
        private static int[][] of(int size) {
            return new int[size][size];
        }

        private int length() {
            return size();
        }

        public int size() {
            return board.length;
        }
    }
}
