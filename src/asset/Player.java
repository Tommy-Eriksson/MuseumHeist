package asset;

/**
 * The Class Player.
 */
public class Player extends Entity {

	/** The speed. */
	private int speed = 2;
	
	/** The sprite. */
	final String sprite = "assets/player.png";
	
	/**
	 * Instantiates a new player.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Player(int x, int y) {
		this.x = setX(x);
		this.y = setY(y);
		}
	
	/**
	 * Instantiates a new player.
	 *
	 * @param x the x
	 * @param y the y
	 * @param sprite the sprite
	 */
	public Player(int x, int y, String sprite) {
		this.x = setX(x);
		this.y = setY(y);
		}
		
	/**
	 * Instantiates a new player.
	 *
	 * @param x the x
	 * @param y the y
	 * @param sprite the sprite
	 * @param speed the speed
	 */
	public Player(int x, int y, String sprite, int speed) {
		this.x = setX(x);
		this.y = setY(y);
		this.speed = speed;
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
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * Move.
	 *
	 * @param x the x
	 * @param y the y
	 * @return the player
	 */
	public Player move(int x, int y) {
		relocate(x, y);
		return null;
	}


		
	
}
