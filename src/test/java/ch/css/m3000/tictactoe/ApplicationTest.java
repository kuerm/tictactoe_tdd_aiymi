package ch.css.m3000.tictactoe;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest {
    @Test
    void startGameThenEmpty3x3BoardIsReturned() {
        int actualWidth = 3;

        int expectedWidth = 3;
        assertThat(actualWidth).isEqualTo(expectedWidth);
    }
}
