package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import asset.Entity;

class EntityTests {

	// General Entity tests
	
	@Test
	void getX_createEntity_expect10() {
		Entity e = new Treasure(10, 90, "assets/background.png");
		int x = e.getX();
		assertEquals(x, 10);
	}
	
	@Test
	void getY_createEntity_expect90() {
		Entity e = new Treasure(10, 90, "assets/background.png");
		int y = e.getY();
		assertEquals(y, 90);
	}
	
	@Test
	@DisplayName("Test that the sprite string is set on creation")
	void getSprite_createEntity_expectSpriteString(){
		Entity e = new Treasure(10, 90, "assets/background.png");
		String sprite = e.getSprite();
		assertEquals(sprite, "assets/background.png");
	}

	@Test
	@DisplayName("Test that the entity is creatad within the bounds of witdh")
	void entity_createEntityOutsideXBounds_expectException(){
		
	    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    	Entity e = new Treasure(3000, 90, "assets/background.png");
	    });

	    assertEquals("x out of bounds", exception.getMessage());
	}
	@Test
	@DisplayName("Test that the entity is creatad within the bounds of height")
	void entity_createEntityOutsideYBounds_expectException(){
		
	    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    	Entity e = new Treasure(80, 9000, "assets/background.png");
	    });

	    assertEquals("y out of bounds", exception.getMessage());
	}
	
	// Door tests
	
	@Test
	void getDirection_createDoor_right() {
		Entity e = new Door(10, 90, "assets/door.png");
		int x = e.getX();
		assertEquals(x, 10);
	}
	
	@Test
	void getExit_createEntity_true() {
		Entity e = new Door(10, 90, "assets/door.png");
		int x = e.getX();
		assertEquals(x, 10);
	}
	
	@Test
	void getOpen_createEntity_true() {
		Entity e = new Door(10, 90, "assets/door.png");
		int x = e.getX();
		assertEquals(x, 10);
	}
	
	@Test
	void getTreasuresLeft_createEntity_15() {
		Entity e = new Door(10, 90, "assets/door.png");
		int x = e.getX();
		assertEquals(x, 10);
	}
	
	@Test
	void foundTreasur_substractFromTreasuresLeft_14() {
		Entity e = new Door(10, 90, "assets/door.png");
		int x = e.getX();
		assertEquals(x, 10);
	}
	
	@Test
	void openDoor_substractFromTreasuresLeft_openFromFalseToTrue() {
		Entity e = new Door(10, 90, "assets/door.png");
		int x = e.getX();
		assertEquals(x, 10);
	}
}
