import handler.InputHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.Game;
import settings.Settings;

public class Launcher extends Application {

	public static InputHandler input;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		input = new InputHandler();

		Game game = new Game();
		Scene scene = new Scene(game.init("level1", input), Settings.getWidth(), Settings.getHeight());
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
