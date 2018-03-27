import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.Game;
import settings.Settings;

public class Launcher extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Game game = new Game();
		stage.setScene(new Scene(game.init("level1"),Settings.getWidth(),Settings.getHeight()));
		
		game.start();
		
		stage.show();
	}

}
