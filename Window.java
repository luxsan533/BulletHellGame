//This file creates the window we can play our game in
package bullethellgame;

import java.awt.Canvas;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

public class Window extends Canvas{
	private static final long serialVersionUID = -240848680533728354L;

	public Window(int width, int height, String title, Game game) throws IOException {
		JFrame frame = new JFrame(title);
//		frame.getContentPane().add(new JPanelWithBackground("battlebeastthragg.jpg"));

		frame.setPreferredSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setDefaultClosed allows the x button to close the window
		frame.setResizable(false);
//		lets us resize our window, we set it to false
		frame.setLocationRelativeTo(null);
//		this starts the window in the middle of the screen rather than the top left
		frame.add(game);
//		adds the game to the frame, VERY IMPORTANT
		frame.setVisible(true);
//		allows us to see the frame
		game.start();
//		starts the game, created in the game.java file
	}
}
