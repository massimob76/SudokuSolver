package userInteraction;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import userInteraction.InteractiveGame;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class InteractiveGameTest {
	
	private InteractiveGame iut;
	private String rowString = "12-45-78-";
	
	@Before
	public void setUp() {
		iut = new InteractiveGame();
	}
	
	@Test
	public void getUnsolvedGameAcceptsAnUnsolvedGameTypedFromTheStandardInput() throws IOException {
		BufferedReader readerMock = mock(BufferedReader.class);
		ReflectionTestUtils.setField(iut, "reader", readerMock);
		when(readerMock.readLine()).thenReturn(rowString);
		String[] expected = {rowString,rowString,rowString,rowString,rowString,rowString,rowString,rowString,rowString};
		String[] actual = iut.getUnsolvedGameAsStringArray();
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void getSolutionReturnsNull() {
		assertNull(iut.getSolutionAsStringArray());
	}
	
}
