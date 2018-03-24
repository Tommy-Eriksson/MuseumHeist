package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import asset.Entity;

class EntityTests {

	@Test
	void getX_createEntity_expect10() {
		Entity e = new Entity(10, 90, "assets/background.png");
		int x = e.getX();
		assertEquals(x, 10);
	}
	@Test
	void getY_createEntity_expect90() {
		Entity e = new Entity(10, 90, "assets/background.png");
		int y = e.getY();
		assertEquals(y, 90);
	}
	
	@Test
	@DisplayName("Test that the sprite string is set on creation")
	void getSprite_createEntity_expectSpriteString(){
		Entity e = new Entity(10, 90, "assets/background.png");
		String sprite = e.getSprite();
		assertEquals(sprite, "assets/background.png");
	}

	@Test
	@DisplayName("Test that the entity is creatad within the bounds of witdh")
	void entity_createEntityOutsideXBounds_expectException(){
		
	    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    	Entity e = new Entity(3000, 90, "assets/background.png");
	    });

	    assertEquals("x out of bounds", exception.getMessage());
	}
	@Test
	@DisplayName("Test that the entity is creatad within the bounds of height")
	void entity_createEntityOutsideYBounds_expectException(){
		
	    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	    	Entity e = new Entity(80, 9000, "assets/background.png");
	    });

	    assertEquals("y out of bounds", exception.getMessage());
	}
}
