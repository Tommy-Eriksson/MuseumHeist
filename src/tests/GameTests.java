package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;
import logic.Game;

class GameTests {
	
	@Test
	void getPane_ExpectPane() throws Exception {
		new MockJavaFx().start();
		Game game = new Game();
		assertEquals("javafx.scene.layout.Pane",game.init("test").getClass().getName());
	}

	@Test
	void getPaneLevelNotFound_Exception() throws FileNotFoundException {
		Game game = new Game();
		assertThrows(FileNotFoundException.class, () -> game.init("wrong"));
	}
	
	@Test
	void getLevel_ExpectObjectArray() throws Exception {
		new MockJavaFx().start();
		Game game = new Game();
		game.init("test");
		Class<?> gameClass = game.getClass();
		
	    Field level = gameClass.getDeclaredField("level");
		level.setAccessible(true);
	    
		Object[][] gameLevel = (Object[][]) level.get(game);
	    assertEquals(25,gameLevel.length);
	}
	
	@Test
	void constructorTest() {
		Game game = new Game();
		
		assertNotNull(game);
	}
	
	@Test
	void startGameLoop_ExpectAnimationTimerToStart() {
		Game game = new Game();
		game.start();
		assertTrue(game.getGameLoopStatus());
	}
	
	@Test
	void stopGameLoop_ExpectAnimationTimerToStop() {
		Game game = new Game();
		game.start();
		game.stop();
		assertFalse(game.getGameLoopStatus());
	}
}
