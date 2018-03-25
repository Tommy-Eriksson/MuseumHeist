package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import asset.Door;
import asset.Treasure;

class EntityTests {

	// General Entity tests
	@Test
	void getX_createEntity_expect10() {
		Treasure e = new Treasure(10, 90, "assets/background.png", 10);
		int x = e.getX();
		assertEquals(x, 10);
	}
	
	@Test
	void getY_createEntity_expect90() {
		Treasure e = new Treasure(10, 90, "assets/background.png", 10);
		int y = e.getY();
		assertEquals(y, 90);
	}
	
	@Test
	@DisplayName("Test that the sprite string is set on creation")
	void getSprite_createEntity_expectSpriteString(){
		Treasure e = new Treasure(10, 90, "assets/background.png", 10);
		String sprite = e.getSprite();
		assertEquals(sprite, "assets/background.png");
	}

	@Test
	@DisplayName("Test that the entity is creatad within the bounds of witdh")
	void entity_createEntityOutsideXBounds_expectException(){
		
	    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    	new Treasure(3000, 90, "assets/background.png", 10);
	    });

	    assertEquals("x out of bounds", exception.getMessage());
	}
	@Test
	@DisplayName("Test that the entity is creatad within the bounds of height")
	void entity_createEntityOutsideYBounds_expectException(){
		
	    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    	new Treasure(80, 9000, "assets/background.png", 10);
	    });

	    assertEquals("y out of bounds", exception.getMessage());
	}
	
	// Door tests
	@Test
	void getDirection_createDoor_right() {
		Door e = new Door(10, 10, "assets/door.png", "right", false, 15);
		
		assertEquals("right", e.getDir());
	}
	
	@Test
	void getExit_createEntity_true() {
		Door e = new Door(10, 10, "assets/door.png", "right", true, 15);
		assertTrue(e.isExit());
	}
	
	@Test
	void getTreasuresLeft_createEntity_15() {
		Door e = new Door(10, 10, "assets/door.png", "right", false, 15);

		assertEquals(15, e.getTreasuresLeft());
	}
	
	@Test
	void foundTreasure_substractFromTreasuresLeft_14() {
		Door e = new Door(10, 10, "assets/door.png", "right", false, 15);
		e.foundTreasure(1);
		
		assertEquals(14, e.getTreasuresLeft());
	}
	
	@Test
	void openDoor_substractFromTreasuresLeft_openFromFalseToTrue() {
		Door e = new Door(10, 10, "assets/door.png", "right", false, 15);

		assertFalse(e.isOpen());
		e.foundTreasure(15);
		assertTrue(e.isOpen());
	}
	
	// Treasure tests
	@Test
	void getPoints_createTreasure_expect10() {
		Treasure e = new Treasure(10, 10, "assets/treasure.png", 10);
		
		assertEquals(10, e.getPoints());
	}
}
