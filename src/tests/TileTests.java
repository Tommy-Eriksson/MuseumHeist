package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import javax.naming.SizeLimitExceededException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import asset.Entity;
import asset.Floor;
import asset.Tile;
import asset.Wall;

class TileTests {

	@Test
	void getSize_createTile_expect24() throws SizeLimitExceededException {
		Tile e = new Wall("assets/wall.png",24);
		int x = e.getSize();
		assertEquals(x, 24);
	}

	@Test
	void getX_createTileWithNegativ_expectException() {
		assertThrows(SizeLimitExceededException.class, () -> new Wall("assets/wall.png",-5));
	}
	
	@Test
	@DisplayName("Test that the sprite string is set on creation")
	void getSprite_createTile_expectSpriteString() throws SizeLimitExceededException{
		Tile e = new Floor("assets/wall.png", 24);
		String sprite = e.getSprite();
		assertEquals(sprite, "assets/wall.png");
	}
}
