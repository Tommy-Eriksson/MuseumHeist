package asset;

import javafx.scene.image.ImageView;

/**
 * The Class Player.
 */
public class Player extends Entity {

	/** The speed. */
	private int speed = 2;
	private ImageView playerNode;

	/**
	 * Instantiates a new player.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param sprite
	 *            the sprite
	 * @param speed
	 *            the speed
	 */
	public Player(int x, int y, String sprite, int speed) {
		this.x = setX(x);
		this.y = setY(y);
		this.sprite = sprite;
		this.speed = speed;
	}

	/**
	 * Instantiates a new player.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param sprite
	 *            the sprite
	 */
	public Player(int x, int y, String sprite) {
		this.x = setX(x);
		this.y = setY(y);
		this.sprite = sprite;
	}

	/**
	 * Gets the speed.
	 *
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Sets the speed.
	 *
	 * @param speed
	 *            the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * Move.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @return the player
	 */
	public Player move(int x, int y) {
		relocate(x, y);
		if (playerNode != null)
			playerNode.relocate(x, y);
		return null;
	}

	public void setImageView(ImageView playerView) {
		this.playerNode = playerView;
	}
	
	public void run(boolean b) {
		if(b)
			this.speed = 4;
		else
			this.speed = 2;
	}
	
	public boolean isRunning() {
		return (speed != 2)? true : false;
	}
}
