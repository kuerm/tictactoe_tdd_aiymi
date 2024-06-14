package ch.css.m3000.tictactoe.acceptancetest;

import ch.css.m3000.tictactoe.TicTacToeBoard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class TicTacToeBoardTest {

    private static final int DEFAULT_SIZE = 3;
    private TicTacToeBoard sut;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    static Stream<Arguments> provideEndGameScenarios() {
        return Stream.of(
                Arguments.of(new int[][]{{1, 1}, {2, 1}, {1, 2}, {2, 3}, {1, 3}}, true), // FirstRowIsStraight
                Arguments.of(new int[][]{{2, 1}, {3, 1}, {2, 2}, {1, 2}, {2, 3}}, true), // SecondRowIsStraight
                Arguments.of(new int[][]{{1, 1}, {1, 2}, {2, 1}, {1, 3}, {3, 1}}, true), // FirstColumnIsStraight
                Arguments.of(new int[][]{{1, 2}, {2, 2}, {1, 1}, {2, 1}, {1, 3}}, true), // SecondColumnIsStraight
                Arguments.of(new int[][]{{1, 1}, {2, 1}, {2, 2}, {1, 2}, {3, 3}}, true), // MainDiagonalIsStraight
                Arguments.of(new int[][]{{3, 1}, {2, 1}, {2, 2}, {3, 2}, {1, 3}}, true), // SecondDiagonalIsStraight
                Arguments.of(new int[][]{{1, 3}}, false) // NoRowIsStraight
        );
    }

    @BeforeEach
    void setup() {
        sut = TicTacToeBoard.of(DEFAULT_SIZE);

        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void playWhenGameIsEndedThenThrowException() {
        sut.play(1, 1);
        sut.play(1, 2);
        sut.play(2, 1);
        sut.play(2, 2);
        sut.play(3, 1);

        assertThatExceptionOfType(IllegalStateException.class).isThrownBy(() -> sut.play(3, 2));
    }


    @Test
    void isEndGameWhenAllFieldsAreFilledThenReturnTrue() {
        sut.play(1, 1);
        sut.play(1, 2);
        sut.play(1, 3);
        sut.play(2, 1);
        sut.play(2, 2);
        sut.play(2, 3);
        sut.play(3, 2);
        sut.play(3, 1);
        sut.play(3, 3);

        boolean actual = sut.isEndGame();

        assertThat(actual).isTrue();
    }

    @Test
    void isEndGameWhenNotAllFieldsAreFilledThenReturnFalse() {
        sut.play(1, 3);

        boolean actual = sut.isEndGame();

        assertThat(actual).isFalse();
    }


    @ParameterizedTest
    @MethodSource("provideEndGameScenarios")
    void isEndGameWhen(int[][] moves, boolean expectedEndGame) {
        for (int[] move : moves) {
            sut.play(move[0], move[1]);
        }

        boolean actual = sut.isEndGame();

        assertThat(actual).isEqualTo(expectedEndGame);
    }
}
