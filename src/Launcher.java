import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import handler.InputHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.Game;
import settings.Settings;

public class Launcher extends Application {

	public static InputHandler input;
	public static String levelName;
	
	public static void main(String[] args) throws IOException {
		final JFileChooser fc = new JFileChooser();
		File workingDirectory = new File(System.getProperty("user.dir") + "/src/asset/level");
		fc.setCurrentDirectory(workingDirectory);
		fc.showOpenDialog(null);
		File file = fc.getSelectedFile();
		// This is where a real application would open the file.
		System.out.println("Opening: " + file.getName());
		levelName = file.getName().replaceFirst(".mhl", "");
		
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("MuseumHeist");
		input = new InputHandler();

		Game game = new Game();
		Scene scene = new Scene(game.init(levelName, input), Settings.getWidth(), Settings.getHeight());
		scene.setOnKeyPressed(EventHandler -> {
			input.addKey(EventHandler.getCode().toString());
		});
		scene.setOnKeyReleased(EventHandler -> {
			input.removeKey(EventHandler.getCode().toString());
		});
		stage.setScene(scene);

		game.start();

		stage.show();
	}

}
