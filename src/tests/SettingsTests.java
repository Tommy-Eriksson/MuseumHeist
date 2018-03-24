package tests;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import settings.Settings;

class SettingsTests {

	@Test
	@DisplayName("get the default height")
	void getHeigth_expect1024() {
		
		Settings s = new Settings();
		int height = s.getHeight();
		System.out.println(height);	
		assertEquals(768, height);
	}
	
	@Test
	@DisplayName("get the default width")
	void getWidth_expect728() {
		
		Settings s = new Settings();
		int width = s.getWidth();
		assertEquals(1024, width);
	}
	
	@Test
	@DisplayName("get the default offsetX")
	void getOffsetX_expect212() {
		
		Settings s = new Settings();
		int offset = s.getOffsetX();
		assertEquals(212, offset);
	}
	
	@Test
	@DisplayName("get the default offsetY")
	void getOffsetY_expect144() {
		
		Settings s = new Settings();
		int offset = s.getOffsetY();
		assertEquals(144, offset);
	}
	
	@Test
	@DisplayName("get the default tilesize")
	void getTileSize_expect24() {
		
		Settings s = new Settings();
		int tileSize = s.getTileSize();
		assertEquals(24, tileSize);
	}
	
	
}
