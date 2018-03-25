package asset;

public class Treasure extends Entity{

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
	
}
