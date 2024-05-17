package bullethellgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Background extends GameObject {

	private BufferedImage image;

	public Background(int x, int y, ID id, int length, int width, Handler handler, BufferedImage image) {
		super(x, y, id, length, width, handler);
		this.image = image;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image.getScaledInstance(width, length, 0), (int) x, (int) y, null);

//		g.drawImage(image, x, y, width, length, x, y, x, y, null);

	}

}
