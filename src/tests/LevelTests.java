package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import handler.Level;

class LevelTests {

	@Test
	void loadLevel_ExpectLevel() throws IOException {
		Level level = new Level();
		
		assertEquals(25, level.getLevel("test").length);
	}

	@Test
	void loadLevelCapitalAndLowerCase_ExpectLevel() throws IOException {
		Level level = new Level();
		
		assertEquals(25, level.getLevel("test").length);		
	}
	
	@Test
	void loadWrongLevel_ExpectException() {
		Level level = new Level();

		assertThrows(FileNotFoundException.class, () -> level.getLevel("Wrong"));
	}
}
