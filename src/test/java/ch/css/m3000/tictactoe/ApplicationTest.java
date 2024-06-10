package ch.css.m3000.tictactoe;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ApplicationTest {

    private static final int DEFAULT_SIZE = 3;
    private Board sut;
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
        sut = Board.of(DEFAULT_SIZE);

        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
 
    @Test
    void playWhenMoveIsLeadingToWinThenPrintWinner() {
        sut.play(1, 1);
        sut.play(1, 2);
        sut.play(2, 1);
        sut.play(2, 2);


        sut.play(3, 1);

        assertThat(outputStream.toString()).isEqualTo("X wins");
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
    void isEndGameWhenSize4BoardAndPlayedWellThenReturnTrue() {
        sut = Board.of(4);
        int actualSize = sut.size();

        int expectedSize = 4;
        assertThat(actualSize).isEqualTo(expectedSize);
        assertThatAllFieldsMustBeEmpty(sut);

        sut.play(1, 1);
        sut.play(1, 2);
        sut.play(2, 1);
        sut.play(2, 2);
        sut.play(3, 1);
        sut.play(3, 2);
        sut.play(4, 1);

        assertThat(sut.isEndGame()).isTrue();
    }

    @Test
    void startGameThenEmpty3x3BoardIsReturned() {
        int actualSize = sut.size();

        int expectedSize = 3;
        assertThat(actualSize).isEqualTo(expectedSize);
        assertThatAllFieldsMustBeEmpty(sut);
    }

    @Test
    void firstInput1x1ThenReturnBoardWithOneXAnd8Empty() {
        sut.play(1, 1);

        Field expectedField = new Field(1, 1, FieldState.X);
        assertThat(sut.fieldAt(1, 1)).isEqualTo(expectedField);
        int expectedEmptyFields = 8;
        int actualEmptyFields = sut.countEmptyFields();
        assertThat(actualEmptyFields).isEqualTo(expectedEmptyFields);
    }

    @Test
    void secondInputWhenOnEmptyFieldThenYisOnThisField() {
        sut.play(1, 1);
        sut.play(1, 2);

        Field expectedField1 = new Field(1, 1, FieldState.X);
        assertThat(sut.fieldAt(1, 1)).isEqualTo(expectedField1);
        Field expectedField2 = new Field(1, 2, FieldState.O);
        assertThat(sut.fieldAt(1, 2)).isEqualTo(expectedField2);
        int expectedEmptyFields = 7;
        int actualEmptyFields = sut.countEmptyFields();
        assertThat(actualEmptyFields).isEqualTo(expectedEmptyFields);
    }

    @Test
    void secondInputWhenOnNoneEmptyFieldThenDoNothing() {
        sut.play(1, 1);
        sut.play(1, 1);

        Field expectedField = new Field(1, 1, FieldState.X);
        assertThat(sut.fieldAt(1, 1)).isEqualTo(expectedField);
        int expectedEmptyFields = 8;
        int actualEmptyFields = sut.countEmptyFields();
        assertThat(actualEmptyFields).isEqualTo(expectedEmptyFields);
    }

    @Test
    void fieldAtWhenEmptyBoardThenReturnAllFieldsHaveEmptyState() {
        int minimumFieldIndex = 1;
        IntStream.rangeClosed(minimumFieldIndex, DEFAULT_SIZE)
                .forEach(y -> IntStream.rangeClosed(minimumFieldIndex, DEFAULT_SIZE)
                        .forEach(x -> {
                            Field actual = sut.fieldAt(x, y);

                            assertThat(actual.state()).isEqualTo(FieldState.EMPTY);
                        }));
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

    @Test
    void playWhenCoordinatesAreInvalidThenDoNothing() {
        sut.play(0, 0);

        assertThatAllFieldsMustBeEmpty(sut);
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

    private void assertThatAllFieldsMustBeEmpty(Board board) {
        int size = board.size();
        int minimumFieldIndex = 1;
        for (int x = minimumFieldIndex; x <= size; ++x) {
            for (int y = minimumFieldIndex; y <= size; ++y) {
                assertThat(board.fieldAt(x, y).state()).isEqualTo(FieldState.EMPTY);
            }
        }
    }

}
