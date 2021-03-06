package logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import javax.naming.SizeLimitExceededException;

import asset.*;
import handler.InputHandler;
import handler.Level;
import handler.Level.Cell;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
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
	private static Pane root;
	private Canvas canvas;
	private static GraphicsContext gc;

	private boolean running = false;

	private InputHandler input;
	private ProcessInput processInput = new ProcessInput();
	private Level levelHandler = new Level();
	private static Object[][] level;

	private int tileSize = Settings.getTileSize();
	private int width = Settings.getWidth();
	private int height = Settings.getHeight();
	private int xOffset = Settings.getOffsetX();
	private int yOffset = Settings.getOffsetY();

	private Tile floorTile;
	private Tile wallTile;

	private static Player playerEntity;
	private static Door doorExit;
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

				processInput.process(input, playerEntity);
				gc.clearRect(0, 0, Settings.getWidth(), Settings.getHeight());
				update(gc);
			}
		}.start();
	}

	public void stop() {
		running = false;
	}

	private void update(GraphicsContext gc) {
		for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level.length; x++) {
				if (level[y][x] instanceof Laser) {
					((Laser) level[y][x]).update();
				}
			}
		}
	}

	// TODO Create the gameboard, need tiles and entitys to make it easier
	public Pane init(String name, InputHandler input) throws FileNotFoundException, SizeLimitExceededException {
		this.input = input;

		root = new Pane();
		canvas = new Canvas(width, height);
		gc = canvas.getGraphicsContext2D();
		Rectangle2D viewPort;
		ImageView sprite;

		root.getChildren().add(canvas);

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
		int[] data;
		for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level.length; x++) {
				// If wall, add wall to root and make level easier to handle
				switch (((Cell) level[y][x]).getType()) {
				case 0: // Floor
					level[y][x] = 0;
					break;
				case 1: // Wall
					level[y][x] = 1;
					wall = new ImageView(new Image(wallTile.getSprite()));
					wall.relocate((x * tileSize) + xOffset, (y * tileSize) + yOffset);
					root.getChildren().add(wall);
					break;
				case 2: // Door
					// Fetch level data
					data = ((Cell) level[y][x]).getData();

					String dir;
					if (data[0] == 4)
						dir = "left";
					else
						dir = "right";

					int treasuresLeft = data[2];
					boolean exit;
					if (data[1] == 0) {
						exit = false;

						// Place player at the entrance
						playerEntity = new Player((x * tileSize) + xOffset+1, (y * tileSize) + yOffset+1,
								"asset/player.png");
						player = new ImageView(new Image(playerEntity.getSprite()));
						playerEntity.setImageView((ImageView) player);
						player.relocate((x * tileSize) + xOffset, (y * tileSize) + yOffset);
						player.setId("player");
						root.getChildren().add(player);
					} else {
						exit = true;
						doorExit = new Door(x, y, "asset/door.png", dir, exit, treasuresLeft);
					}

					// Add door to level
					level[y][x] = new Door(x, y, "asset/door.png", dir, exit, treasuresLeft);

					// Set viewport of image, this is so we can use same image as both closed and
					// open
					viewPort = new Rectangle2D(0, 0, tileSize, tileSize);
					sprite = new ImageView();
					sprite.setViewport(viewPort);
					sprite.setImage(new Image(((Door) level[y][x]).getSprite()));
					door = sprite;

					// Place door at the right place and at the right rotation
					if (dir.equals("left")) {
						door.relocate((x * tileSize) + xOffset - tileSize, (y * tileSize) + yOffset);
					} else {
						door.setRotate(180);
						door.relocate((x * tileSize) + xOffset + tileSize, (y * tileSize) + yOffset);
					}

					if (exit)
						door.setId("exit");
					root.getChildren().add(door);
					break;
				case 3: // Treasure
					data = ((Cell) level[y][x]).getData();

					level[y][x] = new Treasure((Settings.getTileSize() * x) + Settings.getOffsetX(),
							(Settings.getTileSize() * y) + Settings.getOffsetY(), "asset/treasure.png", data[0]);

					viewPort = new Rectangle2D(0, tileSize * (new Random().nextInt(4)), tileSize, tileSize);
					sprite = new ImageView();
					sprite.setViewport(viewPort);
					sprite.setImage(new Image(((Treasure) level[y][x]).getSprite()));

					treasure = sprite;
					treasure.relocate((x * tileSize) + xOffset, (y * tileSize) + yOffset);

					treasure.setId("treasure");
					root.getChildren().add(treasure);
					break;
				case 4: // Laser
					data = ((Cell) level[y][x]).getData();

					level[y][x] = new Laser((Settings.getTileSize() * x) + Settings.getOffsetX(),
							(Settings.getTileSize() * y) + Settings.getOffsetY(), "asset/laser.png", data[0], data[1],
							data[2], data[3], data[4]);

					laser = new ImageView(new Image("asset/laser.png"));
					switch (((Laser) level[y][x]).getDir()) {
					case "up":
						laser.setRotate(90);
						break;
					case "right":
						laser.setRotate(180);
						break;
					case "down":
						laser.setRotate(270);
						break;
					default:
						break;
					}

					laser.relocate((x * tileSize) + xOffset, (y * tileSize) + yOffset);
					root.getChildren().add(laser);
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

	public static Object getCell(int x, int y) {
		return level[y][x];
	}

	public static int getLevelSize() {
		return level.length;
	}

	public static boolean checkTreasure(int x, int y) {
		for (Node node : root.getChildren()) {
			int x2 = ((int) node.getLayoutX()) - (Settings.getTileSize() / 2);
			int y2 = ((int) node.getLayoutY()) - (Settings.getTileSize() / 2);
			int x3 = ((int) node.getLayoutX()) + Settings.getTileSize() - (Settings.getTileSize() / 2);
			int y3 = ((int) node.getLayoutY()) + Settings.getTileSize() - (Settings.getTileSize() / 2);
			if (x > x2 && x < x3 && y > y2 && y < y3) {
				if (node.getId().equals("treasure")) {
					root.getChildren().remove(node);
					return true;
				}
			}
		}
		return false;
	}

	public static Door getDoorExit() {
		return doorExit;
	}

	public static void openDoor() {
		int x = (doorExit.getX() * Settings.getTileSize()) + Settings.getOffsetX() + Settings.getTileSize();
		int y = (doorExit.getY() * Settings.getTileSize()) + Settings.getOffsetY();

		Rectangle2D viewPort = new Rectangle2D(0, Settings.getTileSize(), Settings.getTileSize(),
				Settings.getTileSize());
		ImageView sprite = new ImageView();
		sprite.setViewport(viewPort);
		sprite.setImage(new Image(doorExit.getSprite()));
		sprite.relocate(x, y);
		sprite.setRotate(180);

		for (int i = 0; i < root.getChildren().size(); i++) {
			if (root.getChildren().get(i).getId() != null) {
				if (root.getChildren().get(i).getId().equals("exit")) {
					root.getChildren().remove(i);
					root.getChildren().add(sprite);
				}
			}
		}
	}

	public static int[] getPlayerPos() {
		int[] playerPos = { playerEntity.getX(), playerEntity.getY() };
		return playerPos;
	}

	public static Player getPlayerEntity() {
		return playerEntity;
	}

	public static GraphicsContext getGC() {
		return gc;
	}
}
