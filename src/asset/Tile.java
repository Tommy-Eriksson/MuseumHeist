package asset;

import javax.naming.SizeLimitExceededException;

public abstract class Tile {

	private final String sprite;
	private int size;

	Tile(String s, int i) throws SizeLimitExceededException {
		this.sprite = s;
		setSize(i);
	}

	private void setSize(int i) throws SizeLimitExceededException {
		if (i < 1)
			throw new SizeLimitExceededException("Size cant be lower than 1");
		this.size = i;
	}

	public int getSize() {
		return size;
	}

	public String getSprite() {
		return sprite;
	}
}
