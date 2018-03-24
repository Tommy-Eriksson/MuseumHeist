package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import org.junit.jupiter.api.Test;

import handler.Level;

class LevelTests {

	@Test
	void loadLevel_ExpectLevel() throws IOException {
		Level level = new Level();
		
		String name = "test";
		String expected = "[[LMain$Cell;@182decdb";
		assertEquals(expected, level.getLevel(name).toString());

	}

	@Test
	void loadLevelCapitalAndLowerCase_ExpectLevel() throws IOException {
		Level level = new Level();
		
		String name = "test";
		String expected = "[[LMain$Cell;@182decdb";
		assertEquals(expected, level.getLevel(name).toString());		
	}
	
	@Test
	void loadWrongLevel_ExpectException() {
		Level level = new Level();
		
		String name = "wrong";

		assertThrows(FileNotFoundException.class, () -> level.getLevel(name));
	}
}
