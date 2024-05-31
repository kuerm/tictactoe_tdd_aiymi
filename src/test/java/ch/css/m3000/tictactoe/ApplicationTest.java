package ch.css.m3000.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest {

    private static final int DEFAULT_SIZE = 3;
    private Board sut;

    @BeforeEach
    void setup() {
        sut = Board.of(DEFAULT_SIZE);
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
        int actualEmptyFields = countEmptyFields(sut);
        assertThat(actualEmptyFields).isEqualTo(expectedEmptyFields);
    }

    @Test
    void secondInputWhenOnEmptyFieldThenYisOnThisField() {
        sut.play(1, 1);
        sut.play(1, 2);

        Field expectedField1 = new Field(1, 1, FieldState.X);
        assertThat(sut.fieldAt(1, 1)).isEqualTo(expectedField1);
        Field expectedField2 = new Field(1, 2, FieldState.Y);
        assertThat(sut.fieldAt(1, 2)).isEqualTo(expectedField2);
        int expectedEmptyFields = 7;
        int actualEmptyFields = countEmptyFields(sut);
        assertThat(actualEmptyFields).isEqualTo(expectedEmptyFields);
    }

    @Test
    void secondInputWhenOnNoneEmptyFieldThenDoNothing() {
        sut.play(1, 1);
        sut.play(1, 1);

        Field expectedField = new Field(1, 1, FieldState.X);
        assertThat(sut.fieldAt(1, 1)).isEqualTo(expectedField);
        int expectedEmptyFields = 8;
        int actualEmptyFields = countEmptyFields(sut);
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

    private int countEmptyFields(Board board) {
        int actualEmptyFields = 0;
        int x = 1;
        while (x <= board.size()) {
            int y = 1;
            while (y <= board.size()) {
                actualEmptyFields += board.fieldAt(x, y).state().equals(FieldState.EMPTY) ? 1 : 0;
                ++y;
            }
            ++x;
        }
        return actualEmptyFields;
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
