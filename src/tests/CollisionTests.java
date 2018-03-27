package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import handler.InputHandler;
import logic.Collision;
import logic.Game;
import settings.Settings;

class CollisionTests {

	@Test
	void collisionRightWall_expectTrue() throws Exception {
		new MockJavaFx().start();
		InputHandler input = new InputHandler();
		Game game = new Game();
		game.init("lasertest",input);
		Collision collision = new Collision();
		
		assertTrue(collision.collide(24,0));
	}

	@Test
	void collisionRightEmptySpace_expectFalse() throws Exception {
		new MockJavaFx().start();
		InputHandler input = new InputHandler();
		Game game = new Game();
		game.init("lasertest",input);
		Collision collision = new Collision();
		
		assertFalse(collision.collide(1+Settings.getOffsetX(),0+Settings.getHeight())); // TODO FIX the test coords is wrong
	}
	
	@Test
	void collisionLeftOutsideLevel_expectTrue() throws Exception {
		new MockJavaFx().start();
		InputHandler input = new InputHandler();
		Game game = new Game();
		game.init("lasertest",input);
		Collision collision = new Collision();
		
		assertTrue(collision.collide(0,0));
	}
}
