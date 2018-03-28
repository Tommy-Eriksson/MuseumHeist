package logic;

import asset.Door;
import asset.Treasure;
import settings.Settings;

public class Collision {

	private int width = Settings.getWidth();
	private int height = Settings.getHeight();
	private int xOffset = Settings.getOffsetX();
	private int yOffset = Settings.getOffsetY();
	private int tileSize = Settings.getTileSize();
	private Object cell;

	public boolean collide(int x, int y) {
		int xTileLeft = (x - xOffset) / tileSize;
		int yTileLeft = (y - yOffset) / tileSize;
		int xTileRight = (x - xOffset + tileSize) / tileSize;
		int yTileRight = (y - yOffset + tileSize) / tileSize;

		if (x <= xOffset || x >= width - xOffset - tileSize || y <= yOffset || y + tileSize >= height - tileSize) {
			// Collision outer wall
			return true;
		} else if (xTileRight > 0 || xTileRight <= Game.getLevelSize() - 1 || xTileLeft > 0
				|| xTileLeft <= Game.getLevelSize() - 1 || yTileRight > 0 || yTileRight <= Game.getLevelSize() - 1
				|| yTileLeft > 0 || yTileLeft <= Game.getLevelSize() - 1) {
			// Check cell
			int[] val = { xTileRight, yTileRight, xTileLeft, yTileLeft };
			int[][] order = { { 0, 1 }, { 0, 3 }, { 2, 3 }, { 2, 1 } };
			for (int i = 0; i < 4; i++) {

				if (val[order[i][0]] <= Game.getLevelSize() - 1 && val[order[i][1]] <= Game.getLevelSize() - 1) {
					if (processCollision(Game.getCell(val[order[i][0]], val[order[i][1]]),x,y))
						return true;
				}
			}
		}
		return false;
	}

	private boolean processCollision(Object cell, int x, int y) {
		if (cell instanceof Integer) {
			if ((Integer) cell == 1) {
				return true;
			}
		} else if (cell instanceof Treasure) {
			if(Game.checkTreasure(x, y)) {
				Door exitDoor = Game.getDoorExit();
				exitDoor.foundTreasure(1);
				if(exitDoor.isOpen()) {
					Game.openDoor();
				}
			}
		}else if(cell instanceof Door) {
			int playerX =Game.getPlayerPos()[0];
			int doorExitLeft =Settings.getWidth()-Settings.getOffsetX()-Settings.getTileSize();
			if(Game.getDoorExit().isOpen() && (playerX >= doorExitLeft-2 || playerX >= doorExitLeft-1))
				System.out.println("avslut");
		}

		return false;
	}
}
