package ch.css.m3000.tictactoe;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {
    @Test
    void toStringWhenBoardIsEmptyThenReturnEmptyStructureWithoutWinner() {
        Board sut = Board.of(3);

        assertThat(sut.toString()).isEqualTo("""
                -------------
                |   |   |   |
                -------------
                |   |   |   |
                -------------
                |   |   |   |
                -------------
                """);
    }

    @Test
    void toStringWhenBoardIsPlayedWithoutWinnerThenReturnStructureWithoutWinner() {
        Board sut = Board.of(3);
        sut.play(1, 1);
        sut.play(2, 2);

        assertThat(sut.toString()).isEqualTo("""
                -------------
                | X |   |   |
                -------------
                |   | O |   |
                -------------
                |   |   |   |
                -------------
                """);
    }

    @Test
    void toStringWhenBoardIsPlayedWithWinnerThenReturnStructureWithWinner() {
        Board sut = Board.of(3);
        sut.play(1, 1);
        sut.play(2, 2);
        sut.play(1, 2);
        sut.play(2, 1);
        sut.play(1, 3);

        assertThat(sut.toString()).isEqualTo("""
                -------------
                | X | X | X |
                -------------
                | O | O |   |
                -------------
                |   |   |   |
                -------------
                Player X wins
                """);
    }

}