package asset;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Game;
import settings.Settings;

public class Laser extends Entity {

	String dir;
	int time;
	int maxDegree;
	int minDegree;
	int speed;

	private double x2;
	private double y2;
	private int length = 0;
	private double currentDegree;

	Laser() {

	}

	public Laser(int x, int y, String sprite, String dir, int time, int maxDegree, int minDegree, int speed) {
		this.x = setX(x);
		this.y = setY(y);
		this.sprite = sprite;
		this.dir = dir;
		this.time = time;
		this.maxDegree = maxDegree;
		this.minDegree = minDegree;
		this.speed = speed;
		this.currentDegree = maxDegree;
	}

	public GraphicsContext update(GraphicsContext gc) {
		if (currentDegree < minDegree) {
			currentDegree = currentDegree + (speed * 0.1);
		} else if (currentDegree > minDegree) {
			currentDegree = currentDegree - (speed * 0.1);
		}

		if (currentDegree == minDegree) {
			int temp = minDegree;
			minDegree = maxDegree;
			maxDegree = temp;
			currentDegree = maxDegree;
		}

		return beam(gc);
	}

	private GraphicsContext beam(GraphicsContext gc) {

		double radians = Math.toRadians(currentDegree);
		boolean getEndPos = true;
		int playerX = Game.getPlayerPos()[0];
		int playerY = Game.getPlayerPos()[1];
		
		int cellX;
		int cellY;
		Object cell;

		while (getEndPos) {
			length = length + 2;

			x2 = x + length * Math.cos(radians);
			y2 = y + length * Math.sin(radians);
			
			cellX = (int) (x2 - Settings.getOffsetX()) / Settings.getTileSize();
			cellY = (int) (y2 - Settings.getOffsetY()) / Settings.getTileSize();
			cell = Game.getCell(cellX, cellY);

			if (cellX < 0 && cellY < 0 || cellX > Game.getLevelSize() - 1 || cellY > Game.getLevelSize() - 1)
				getEndPos = false;
			if (cell instanceof Integer) {
				if ((Integer) cell == 1 || x2 < Settings.getOffsetX()
						|| x2 > Settings.getWidth() - Settings.getOffsetX() || y2 < Settings.getOffsetY()
						|| y2 > Settings.getHeight()) {
					getEndPos = false;
				}
			}
			if(x2 > playerX && x2 < playerX + Settings.getTileSize() && y2 > playerY && y2 < playerY + Settings.getTileSize()) {
				// TODO Laser hit player!
			}
		}
		
		gc.setStroke(Color.RED);
		gc.strokeLine(x, y, x2, y2);
		return gc;
	}

	public double getX2() {
		return x2;
	}

	public double getY2() {
		return y2;
	}
	
	public int getMaxDegree() {
		return maxDegree;
	}
	
	public int getMinDegree() {
		return minDegree;
	}
}
