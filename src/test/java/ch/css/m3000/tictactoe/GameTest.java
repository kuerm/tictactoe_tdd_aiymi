package ch.css.m3000.tictactoe;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameTest {
    private PrintStream originalOut;
    private ByteArrayOutputStream temporaryOutputStream;

    @Mock
    private GameBoardReader boardReaderMock;

    @BeforeEach
    void setup() {
        originalOut = System.out;
        temporaryOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(temporaryOutputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void startGame() {
        GameBoard gameBoard = Board.of(3);
        GameBoardPrinter gameBoardPrinter = new PrinterAdapter();
        when(boardReaderMock.readMove()).thenReturn("1,1").thenReturn("1,2").thenReturn("2,1").thenReturn("2,2").thenReturn("3,1");
        Game sut = new Game(gameBoard, gameBoardPrinter, boardReaderMock);

        sut.startGame();

        String expectedOutput = """
                -------------
                | X | O |   |
                -------------
                | X | O |   |
                -------------
                | X |   |   |
                -------------
                Player X wins""";
        String actual = temporaryOutputStream.toString();
        String croppedForLastOutput = actual.substring(actual.lastIndexOf(":") + 8).trim();
        assertThat(croppedForLastOutput).isEqualTo(expectedOutput);
    }
}