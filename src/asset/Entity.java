package asset;

import settings.Settings;

public class Entity {

	private final String sprite;
	private int x;
	private int y;
	private boolean visible;
	
	protected void relocate(int x, int y) {
		setX(x);
		this.y = y;
	}
	
	public Entity(int x, int y, String sprite) {
		this.sprite = sprite;
		visible = true;
		setX(x);
		this.y = y;
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getSprite() {
		return sprite;
	}

	private void setX(int x) {
		
		if (x < 0 || x > Settings.getWidth() - Settings.getTileSize()) throw new IllegalArgumentException("x out of bounds");
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}

