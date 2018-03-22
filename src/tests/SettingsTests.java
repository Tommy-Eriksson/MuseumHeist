package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SettingsTests {

	@Test
	@DisplayName("Create window and get the default height")
	void getHeigth_expect1024() {
		
		Settings w = new Settings();
		int height = w.getHeight();
		assertEquals(height, 1024);
	}
	
	@Test
	@DisplayName("Create window and get the default width")
	void getWidth_expect728() {
		Settings w = new Settings();
		int width = w.getWidth();
		assertEquals(width, 728);
	}
}
