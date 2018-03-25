package asset;

public class Treasure implements Entity{

	int x;
	int y;
	String sprite;
	int points;
	
	public Treasure(int x, int y, String sprite, int points){
		this.x = setX(x);
		this.y = setY(y);
		this.sprite = sprite;
		this.points = points;
	}
	
	public int getPoints(){
		return points;
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
