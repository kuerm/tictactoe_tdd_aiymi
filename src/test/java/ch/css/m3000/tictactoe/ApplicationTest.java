package ch.css.m3000.tictactoe;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest {
    @Test
    void startGameThenEmpty3x3BoardIsReturned() {
        int size = 3;
        int[][] board = createBoard(size);

        int actualWidth = length(new Board(board));

        int expectedWidth = 3;
        assertThat(actualWidth).isEqualTo(expectedWidth);
    }

    private int length(Board board) {
        return board.board().length;
    }

    private int[][] createBoard(int size) {
        return new int[size][size];
    }

    private static record Board(int[][] board) {
    }
}
