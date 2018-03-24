package logic;

import java.io.FileNotFoundException;
import java.io.IOException;

import handler.Level;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import settings.Settings;

public class Game {
	private final String BG_IMG = "src/asset/background.png"; // This should not be in this class
	
	private Pane root;
	
	private Level levelHandler = new Level();
	private Object[][] level;
	
	private int tileSize = Settings.getTileSize();
	private int xOffset = Settings.getOffsetX();
	private int yOffset = Settings.getOffsetY();
	
	public Pane init(String name) throws FileNotFoundException {
		Pane root = new Pane();
		
		try {
			level = levelHandler.getLevel(name);
		} catch (IOException e) {
			throw new FileNotFoundException("Could not locate " + name);
		}
		
		BackgroundImage bgImg = new BackgroundImage(new Image(BG_IMG), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
				BackgroundPosition.DEFAULT,
				new BackgroundSize(tileSize, tileSize, false, false, false, false));
		root.setBackground(new Background(bgImg));
		
		// Create the outer walls of the playfield
		
		return root;
	}
}
