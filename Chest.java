package bullethellgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Chest extends GameObject{

	private GameObject content;
	private BufferedImage lockedimage;
	private BufferedImage openimage;

	public Chest(int x, int y, ID id, int length, int width, Handler handler, boolean isLocked, GameObject content) {
		super(x, y, id, length, width, handler);

		BufferedImageLoader loader = new BufferedImageLoader();

		this.lockedimage = loader.loadImage("/lockedchestgame.jpg");
		this.openimage = loader.loadImage("/openchestgame.jpg");

		this.isLocked = isLocked;
		this.content = content;
		content.setX(x);
		content.setY(y + 32);
	}

	@Override
	public void tick() {
		if (!isLocked && content != null) {
			handler.addObject(content);
			content = null;
			}
		block();
	}

	@Override
	public void render(Graphics g) {
		if (isLocked) {
			g.drawImage(this.lockedimage, (int) x, (int) y, null);
			for (int i = 0; i < handler.objectList.size(); i++) {
			GameObject tempObject = handler.objectList.get(i);
			if (tempObject.getID() == ID.Player)
				if(keyBounds().intersects(tempObject.getBounds())) {
					g.setColor(Color.yellow);
					g.drawString("Press K to unlock", tempObject.getX() - 20, tempObject.getY() + 47);
			}
		}
	} else {			
		g.drawImage(this.openimage, (int) x, (int) y, null);
}
}

}
