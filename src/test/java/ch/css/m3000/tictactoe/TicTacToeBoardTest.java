package ch.css.m3000.tictactoe;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TicTacToeBoardTest {
    @Test
    void toStringWhenBoardIsEmptyThenReturnEmptyStructureWithoutWinner() {
        TicTacToeBoard sut = TicTacToeBoard.of(3);

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
        TicTacToeBoard sut = TicTacToeBoard.of(3);
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
        TicTacToeBoard sut = TicTacToeBoard.of(3);
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