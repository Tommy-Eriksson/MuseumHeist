package logic;

import asset.Player;
import handler.InputHandler;

public class ProcessInput {
	private Collision collision = new Collision();
	private int speed;

	public void process(InputHandler input, Player player) {
		this.speed = player.getSpeed();
		if (input.keyActivated("LEFT") && !input.keyActivated("RIGHT")) {
			for (int i = 0; i < speed; i++) {
				if (!collision.collide(player.getX() - 1, player.getY()))
					player.move(player.getX() - 1, player.getY());
				else
					break;
			}
		}
		if (!input.keyActivated("LEFT") && input.keyActivated("RIGHT")) {
			for (int i = 0; i < speed; i++) {
				if (!collision.collide(player.getX() + 1, player.getY()))
					player.move(player.getX() + 1, player.getY());
				else
					break;
			}
		}
		if (input.keyActivated("UP") && !input.keyActivated("DOWN")) {
			for (int i = 0; i < speed; i++) {
				if (!collision.collide(player.getX(), player.getY() - 1))
					player.move(player.getX(), player.getY() - 1);
				else
					break;
			}
		}
		if (!input.keyActivated("UP") && input.keyActivated("DOWN")) {
			for (int i = 0; i < speed; i++) {
				if (!collision.collide(player.getX(), player.getY() + 1))
					player.move(player.getX(), player.getY() + 1);
				else
					break;
			}
		}

		if (input.keyActivated("SHIFT")) {
			player.run(true);
		}
		if (!input.keyActivated("SHIFT")) {
			player.run(false);
		}
	}
}
