package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import javax.naming.SizeLimitExceededException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import asset.Entity;

class TileTests {

	@Test
	void getSize_createTile_expect24() {
		Tile e = new Entity("assets/wall.png",24);
		int x = e.getSize();
		assertEquals(x, 24);
	}

	@Test
	void getX_createTileWithNegativ_expectException() {
		assertThrows(SizeLimitExceededException.class, () -> Tile e = new Entity("assets/wall.png",-5));
	}
	
	@Test
	@DisplayName("Test that the sprite string is set on creation")
	void getSprite_createTile_expectSpriteString(){
		Tile e = new Entity(10, 90, "assets/wall.png");
		String sprite = e.getSprite();
		assertEquals(sprite, "assets/wall.png");
	}
}
