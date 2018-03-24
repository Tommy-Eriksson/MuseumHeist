package settings;

public class Settings {

	private static final int WIDTH = 1024;
	private static final int HEIGHT = 768;
	private static final int OFFSET_X = 212;
	private static final int OFFSET_Y = 144;
	private static final int TILE_SIZE = 24;
	
	public static int getWidth() {
		return WIDTH;
	}
	public static int getHeight() {
		return HEIGHT;
	}
	public static int getOffsetX() {
		return OFFSET_X;
	}
	public static int getOffsetY() {
		return OFFSET_Y;
	}
	public static int getTileSize() {
		return TILE_SIZE;
	}
}
