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

	private boolean on = true;
	private int currentTime = 0;

	private double currentDegree;
	private int targetDegree;
	private double x2;
	private double y2;
	private int length = 0;

	public Laser(int x, int y, String sprite, int dir, int time, int maxDegree, int minDegree, int speed) {
		this.x = setX(x);
		this.y = setY(y);
		this.sprite = sprite;
		this.time = time * 100;
		this.maxDegree = setDegree(maxDegree);
		this.minDegree = setDegree(minDegree);
		this.speed = speed;
		this.currentDegree = setDegree(maxDegree);
		switch (dir) {
		case 2:
			this.dir = "down";
			this.x = x + (Settings.getTileSize() / 2);
			this.y = y + (Settings.getTileSize() / 4) * 3;
			break;
		case 4:
			this.dir = "left";
			this.x = x + (Settings.getTileSize() / 4);
			this.y = y + (Settings.getTileSize() / 2);
			break;
		case 6:
			this.dir = "right";
			this.x = x + (Settings.getTileSize() / 4) * 3;
			this.y = y + (Settings.getTileSize() / 2);
			break;
		case 8:
			this.dir = "up";
			this.x = x + (Settings.getTileSize() / 2);
			this.y = y + (Settings.getTileSize() / 4);
			break;
		}
		if (maxDegree > minDegree)
			targetDegree = setDegree(minDegree);
		else
			targetDegree = setDegree(maxDegree);
	}

	public void update() {
		if (currentDegree >= targetDegree) {
			currentDegree = currentDegree - (speed * 0.5);
		} else if (currentDegree <= targetDegree) {
			currentDegree = currentDegree + (speed * 0.5);
		}

		if (targetDegree == maxDegree && currentDegree == maxDegree) {
			targetDegree = minDegree;
		} else if (targetDegree == minDegree && currentDegree == minDegree) {
			targetDegree = maxDegree;
		}

		if (time != 0) {
			if (currentTime < (time * 2) && on) {
				beam();
				currentTime++;
				if (currentTime == time) {
					on = false;
					currentTime = 0;
				}
			} else if (currentTime < time && !on) {
				currentTime++;
				if (currentTime == time) {
					on = true;
					currentTime = 0;
				}
			}
		} else
			beam();

	}

	private void beam() {

		double radians = Math.toRadians(currentDegree);
		boolean getEndPos = true;
		int playerX = Game.getPlayerPos()[0];
		int playerY = Game.getPlayerPos()[1];
		length = 0;

		int cellX;
		int cellY;
		Object cell;

		while (getEndPos) {
			length = length + 2;

			x2 = x + length * Math.cos(radians);
			y2 = y + length * Math.sin(radians);

			cellX = (int) (x2 - Settings.getOffsetX()) / Settings.getTileSize();
			cellY = (int) (y2 - Settings.getOffsetY()) / Settings.getTileSize();

			Game.getGC().setStroke(Color.RED);
			Game.getGC().strokeLine(x, y, x2, y2);

			if (cellX < 0 || cellY < 0 || cellX > Game.getLevelSize() - 1 || cellY > Game.getLevelSize() - 1) {
				getEndPos = false;
				break;
			}

			cell = Game.getCell(cellX, cellY);
			if (cell instanceof Integer) {
				if ((Integer) cell == 1 || x2 < Settings.getOffsetX()
						|| x2 > Settings.getWidth() - Settings.getOffsetX() || y2 < Settings.getOffsetY()
						|| y2 > Settings.getHeight()) {
					{
						getEndPos = false;
						break;
					}
				}
			}
			if (x2 > playerX && x2 < playerX + Settings.getTileSize() && y2 > playerY
					&& y2 < playerY + Settings.getTileSize()) {
				System.out.println("Player HIT!");
			}
		}

		Game.getGC().setStroke(Color.RED);
		Game.getGC().strokeLine(x, y, x2, y2);
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

	public String getDir() {
		return dir;
	}

	private int setDegree(int degree) {
		switch (degree) {
		case 1:
			degree = 135; // 315
			break;
		case 2:
			degree = 90; // 270
			break;
		case 3:
			degree = 45; // 225
			break;
		case 4:
			degree = 180; // 0
			break;
		case 6:
			degree = 0; // 180
			break;
		case 7:
			degree = 225; // 45
			break;
		case 8:
			degree = 270;
			break;
		case 9:
			degree = 315; // 135
			break;
		}
		return degree;
	}

	public int getCurrentDegree() {
		return (int) this.currentDegree;
	}

	public int getTargetDegree() {
		return this.targetDegree;
	}
}
