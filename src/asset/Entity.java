package asset;

import settings.Settings;

public interface Entity {


	void relocate(int x, int y);

	default int setX(int x) {
		if (x > 0 && x < Settings.getWidth())
			return x;
		else
			throw new IllegalArgumentException("x out of bounds");
	};

	default int setY(int y) {
		if (y > 0 && y < Settings.getHeight())
			return y;
		else
			throw new IllegalArgumentException("y out of bounds");
	}
	
	int getX();

	int getY();
	
	String getSprite();
	
}