package asset;

public class Door implements Entity {

	private int x;
	private int y;
	private final String sprite;
	private String dir;
	private boolean exit;
	private int treasuresLeft;
	private boolean open = false;

	public Door(int x, int y, String sprite, String dir, boolean exit, int treasuresLeft) {
		this.x = setX(x);
		this.y = setY(y);
		this.sprite = sprite;
		this.dir = dir;
		this.exit = exit;
		this.treasuresLeft = treasuresLeft;
	}

	public String getDir() {
		return dir;
	}

	public boolean isExit() {
		return exit;
	}

	public void foundTreasure(int treasure) {
		if(!open) {
			treasuresLeft = treasuresLeft -treasure;
			if(treasuresLeft <=0) {
				setOpen(true);
			}
		}
	}
	
	public int getTreasuresLeft() {
		return treasuresLeft;
	}

	public boolean isOpen() {
		return open;
	}

	private void setOpen(boolean open) {
		this.open = open;
	}

	@Override
	public void relocate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public String getSprite() {
		return sprite;
	}

}
