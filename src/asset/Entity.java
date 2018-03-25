package asset;

import settings.Settings;

public abstract class Entity {

	int x;
	int y;
	String sprite;

	public void relocate(int x, int y) {
		this.x = x;
		this.y = y;
	};

	protected int setX(int x) {
		if (x >= 0 && x <= Settings.getWidth())
			return x;
		else
			throw new IllegalArgumentException("x out of bounds");
	};

	protected int setY(int y) {
		if (y >= 0 && y <= Settings.getHeight())
			return y;
		else
			throw new IllegalArgumentException("y out of bounds");
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	};
	
	public String getSprite() {
		return sprite;
	};
	
}