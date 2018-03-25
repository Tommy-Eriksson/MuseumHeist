package tests;

import java.util.concurrent.CountDownLatch;

import javax.swing.SwingUtilities;

import javafx.embed.swing.JFXPanel;

public class MockJavaFx {

	public void start() throws Exception {

		final CountDownLatch latch = new CountDownLatch(1);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// initializes JavaFX environment
				new JFXPanel();

				latch.countDown();
			}
		});
	}

}
