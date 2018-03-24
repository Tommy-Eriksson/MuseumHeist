package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import javafx.scene.layout.Pane;
import logic.Game;

class GameTests {

	@Test
	void getPane_ExpectPane() {
		Game game = new Game();
		Pane root = new Pane();
		assertEqual(root, game.init());
	}

	@Test
	void getLevel_ExpectObjectArray() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Game game = new Game();
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
