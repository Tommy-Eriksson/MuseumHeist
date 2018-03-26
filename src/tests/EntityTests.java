package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import asset.Door;
import asset.Laser;
import asset.Player;
import asset.Treasure;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import logic.Game;

class EntityTests {

	// General Entity tests
	@Test
	void getX_createEntity_expect10() {
		Treasure e = new Treasure(10, 90, "asset/background.png", 10);
		int x = e.getX();
		assertEquals(x, 10);
	}

	@Test
	void getY_createEntity_expect90() {
		Treasure e = new Treasure(10, 90, "asset/background.png", 10);
		int y = e.getY();
		assertEquals(y, 90);
	}

	@Test
	@DisplayName("Test that the sprite string is set on creation")
	void getSprite_createEntity_expectSpriteString() {
		Treasure e = new Treasure(10, 90, "asset/background.png", 10);
		String sprite = e.getSprite();
		assertEquals(sprite, "asset/background.png");
	}

	@Test
	@DisplayName("Test that the entity is created within the bounds of witdh")
	void entity_createEntityOutsideXBounds_expectException() {

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			new Treasure(3000, 90, "asset/background.png", 10);
		});

		assertEquals("x out of bounds", exception.getMessage());
	}

	@Test
	@DisplayName("Test that the entity is creatad within the bounds of height")
	void entity_createEntityOutsideYBounds_expectException() {

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			new Treasure(80, 9000, "asset/background.png", 10);
		});

		assertEquals("y out of bounds", exception.getMessage());
	}

	// Door tests
	@Test
	void getDirection_createDoor_right() {
		Door e = new Door(10, 10, "asset/door.png", "right", false, 15);

		assertEquals("right", e.getDir());
	}

	@Test
	void getExit_createEntity_true() {
		Door e = new Door(10, 10, "asset/door.png", "right", true, 15);
		assertTrue(e.isExit());
	}

	@Test
	void getTreasuresLeft_createEntity_15() {
		Door e = new Door(10, 10, "asset/door.png", "right", false, 15);

		assertEquals(15, e.getTreasuresLeft());
	}

	@Test
	void foundTreasure_substractFromTreasuresLeft_14() {
		Door e = new Door(10, 10, "asset/door.png", "right", false, 15);
		e.foundTreasure(1);

		assertEquals(14, e.getTreasuresLeft());
	}

	@Test
	void openDoor_substractFromTreasuresLeft_openFromFalseToTrue() {
		Door e = new Door(10, 10, "asset/door.png", "right", false, 15);

		assertFalse(e.isOpen());
		e.foundTreasure(15);
		assertTrue(e.isOpen());
	}

	// Treasure tests
	@Test
	void getPoints_createTreasure_expect10() {
		Treasure e = new Treasure(10, 10, "asset/treasure.png", 10);

		assertEquals(10, e.getPoints());
	}

	// Player tests
	@Test
	void getSpeed_createRunningPlayer_expect4() {
		Player player = new Player(12, 12, "asset/player.png", 4);

		assertEquals(4, player.getSpeed());
	}

	@Test
	void getSpeed_createWalkingPlayerByDefault_expect2() {
		Player player = new Player(12, 12, "asset/player.png");

		assertEquals(2, player.getSpeed());
	}

	@Test
	void setSpeed_getPlayerRunning_expect4() {
		Player player = new Player(12, 12, "asset/player.png", 2);
		player.setSpeed(4);

		assertEquals(4, player.getSpeed());
	}

	@Test
	void movePlayer_expectX14Y14() {
		Player player = new Player(10, 10, "asset/player.png");

		player.move(14, 14);

		assertEquals(14, player.getX());
		assertEquals(14, player.getY());
	}

	// Laser tests
	@Test
	void getX_createLaser_expect10() {
		Laser laser = new Laser(10,10,"asset/laser.png","right",2,45,90,1);
		
		assertEquals(10,laser.getX());
	}
	
	@Test
	void updateLaserSpeed2_expectDegreeIncrease() throws Exception {
		new MockJavaFx().start();
		Game g = new Game();
		g.init("level1");
		Canvas c = new Canvas();
		GraphicsContext gc = c.getGraphicsContext2D();
		
		Laser laser = new Laser(300,300,"asset/laser.png","right",2,45,90,1);
		
		assertEquals(45,laser.getMaxDegree());
		laser.update(gc);
		assertEquals(45.2,laser.getMaxDegree(),0.3);
	}
	
	@Test
	void updateLaserSpeed2_expectX2Increase() throws Exception {
		new MockJavaFx().start();
		Game g = new Game();
		g.init("level1");
		Canvas c = new Canvas();
		GraphicsContext gc = c.getGraphicsContext2D();
		
		Laser laser = new Laser(300,300,"asset/laser.png","right",2,45,90,1);
		
		assertEquals(0,laser.getX2(),0.3);
		laser.update(gc);
		assertEquals(332.47009225121934,laser.getX2(),0.3);
	}
	
	@Test
	void updateLaserSpeed2_expectMaxDegreeAndMinDegreeToSwitch() throws Exception {
		new MockJavaFx().start();
		Game g = new Game();
		g.init("level1");
		Canvas c = new Canvas();
		GraphicsContext gc = c.getGraphicsContext2D();
		
		Laser laser = new Laser(300,300,"asset/laser.png","right",2,89,90,5);
		
		assertEquals(89,laser.getMaxDegree());
		assertEquals(90,laser.getMinDegree());
		laser.update(gc);
		laser.update(gc);
		assertEquals(90,laser.getMaxDegree());
		assertEquals(89,laser.getMinDegree());
	}
}
