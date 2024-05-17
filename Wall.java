package bullethellgame;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends GameObject{

	public Wall(int x, int y, ID id, int length, int width, Handler handler) {
		super(x, y, id, length, width, handler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		block();
	}

	@Override
	public void render(Graphics g) {
		g.fillRect(x, y, length, width);
//		g.drawString(Integer.toString(this.x), 50, 200);
	}
}