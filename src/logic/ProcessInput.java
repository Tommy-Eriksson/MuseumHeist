package logic;

import asset.Player;
import handler.InputHandler;

public class ProcessInput {
	public void process(InputHandler input, Player player) {
		if (input.keyActivated("LEFT") && !input.keyActivated("RIGHT")) {
			player.move(player.getX() - player.getSpeed(), player.getY());
		}
		if (!input.keyActivated("LEFT") && input.keyActivated("RIGHT")) {
			player.move(player.getX() + player.getSpeed(), player.getY());
		}
		if (input.keyActivated("UP") && !input.keyActivated("DOWN")) {
			player.move(player.getX(), player.getY() - player.getSpeed());
		}
		if (!input.keyActivated("UP") && input.keyActivated("DOWN")) {
			player.move(player.getX(), player.getY() + player.getSpeed());
		}

		if (input.keyActivated("SHIFT")) {
			player.run(true);
		}
		if (!input.keyActivated("SHIFT")) {
			player.run(false);
		}
	}
}
