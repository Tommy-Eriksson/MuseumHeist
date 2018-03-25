package logic;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.naming.SizeLimitExceededException;

import asset.Floor;
import asset.Tile;
import asset.Wall;
import handler.Level;
import handler.Level.Cell;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import settings.Settings;

public class Game {
	private Pane root;
	private Canvas canvas;
	private GraphicsContext gc;

	private boolean running = false;

	private Level levelHandler = new Level();
	private Object[][] level;

	private int tileSize = Settings.getTileSize();
	private int width = Settings.getWidth();
	private int height = Settings.getHeight();
	private int xOffset = Settings.getOffsetX();
	private int yOffset = Settings.getOffsetY();

	private Tile floorTile;
	private Tile wallTile;

	private static Node player;
	private static Node laser;
	private static Node treasure;
	private static Node door;

	private static Node wall;
	private static Node floor;

	// TODO Gameloop logic
	public void start() {
		running = true;
		new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				if (!running)
					this.stop();

			}
		}.start();
	}

	public void stop() {
		running = false;
	}

	// TODO Create the gameboard, need tiles and entitys to make it easier
	public Pane init(String name) throws FileNotFoundException, SizeLimitExceededException {
		root = new Pane();
		canvas = new Canvas(width, height);
		gc = canvas.getGraphicsContext2D();

		floorTile = new Floor("asset/background.png", 24);
		wallTile = new Wall("asset/wall.png", 24);

		try {
			level = (Object[][]) levelHandler.getLevel(name);
		} catch (IOException e) {
			throw new FileNotFoundException("Could not locate " + name);
		}

		BackgroundImage bgImg = new BackgroundImage(new Image(floorTile.getSprite()), BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(tileSize, tileSize, false, false, false, false));
		root.setBackground(new Background(bgImg));

		// Create the outer walls of the playfield
		// The playfield is 25x25 and walls is outside of playfield
		// So we need to loop 2 more
		for (int y = 0; y < level.length + 2; y++) {
			for (int x = 0; x < level.length + 2; x++) {
				// If we are at the edges then we add a wall
				if (y == 0 || y == level.length + 1) {
					wall = new ImageView(new Image(wallTile.getSprite()));
					// Relocate wall to the right place
					// The playfield is column and cell based so we need to translate
					// cell index to x y in pixels based on window size and tile size
					wall.relocate((x * tileSize) + xOffset - tileSize, (y * tileSize) + yOffset - tileSize);
					root.getChildren().add(wall);
				}
				// If we are at the far right we add walls
				if (x == level.length + 1) {
					wall = new ImageView(new Image(wallTile.getSprite()));
					wall.relocate((x * tileSize) + xOffset - tileSize, (y * tileSize) + yOffset - tileSize);
					root.getChildren().add(wall);
				}
			}
			// Far left wall
			wall = new ImageView(new Image(wallTile.getSprite()));
			wall.relocate((0 * tileSize) + xOffset - tileSize, (y * tileSize) + yOffset - tileSize);
			root.getChildren().add(wall);
		}

		// TODO Create board and add tiles and entitys to level
		for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level.length; x++) {
				// If wall, add wall to root and make level easier to handle
				switch (((Cell) level[y][x]).getType()) {
				case 0: // Floor
					level[y][x] = new Integer(0);
					break;
				case 1: // Wall
					level[y][x] = new Integer(1);
					wall = new ImageView(new Image(wallTile.getSprite()));
					wall.relocate((x * tileSize) + xOffset, (y * tileSize) + yOffset);
					root.getChildren().add(wall);
					break;
				case 2: // Door
					
					break;
				case 3: // Treasure
					
					break;
				case 4: // Laser
					
					break;
				default:
					throw new NumberFormatException("Not valid level data");
				}
			}
		}

		return root;
	}

	public boolean getGameLoopStatus() {
		return running;
	}
}
