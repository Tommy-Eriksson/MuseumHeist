package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import handler.InputHandler;
import logic.Game;

class CollisionTests {

	@Test
	void collisionRightWall_expectTrue() throws Exception {
		new MockJavaFx().start();
		InputHandler input = new InputHandler();
		Game game = new Game();
		game.init("lasertest",input);
		Collision collision = new Collision();
		
		assertTrue(collision.collide(1,0,"right"));
	}

	@Test
	void collisionRightEmptySpace_expectFalse() throws Exception {
		new MockJavaFx().start();
		InputHandler input = new InputHandler();
		Game game = new Game();
		game.init("lasertest",input);
		Collision collision = new Collision();
		
		assertFalse(collision.collide(2,0,"right"));
	}
	
	@Test
	void collisionLeftOutsideLevel_expectTrue() throws Exception {
		new MockJavaFx().start();
		InputHandler input = new InputHandler();
		Game game = new Game();
		game.init("lasertest",input);
		Collision collision = new Collision();
		
		assertTrue(collision.collide(-1,0,"left"));
	}
}
