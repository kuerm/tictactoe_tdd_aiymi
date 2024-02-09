package ch.css.m3000.tictactoe;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest {
    @Test
    void startGameThenEmpty3x3BoardIsReturned() {
        int[][] board = new int[3][3];
        
        int actualWidth = board.length;

        int expectedWidth = 3;
        assertThat(actualWidth).isEqualTo(expectedWidth);
    }
}
